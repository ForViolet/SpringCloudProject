package com.ly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//传给前端的json封装体
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;  //状态码
    private String message;  //消息
    private T data;  //对应类型的json数据

    //提供一个两个参数的构造方法
    public CommonResult(Integer code, String message){
        this(code, message, null);
    }
}
