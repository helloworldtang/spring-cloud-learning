package com.learning.eureka.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by tang.cheng on 2017/3/23.
 */
@Service
public class ComputeService {

    @Autowired
    private RestTemplate restTemplate;

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
