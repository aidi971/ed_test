package com.eddie.test.mq.delay.config.rabbitInterface;

import org.springframework.amqp.core.CustomExchange;
import org.springframework.context.annotation.Bean;

/**
 * @Author Eddie
 * @Date 2022/7/25 16:34
 * @Version 1.0
 * @Description
 */
public interface ExchangeConfig {
    @Bean
    CustomExchange customExchange();
}
