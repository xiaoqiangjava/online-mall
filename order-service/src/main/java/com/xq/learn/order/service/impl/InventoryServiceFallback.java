package com.xq.learn.order.service.impl;

import com.xq.learn.common.CommonResponse;
import com.xq.learn.order.service.InventoryService;
import com.xq.learn.order.util.JsonUtil;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 库存服务调用fallback
 * @author xiaoqiang
 * @date 2020/3/22 15:19
 */
@Service
public class InventoryServiceFallback implements InventoryService
{
    private static final Logger logger = LoggerFactory.getLogger(InventoryServiceFallback.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${stock.routing.key}")
    private String routingKey;

    @Value("${stock.exchange}")
    private String exchange;

    @Override
    public CommonResponse reduceStock(String goodId, int num)
    {
        logger.warn("Failed to reduce stock, good({}), num({})", goodId, num);
        // 调用库存服务失败，将信息写入rabbitmq，系统内部封装好的错误，不会进入该fallback方法。
        CorrelationData data = new CorrelationData();
        data.setId(UUID.randomUUID().toString().concat(":").concat(goodId));
        Map<String, Object> message = new LinkedHashMap<>();
        message.put("good_id", goodId);
        message.put("num", num);
        data.setReturnedMessage(new Message(JsonUtil.getJson(message).getBytes(StandardCharsets.UTF_8), null));
        rabbitTemplate.convertAndSend(exchange, routingKey, message, data);
        CommonResponse response = new CommonResponse(true, "Succeed to send stock info to rabbit.");
        logger.info("Succeed to send stock info to rabbit. ");
        return response;
    }
}
