package com.ly.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Echo
 * @Date: 2021/4/16-15:52
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    //@LoadBalanced  //开启负载均衡功能
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
