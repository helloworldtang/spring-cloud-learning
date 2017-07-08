package com.learning.eureka.ribbon.controller;

import com.learning.eureka.ribbon.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tangcheng on 7/9/2017.
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login")
    public String hi(@RequestParam String username, @RequestParam String password) {
        return loginService.login(username, password);
    }

}
