package com.eddie.test.mq.delay.config.rabbitInterface;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;

/**
 * @Author Eddie
 * @Date 2022/7/25 16:33
 * @Version 1.0
 * @Description
 */
public interface DirectExchangeConfig {
    @Bean
    DirectExchange directExchange();
}
