package com.learning.eureka.ribbon.controller;

import com.learning.eureka.ribbon.service.LoginService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tangcheng on 7/9/2017.
 */
@ApiModel("Login")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation("login")
    @PostMapping(value = "/login")
    public String hi(@RequestParam String username, @RequestParam String password) {
        return loginService.login(username, password);
    }

}
