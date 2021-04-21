package com.ly;

import com.lys.myrole.MySelfRole;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @Author: Echo
 * @Date: 2021/4/16-15:35
 */
@SpringBootApplication
@EnableEurekaClient
//name为指定服务名，configuration为指定服务使用自定义配置
@RibbonClient(name = "cloud-payment-service",configuration = MySelfRole.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
