package com.learning.eureka.ribbon.controller;

import com.learning.eureka.ribbon.service.HiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tangcheng on 7/9/2017.
 */
@Api(tags = "Say Hi!")
@RestController
public class HiController {

    @Autowired
    private HiService hiService;

    @ApiOperation("hi，Today!")
    @GetMapping("hi")
    public String hi(@RequestParam(value = "msg", defaultValue = "today!") String msg) {
        return hiService.echo(msg);
    }
}
