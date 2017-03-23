package com.learning.eureka.ribbon.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by tang.cheng on 2017/3/20.
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced//开启均衡负载功能
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
