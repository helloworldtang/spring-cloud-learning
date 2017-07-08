package com.learning.eureka.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

/**
 * Created by tangcheng on 7/9/2017.
 */
@Service
public class LoginService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "loginError")
    public String login(String username, String password) {
        //RestTemplate请求中的服务器必须大写，否则会请求不到
        return restTemplate.getForObject("http://SERVICE-HI/login?username={username}&password={password}",
                String.class,
                username,
                password);
    }

    public String loginError(String username, String password) {
        return MessageFormat.format("ribbon-loginError:{0},{1}", username, password);
    }
}
