package com.ly.service;

import com.ly.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    //增加记录
    int create(Payment payment);

    //按id查询记录
    Payment getPaymentById(@Param("id") Long id);
}
