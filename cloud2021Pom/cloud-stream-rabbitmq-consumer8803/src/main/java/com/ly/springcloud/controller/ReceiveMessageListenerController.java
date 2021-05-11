package com.ly.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

/**
 * @Author: Echo
 * @Date: 2021/5/10-15:03
 */
@Controller
@EnableBinding(Sink.class)  //表示绑定了Sink接口，消息接收通道
public class ReceiveMessageListenerController {
    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)  //监听队列，用于消费者队列的消息接收
    public void getMessage(Message<String> message){
        System.out.println("Consumer2,----->获取到消息:"+message.getPayload()+"\t port: "+serverPort);
    }
}
