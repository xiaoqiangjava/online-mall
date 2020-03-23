package com.xq.learn.order.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbit交换机
 * @author xiaoqiang
 * @date 2020/3/23 17:59
 */
@Configuration
public class RabbitExchange
{
    @Value("${stock.exchange}")
    private String exchange;

    @Value("${stock.queue}")
    private String queue;

    @Value("${stock.routing.key}")
    private String routingKey;

    @Bean
    public DirectExchange stockExchange()
    {
        return new DirectExchange(exchange);
    }

    @Bean
    public Queue stockQueue()
    {
        return new Queue(queue);
    }

    @Bean
    public Binding binding()
    {
        return BindingBuilder.bind(stockQueue()).to(stockExchange()).with(routingKey);
    }
}
