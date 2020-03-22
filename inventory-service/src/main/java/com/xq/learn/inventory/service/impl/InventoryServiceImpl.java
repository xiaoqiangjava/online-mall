package com.xq.learn.inventory.service.impl;

import com.xq.learn.exception.MallException;
import com.xq.learn.inventory.service.InventoryService;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author xiaoqiang
 * @date 2020/3/22 15:03
 */
@Service
public class InventoryServiceImpl implements InventoryService
{
    private static final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

    private static final String LOCK_KEY = "Lock-key";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void reduceStock(String goodId, int num)
    {
        // 请求唯一标识，确保redis释放锁时只释放了自己添加的锁
        String traceId = UUID.randomUUID().toString();
        String key = LOCK_KEY + goodId;
        try
        {
            // 扣减库存，使用redis实现分布式锁
            Boolean flag = redisTemplate.opsForValue().setIfAbsent(key, traceId, 10000, TimeUnit.MILLISECONDS);
            if (null != flag && flag)
            {
                // 从redis中获取当前库存
                String stock = redisTemplate.opsForValue().get(goodId);
                logger.info("good({}) stock({})", goodId, stock);
                stock = null == stock ? "0" : stock;
                int stockNum = Integer.valueOf(stock);
                if (stockNum <= 0)
                {
                    throw new MallException("mall.0002", "Inventory shortage! ");
                }
                redisTemplate.opsForValue().set(goodId, String.valueOf(--stockNum));

            }
            else
            {
                throw new MallException("mall.0001", "Please try again later.");
            }
        }
        finally
        {
            if (StringUtils.equals(traceId, redisTemplate.opsForValue().get(key)))
            {
                redisTemplate.delete(key);
            }
        }
    }
}
