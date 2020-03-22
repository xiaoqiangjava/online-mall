package com.xq.learn.order.service.impl;

import com.xq.learn.common.CommonResponse;
import com.xq.learn.order.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Override
    public CommonResponse reduceStock(String goodId, int num)
    {
        logger.warn("Failed to reduce stock, good({}), num({})", goodId, num);
        // 调用库存服务失败，将信息写入rabbitmq
        CommonResponse response = new CommonResponse(true, "Succeed to send stock info to rabbit.");
        logger.info("Succeed to send stock info to rabbit. ");
        return response;
    }
}
