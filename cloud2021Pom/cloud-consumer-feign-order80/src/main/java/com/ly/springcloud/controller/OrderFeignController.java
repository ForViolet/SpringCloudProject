package com.ly.springcloud.controller;

import com.ly.entity.CommonResult;
import com.ly.entity.Payment;
import com.ly.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Echo
 * @Date: 2021/4/21-21:11
 */
@RestController
@RequestMapping("/consumer")
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //openfeign-ribbon 客户端默认等待时间1秒钟
        return paymentFeignService.paymentFeignTimeout();
    }
}
