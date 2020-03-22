package com.xq.lear.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 注册中心，使用Eureka实现服务注册和发现
 * @author xiaoqiang
 * @date 2020/3/21 21:54
 */
@SpringBootApplication
@EnableEurekaServer
public class CenterApplication extends WebSecurityConfigurerAdapter
{
    public static void main(String[] args)
    {
        SpringApplication.run(CenterApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable();
    }
}
