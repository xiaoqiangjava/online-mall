package com.xq.learn.order.controller;

import com.xq.learn.common.CommonResponse;
import com.xq.learn.common.OrderResponse;
import com.xq.learn.order.Order;
import com.xq.learn.order.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaoqiang
 * @date 2020/3/22 12:41
 */
@RestController
@RequestMapping("/v1/mall")
public class OrderController
{
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderResponse<Order> list()
    {
        OrderResponse<Order> response = new OrderResponse<>(true);
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setGoodId(UUID.randomUUID().toString());
        order.setPrice(98.00);
        orders.add(order);

        order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setGoodId(UUID.randomUUID().toString());
        order.setPrice(100.00);
        orders.add(order);

        response.setOrders(orders);
        logger.info("Succeed to list orders.");

        return response;
    }

    @RequestMapping(value = "orders/{order_id}/pay", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommonResponse payOrder(@PathVariable("order_id") String orderId)
    {
        // 支付订单
        CommonResponse response = orderService.payOrder(orderId);
        logger.info("Succeed to pay order. ");
        return response;
    }
}
