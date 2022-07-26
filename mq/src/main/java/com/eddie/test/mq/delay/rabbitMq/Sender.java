package com.eddie.test.mq.delay.rabbitMq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author Eddie
 * @Date 2022/7/25 16:36
 * @Version 1.0
 * @Description
 */
@Component
public class Sender {
    @Autowired
    AmqpTemplate amqpTemplate;

    public Sender(){

    }
    public void send(String exchangeName, String routingKey, String order, Date date) throws Exception {
        final long time = this.queueMessages(date);
        this.amqpTemplate.convertAndSend(exchangeName, routingKey, order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setDelay(Math.toIntExact(time));
                return message;
            }
        });
    }

    public void send(String exchangeName, String routingKey, String order) throws Exception {
        this.amqpTemplate.convertAndSend(exchangeName, routingKey, order);
    }

    public void send(String exchangeName, String routingKey, String order, Long date) throws Exception {
        final long time = this.queueMessages(date);
        this.amqpTemplate.convertAndSend(exchangeName, routingKey, order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setDelay(Math.toIntExact(time));
                return message;
            }
        });
    }

    public long queueMessages(Date date) throws Exception {
        long time = 0L;
        long timeIng = date.getTime();
        long currentTime = System.currentTimeMillis();
        if (timeIng > currentTime) {
            time = timeIng - currentTime;
        }

        return time;
    }

    public long queueMessages(Long date) throws Exception {
        long time = 0L;
        long currentTime = System.currentTimeMillis();
        if (date > currentTime) {
            time = date - currentTime;
        }
        return time;
    }
}
