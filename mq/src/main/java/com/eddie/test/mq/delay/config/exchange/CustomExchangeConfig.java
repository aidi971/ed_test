package com.eddie.test.mq.delay.config.exchange;

import com.eddie.test.mq.delay.config.rabbitInterface.ExchangeConfig;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Eddie
 * @Date 2022/7/25 17:21
 * @Version 1.0
 * @Description
 */
public class CustomExchangeConfig extends DelayConfig implements ExchangeConfig {
    @Value("${mq.exchangeName}")
    public String exchangeName;

    public CustomExchangeConfig() {
    }

    @Override
    @Bean
    public CustomExchange customExchange() {
        Map<String, Object> args = new HashMap();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(this.exchangeName, "x-delayed-message", true, false, args);
    }
}
