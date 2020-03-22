package com.xq.learn.sleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @author xiaoqiang
 * @date 2020/3/22 0:29
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
public class SleuthApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SleuthApplication.class, args);
    }
}
