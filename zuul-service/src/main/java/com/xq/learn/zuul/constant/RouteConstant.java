package com.xq.learn.zuul.constant;

/**
 * 配置服务路由
 * @author xiaoqiang
 * @date 2020/3/22 18:57
 */
public class RouteConstant
{
    /**
     * 默认的fallback
     */
    public static final String DEFAULT = "*";

    /**
     * inventory-service 库存服务路由
     */
    public static final String INVENTORY_SERVICE = "inventory-service";

    /**
     * order-service 订单服务路由
     */
    public static final String ORDER_SERVICE = "order-service";


}
