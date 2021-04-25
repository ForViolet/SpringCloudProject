package com.ly.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Echo
 * @Date: 2021/4/22-15:40
 */
@Service
public class PaymentService {
    //正常访问
    public String paymentInfo_OK(Integer id){
        return "当前线程:"+Thread.currentThread().getName()+" OK,id:"+id+"\t"+"(*^▽^*)";
    }

    //添加该注解实现断路器功能，service方法对应服务发生异常时，自动跳到fallbackMethod指定方法执行
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            //设置自身超时时间调用峰值为3秒，超时了交给兜底方法处理，服务降级fallback
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_Timeout(Integer id){
        int timeNumber = 3;  //峰值为5秒，响应需要3秒
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "当前线程:"+Thread.currentThread().getName()+" Timeout,id:"+id+"\t"+"￣へ￣"+" 耗时:"+timeNumber;
    }
    //兜底方法
    public String paymentInfo_TimeoutHandler(Integer id){
        return "当前线程:"+Thread.currentThread().getName()+" ,系统繁忙，请稍后再试,id:"+id;
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数阈值
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60") //允许失败率
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id <0 ){
            throw new RuntimeException("------id不能为负数------");
        }
        String serialNumber = IdUtil.simpleUUID();  //使用hutool工具类，等同于Random.UUID（）
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号:"+serialNumber;
    }
    //兜底方法
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数，请重新操作,id:"+id;
    }
}
