package com.eddie.test.user.delayQueue;

import com.eddie.test.mq.delay.rabbitMq.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Eddie
 * @Date 2022/7/25 17:04
 * @Version 1.0
 * @Description 延时队列
 */
@Component
public class UserDelayQueue {
    @Autowired
    private UserDelayConfig userDelayConfig;
    @Autowired
    private Sender sender;

    Logger logger = LoggerFactory.getLogger(UserDelayQueue.class);

    //发送mq
    public void sendRabbitMq(String order,Long receiverTime){
        try {
            sender.send(userDelayConfig.exchangeName,userDelayConfig.routingKey,order,receiverTime);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
