package com.ly.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Echo
 * @Date: 2021/4/25-20:48
 */
@Configuration
public class GateWayConfig {
    /**
     * 配置了一个id为Path_route_ly的路由规则,
     * 当访问http://localhost:9527/guoneinews时，会自动转发到地址http://news.baidu.com/guonei
     * */
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        routes.route("path_route_news",  //路由ID
                r -> r.path("/guonei")  //断言，路径相匹配的进行路由
                        .uri("http://news.baidu.com/guonei")); //匹配后提供服务的路由地址

        routes.route("path_route_movie",
                r -> r.path("/movie")
                        .uri("https://www.bilibili.com/movie/"));

        return routes.build();
    }
}
