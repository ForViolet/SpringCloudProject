package com.ly.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Echo
 * @Date: 2021/4/26-19:38
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;  //读取到GitHub中的config.info代表成功

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }
}
