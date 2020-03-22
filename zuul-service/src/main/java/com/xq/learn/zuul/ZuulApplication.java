package com.xq.learn.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author xiaoqiang
 * @date 2020/3/22 11:57
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
