package com.xq.learn.order.service;

import com.xq.learn.common.CommonResponse;

/**
 * 订单相关service接口
 * @author xiaoqiang
 * @date 2020/3/22 15:13
 */
public interface OrderService
{
    /**
     * 支付订单
     * @param orderId 订单id
     * @return response
     */
    CommonResponse payOrder(String orderId);
}
