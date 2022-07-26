package com.eddie.test.user.delayQueue;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @Author Eddie
 * @Date 2022/7/25 17:39
 * @Version 1.0
 * @Description
 */
@Component
public class UserDelayReceiver {
    Logger logger = LoggerFactory.getLogger(UserDelayReceiver.class);

    @RabbitListener(queues = "${mq.user.queue}")
    @RabbitHandler
    public void handler(String order, Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        try {
//            InvestigateUtils.findInvestigateType(order);

            // 手动ack
            Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

            // 手动签收
            channel.basicAck(deliveryTag, false);

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            channel.basicReject((Long) headers.get(AmqpHeaders.DELIVERY_TAG),false);
        }
    }
}
