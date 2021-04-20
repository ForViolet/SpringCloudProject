package com.ly.controller;

import com.ly.entity.CommonResult;
import com.ly.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Echo
 * @Date: 2021/4/16-15:39
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {

    //public static final String PAYMENT_URL = "http://localhost:8001"; //提供者url地址
    public static final String PAYMENT_URL = "http://cloud-payment-service";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/payment/create")
    public CommonResult<Payment> create(Payment payment){

        return restTemplate.postForObject(
                PAYMENT_URL+"/provider/payment/create",  //URL地址
                payment,  //要发送的对象
                CommonResult.class  //要返回的响应类型
                );
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(
                PAYMENT_URL+"/provider/payment/get/"+id,CommonResult.class);
    }
}
