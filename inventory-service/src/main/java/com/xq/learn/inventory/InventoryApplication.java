package com.xq.learn.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 库存服务
 * @author xiaoqiang
 * @date 2020/3/22 14:44
 */
@SpringBootApplication
@EnableDiscoveryClient
public class InventoryApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(InventoryApplication.class, args);
    }
}
