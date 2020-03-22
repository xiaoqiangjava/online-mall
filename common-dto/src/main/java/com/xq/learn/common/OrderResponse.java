package com.xq.learn.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.util.List;

/**
 * 订单响应实体类
 * @author xiaoqiang
 * @date 2020/3/22 13:30
 */
@JsonPropertyOrder("is_success")
public class OrderResponse<T> implements Serializable
{
    private boolean isSuccess;

    private String message;

    private List<T> orders;

    private T order;

    public OrderResponse()
    {

    }

    public OrderResponse(boolean isSuccess)
    {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess()
    {
        return isSuccess;
    }

    @JsonProperty("is_success")
    public void setSuccess(boolean success)
    {
        isSuccess = success;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public List<T> getOrders()
    {
        return orders;
    }

    public void setOrders(List<T> orders)
    {
        this.orders = orders;
    }

    public T getOrder()
    {
        return order;
    }

    public void setOrder(T order)
    {
        this.order = order;
    }
}
