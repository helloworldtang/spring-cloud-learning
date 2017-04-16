package com.learning.eureka.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by tang.cheng on 2017/3/23.
 */
@Service
public class ComputeService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ComputeService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;

    @PostConstruct
    public void testApi() {
        try {
            LOGGER.info("{}", serviceUrl());
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public String serviceUrl() {
        String serviceName = "COMPUTE-SERVICE";
        List<ServiceInstance> list = discoveryClient.getInstances(serviceName);
        if (list != null && list.size() > 0) {
            return String.valueOf(list.get(0).getUri());
        }
        throw new IllegalArgumentException("service no found:" + serviceName);
    }


    @HystrixCommand(fallbackMethod = "addServiceFallback")
    public String addService(Integer a, Integer b) {
        //消费COMPUTE-SERVICE的add服务
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://COMPUTE-SERVICE/add?a={a}&b={b}",
                String.class,
                a, b);
        return responseEntity.getBody();
    }

    public String addServiceFallback(Integer a, Integer b) {
        return "error:" + a + "," + b;
    }

}
