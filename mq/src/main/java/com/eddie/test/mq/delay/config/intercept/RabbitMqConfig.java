package com.eddie.test.mq.delay.config.intercept;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpoint;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Eddie
 * @Date 2022/7/25 16:22
 * @Version 1.0
 * @Description
 */
@Configuration
public class RabbitMqConfig implements RabbitListenerConfigurer {

    Logger logger = LoggerFactory.getLogger(RabbitMqConfig.class);

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                RabbitMqConfig.this.logger.info("ConfirmCallback:     相关数据：" + correlationData);
                RabbitMqConfig.this.logger.info("ConfirmCallback:     确认情况：" + ack);
                RabbitMqConfig.this.logger.info("ConfirmCallback:     原因：" + cause);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                RabbitMqConfig.this.logger.info("ReturnCallback:     消息：" + message);
                RabbitMqConfig.this.logger.info("ReturnCallback:     回应码：" + replyCode);
                RabbitMqConfig.this.logger.info("ReturnCallback:     回应信息：" + replyText);
                RabbitMqConfig.this.logger.info("ReturnCallback:     交换机：" + exchange);
                RabbitMqConfig.this.logger.info("ReturnCallback:     路由键：" + routingKey);
            }
        });
        return rabbitTemplate;
    }

    /**
     * Callback allowing a {@link RabbitListenerEndpointRegistry
     * RabbitListenerEndpointRegistry} and specific {@link RabbitListenerEndpoint
     * RabbitListenerEndpoint} instances to be registered against the given
     * {@link RabbitListenerEndpointRegistrar}. The default
     * {@link RabbitListenerContainerFactory RabbitListenerContainerFactory}
     * can also be customized.
     *
     * @param registrar the registrar to be configured
     */
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {

    }
}
