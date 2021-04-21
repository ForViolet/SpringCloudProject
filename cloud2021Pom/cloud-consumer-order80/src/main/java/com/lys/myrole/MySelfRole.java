package com.lys.myrole;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Echo
 * @Date: 2021/4/21-10:37
 */
@Configuration
public class MySelfRole {
    @Bean
    public IRule getIRule(){
        return new RandomRule();  //设置负载规则为随机
    }
}
