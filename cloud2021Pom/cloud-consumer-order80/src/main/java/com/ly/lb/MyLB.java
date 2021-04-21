package com.ly.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Echo
 * @Date: 2021/4/21-14:53
 */
@Component
public class MyLB implements LoadBalancer{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current = this.atomicInteger.get();
            //如果current为最大值，则重新计算，否则加1(防止越界)
            next = current >= 2147483647 ? 0 : current + 1;  //Integer.MAX_VALUE

        }while(!this.atomicInteger.compareAndSet(current,next));
        System.out.println("*********第"+next+"次访问*********");
        return next;
    }

    @Override
    public ServiceInstance getInstances(List<ServiceInstance> serviceInstances) {
        if(serviceInstances.size() <= 0){
            return null;
        }
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
