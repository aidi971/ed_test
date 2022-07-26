package com.eddie.test.mq.delay.config.exchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

/**
 * @Author Eddie
 * @Date 2022/7/26 15:08
 * @Version 1.0
 * @Description
 */
public class DelayConfig {
    public static final String dead_queue = "deal_queue";
    public static final String dead_exchangeName = "deal_Exchange";
    public static final String dead_RoutingKey = "dead_routing_key";
    public static final String DEAD_LETTER_QUEUE_KEY = "x-dead-letter-exchange";
    public static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    public DelayConfig() {
    }

    @Bean
    public Queue deadQueue() {
        Queue queue = new Queue(dead_queue, true);
        return queue;
    }

    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(dead_exchangeName);
    }

    @Bean
    public Binding bindingDeadExchange5() {
        return BindingBuilder.bind(this.deadQueue()).to(this.deadExchange()).with(dead_RoutingKey);
    }
}
