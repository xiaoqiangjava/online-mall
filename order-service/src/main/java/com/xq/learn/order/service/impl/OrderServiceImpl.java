package com.xq.learn.order.service.impl;

import com.xq.learn.common.CommonResponse;
import com.xq.learn.order.service.InventoryService;
import com.xq.learn.order.service.OrderService;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaoqiang
 * @date 2020/3/22 15:16
 */
@Service
public class OrderServiceImpl implements OrderService
{
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private InventoryService inventoryService;

    @Override
    public CommonResponse payOrder(String orderId)
    {
        // 1. 根据订单id查询订单信息
        String goodId = UUID.randomUUID().toString();
        int num = 1;
        // 2. 支付订单
        logger.info("Succeed to pay order({}). ", orderId);
        // 3. 调用库存服务修改库存
        CommonResponse response = inventoryService.reduceStock(orderId, num);
        if (!response.isSuccess())
        {
            logger.error("Failed to reduce stock, good({}), stock({})", goodId, num);
        }
        logger.info("Succeed to reduce stock, good({})", goodId);
        // 4. 调用仓库服务发货

        return response;
    }
}
