package com.ly.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Echo
 * @Date: 2021/4/21-20:00
 */
@SpringBootApplication
@EnableFeignClients  //开启feign，初始化FeignClient的配置和动态执行client的请求。
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
    }
}
