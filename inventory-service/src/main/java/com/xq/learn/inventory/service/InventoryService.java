package com.xq.learn.inventory.service;

/**
 * @author xiaoqiang
 * @date 2020/3/22 15:02
 */
public interface InventoryService
{
    /**
     * 给指定的商品扣减库存
     * @param goodId 商品id
     * @param num 需要扣减的库存数量
     */
    void reduceStock(String goodId, int num);
}
