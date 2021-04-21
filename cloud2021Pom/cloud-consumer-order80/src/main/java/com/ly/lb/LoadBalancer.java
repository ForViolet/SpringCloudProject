package com.ly.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Author: Echo
 * @Date: 2021/4/21-14:51
 */
public interface LoadBalancer {
    //传入具体实例集合，返回选中实例
    ServiceInstance getInstances(List<ServiceInstance> serviceInstances);
}
