package com.learning.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by tang.cheng on 2017/3/20.
 */
@EnableEurekaServer //启动一个服务注册中心,对外提供服务注册以及发现功能.可以联合多个服务作为集群
@SpringBootApplication
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
