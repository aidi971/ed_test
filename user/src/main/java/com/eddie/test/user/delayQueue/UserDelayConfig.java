package com.eddie.test.user.delayQueue;

import com.eddie.test.mq.delay.config.exchange.CustomExchangeConfig;
import com.eddie.test.mq.delay.config.rabbitInterface.QueueConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Eddie
 * @Date 2022/7/25 17:12
 * @Version 1.0
 * @Description  设置队列绑定交换机
 */
@Configuration
public class UserDelayConfig extends CustomExchangeConfig implements QueueConfig {

     //队列名称
    @Value("${mq.user.queue}")
    public String queue;

    @Value("${mq.user.routingKey}")
    public String routingKey;

    @Override
    @Bean("UserDelayConfig_customQueue")
    public Queue customQueue() {
        //将普通队列绑定到死信队列交换机上
        Map<String, Object> args = new HashMap<>(2);
        args.put(DEAD_LETTER_QUEUE_KEY, dead_exchangeName);
        args.put(DEAD_LETTER_ROUTING_KEY, dead_RoutingKey);
        // 第一个参数是创建的queue的名字，第二个参数是是否支持持久化
        return new Queue(queue, true);
    }

    @Override
    @Bean("UserDelayConfig_binding")
    public Binding binding() {
        return BindingBuilder.bind(customQueue()).to(customExchange()).with(routingKey).noargs();
    }
}
