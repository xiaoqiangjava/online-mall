package com.xq.learn.inventory.rabbit;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionNameStrategy;
import org.springframework.amqp.rabbit.connection.SimplePropertyValueConnectionNameStrategy;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

/**
 * Rabbit配置类，这里只配置消费者
 * @author xiaoqiang
 * @date 2020/3/23 18:15
 */
@Configuration
public class RabbitConfig
{
    /**
     * threadFactory bean，用于创建connection线程时，指定固定的前缀
     * @param threadPrefix 线程名称前缀
     * @return threadFactory bean
     */
    @Bean
    public CustomizableThreadFactory threadFactory(@Value("${rabbit.connection.thread.prefix}")String threadPrefix)
    {
        return new CustomizableThreadFactory(threadPrefix);
    }

    /**
     * connection命名策略
     * @return nameStrategy bean
     */
    @Bean
    public ConnectionNameStrategy nameStrategy()
    {
        return new SimplePropertyValueConnectionNameStrategy("spring.application.name");
    }

    /**
     * 配置containerFactory, 与@RabbitListener注解使用时，可以指定containerFactory
     * @param connectionFactory 连接工场
     * @return containerFactory
     */
    @Bean
    public RabbitListenerContainerFactory containerFactory(ConnectionFactory connectionFactory)
    {
        SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory);
        // 设置手动应答，需要在接受到消息时手动应答消息，否则会一值等待消息应答
        containerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置最小的消费者线程数量
        containerFactory.setConcurrentConsumers(2);
        // 设置最大的消费者线程数量
        containerFactory.setMaxConcurrentConsumers(10);
        // 预拉取消息数量
        containerFactory.setPrefetchCount(5);

        return containerFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        return rabbitTemplate;
    }
}
