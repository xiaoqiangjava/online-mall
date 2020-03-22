package com.xq.learn.order;

import java.io.Serializable;

/**
 * @author xiaoqiang
 * @date 2020/3/22 12:46
 */
public class Order implements Serializable
{
    private String id;

    private String goodId;

    private Double price;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getGoodId()
    {
        return goodId;
    }

    public void setGoodId(String goodId)
    {
        this.goodId = goodId;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }
}
