package com.eddie.test.mq.delay.config.rabbitInterface;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;


/**
 * @Author Eddie
 * @Date 2022/7/25 16:35
 * @Version 1.0
 * @Description
 */
public interface QueueConfig {
    @Bean
    Queue customQueue();
    @Bean
    Binding binding();
}
