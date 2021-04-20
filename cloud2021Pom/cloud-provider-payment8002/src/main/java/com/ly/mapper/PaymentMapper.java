package com.ly.mapper;

import com.ly.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentMapper {

    //增加记录
    int create(Payment payment);

    //按id查询记录
    Payment getPaymentById(@Param("id") Long id);
}
