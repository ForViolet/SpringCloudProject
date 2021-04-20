package com.ly.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author: Echo
 * @Date: 2021/4/20-16:49
 */
@RestController
public class PaymentController {
    @Value("${server.port}") //获取端口号
    private String serverPort;

    @RequestMapping("/payment/consul")
    public String consul(){
        return "springCloud with consul:"+serverPort+"\t"+ UUID.randomUUID().toString();
    }
}
