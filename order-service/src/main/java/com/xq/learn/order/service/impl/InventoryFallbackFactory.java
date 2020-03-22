package com.xq.learn.order.service.impl;

import com.xq.learn.order.service.InventoryService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义FallbackFactory，打印日志异常信息, 或者自定ErrorDecoder来处理异常
 * @author xiaoqiang
 * @date 2020/3/22 23:50
 */
@Component
public class InventoryFallbackFactory implements FallbackFactory<InventoryService>
{
    private static final Logger logger = LoggerFactory.getLogger(InventoryFallbackFactory.class);

    // 这里需要注入InventoryService的实现类，不能是接口
    @Autowired
    private InventoryServiceFallback inventoryService;

    @Override
    public InventoryService create(Throwable throwable)
    {
        logger.error("Service invoke error: ", throwable);
        return inventoryService;
    }
}
