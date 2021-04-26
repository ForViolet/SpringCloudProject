package com.ly.springcloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @Author: Echo
 * @Date: 2021/4/26-14:57
 */
@Component
public class MyLogGateWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("进入MyLogGateWayFilter  "+new Date().getTime());

        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if(uname == null){
            System.out.println("用户名为null，非法用户");
            //设置响应不被接受
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        //返回值为过滤器的优先级，越小优先级越高(最小为Integer.MIN_VALUE，最大为Integer.MAX_VALUE)
        return 0;
    }
}
