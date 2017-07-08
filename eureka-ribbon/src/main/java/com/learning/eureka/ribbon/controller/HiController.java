package com.learning.eureka.ribbon.controller;

import com.learning.eureka.ribbon.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tangcheng on 7/9/2017.
 */
@RestController
public class HiController {

    @Autowired
    private HiService hiService;

    @RequestMapping("hi")
    public String hi(@RequestParam String msg) {
        return hiService.echo(msg);
    }
}
