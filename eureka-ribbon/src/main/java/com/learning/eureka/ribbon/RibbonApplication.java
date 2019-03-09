package com.learning.eureka.ribbon;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Created by tang.cheng on 2017/3/20.
 */
@EnableSwagger2Doc
@SpringBootApplication
@EnableDiscoveryClient//添加发现服务的功能
@EnableCircuitBreaker //开启断路器功能
@EnableHystrixDashboard
public class RibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(RibbonApplication.class, args);
    }
}
