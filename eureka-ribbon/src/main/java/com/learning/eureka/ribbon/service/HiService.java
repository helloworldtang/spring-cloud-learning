package com.learning.eureka.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by tangcheng on 7/9/2017.
 */
@Service
public class HiService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String echo(String msg) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?msg={msg}", String.class, msg);
    }

    public String hiError(String msg) {
        return "hiError:" + msg;
    }

}
