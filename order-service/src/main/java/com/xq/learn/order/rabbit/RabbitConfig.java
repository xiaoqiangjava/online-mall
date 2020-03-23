package com.xq.learn.order.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionNameStrategy;
import org.springframework.amqp.rabbit.connection.SimplePropertyValueConnectionNameStrategy;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

/**
 * rabbit配置类
 * @author xiaoqiang
 * @date 2020/3/23 15:29
 */
@Configuration
public class RabbitConfig
{
    private static final Logger logger = LoggerFactory.getLogger(RabbitConfig.class);

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMandatory(true);
        template.setConfirmCallback((correlationData, ack, cause) ->
        {
            String id = correlationData.getId();
            String message = correlationData.getReturnedMessage().toString();
            logger.info("<<<confirmCallback>>>");
            logger.info("id: {}, message: {}", id, message);
            logger.info("ack: " + ack);
            logger.info("cause: " + cause);
        });
        template.setReturnCallback((message, replyCode, replyText, exchange, routingKey) ->
        {
            logger.info("<<<returnCallback>>>");
            logger.info("message: " + message);
            logger.info("replyCode: {}, replyText: {}, exchange: {}, routingKey: {}", replyCode, replyText, exchange, routingKey);
        });

        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    /**
     * 注入ThreadFactory，目的是为connection中的线程配置名称前缀
     * @param threadPrefix 线程名称前缀
     * @return threadFactory
     */
    @Bean
    public CustomizableThreadFactory threadFactory(@Value("${rabbit.connection.thread.prefix}")String threadPrefix)
    {
        return new CustomizableThreadFactory(threadPrefix);
    }

    /**
     * 注入Connection命名策略，指定的属性名称必须在application.properties配置文件中存在，即在Environment
     * @return connectionNameStrategy bean
     */
    @Bean
    public ConnectionNameStrategy connectionNameStrategy()
    {
        return new SimplePropertyValueConnectionNameStrategy("spring.application.name");
    }
}
