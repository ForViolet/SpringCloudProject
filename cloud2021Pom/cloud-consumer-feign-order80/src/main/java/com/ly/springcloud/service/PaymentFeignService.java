package com.ly.springcloud.service;

import com.ly.entity.CommonResult;
import com.ly.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: Echo
 * @Date: 2021/4/21-20:03
 */
@Component
@FeignClient(value = "cloud-payment-service")  //指定服务提供方名称
public interface PaymentFeignService {

    @GetMapping("/provider/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/provider/payment/feign/timeout")
    String paymentFeignTimeout();
}
