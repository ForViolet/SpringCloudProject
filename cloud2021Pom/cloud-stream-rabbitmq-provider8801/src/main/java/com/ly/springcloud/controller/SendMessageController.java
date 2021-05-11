package com.ly.springcloud.controller;

import com.ly.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Echo
 * @Date: 2021/5/8-15:48
 */
@RestController
public class SendMessageController {

    @Resource
    private IMessageProvider provider;

    @GetMapping("/sendMessage")
    public String sendMessage(){
        return provider.send();
    }
}
