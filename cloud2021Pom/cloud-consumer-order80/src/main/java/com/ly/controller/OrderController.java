package com.ly.controller;

import com.ly.entity.CommonResult;
import com.ly.entity.Payment;
import com.ly.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

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

    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

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

    @GetMapping("/payment/getEntity/{id}")
    public CommonResult getForEntity(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/provider/payment/get/" + id, CommonResult.class);
        //获取状态码并判断是否为2XX成功
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();  //返回body
        }else{
            return new CommonResult<>(444,"操作失败");
        }
    }

    @PostMapping("/payment/createEntity")
    public CommonResult<Payment> createEntity(Payment payment){
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/provider/payment/create", payment, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<>(444,"操作失败");
        }
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        //获取cloud-payment-service服务的所有具体实例
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if(instances == null || instances.size() <= 0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.getInstances(instances);
        URI uri = serviceInstance.getUri();
        System.out.println(uri);

        return restTemplate.getForObject(uri+"/provider/payment/lb",String.class);
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return restTemplate.getForObject("http://localhost:8001"+"/provider/payment/zipkin/",String.class);
    }

}
