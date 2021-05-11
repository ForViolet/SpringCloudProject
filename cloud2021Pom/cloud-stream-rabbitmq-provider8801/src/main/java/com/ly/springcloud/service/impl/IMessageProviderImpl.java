package com.ly.springcloud.service.impl;

import com.ly.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author: Echo
 * @Date: 2021/5/8-15:32
 */
@EnableBinding(Source.class)   //表示绑定了Source通道，消息推送通道
public class IMessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;  //注入消息发送通道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("=====serial:"+serial);
        return null;
    }
}
