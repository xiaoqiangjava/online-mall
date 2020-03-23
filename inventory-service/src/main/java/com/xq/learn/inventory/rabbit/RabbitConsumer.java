package com.xq.learn.inventory.rabbit;

import com.rabbitmq.client.Channel;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xiaoqiang
 * @date 2020/3/23 18:47
 */
@Component
public class RabbitConsumer
{
    private static final Logger logger = LoggerFactory.getLogger(RabbitConsumer.class);

    @RabbitListener(queues = "${stock.queue}", containerFactory = "containerFactory")
    public void consume(Message message, Channel channel) throws IOException
    {
        logger.info("<<<message>>>: {}", message);
        // 手动应答
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
}
