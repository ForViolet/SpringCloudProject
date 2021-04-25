package com.ly.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Author: Echo
 * @Date: 2021/4/23-10:28
 */
//新建类实现PaymentHystrixService统一为接口中方法进行服务降级处理
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService--fallback--paymentInfo_OK,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "PaymentFallbackService--paymentInfo_Timeout,o(╥﹏╥)o";
    }
}
