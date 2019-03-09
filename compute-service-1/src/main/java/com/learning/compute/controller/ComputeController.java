package com.learning.compute.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tang.cheng on 2017/3/20.
 */
@Api(tags = "计算服务")
@Slf4j
@RestController
public class ComputeController {
    @Autowired
    private Registration registration;

    @ApiOperation("加法运算")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add(@RequestParam(defaultValue = "1") Integer a, @RequestParam(defaultValue = "2") Integer b) {
        Integer r = a + b;
        log.info("/add, host:" + registration.getHost() + ", service_id:" + registration.getServiceId() + ", result:" + r);
        return r;
    }

    @ApiOperation("减法运算")
    @RequestMapping(value = "/minus", method = RequestMethod.GET)
    public Integer minus(@RequestParam(defaultValue = "3") Integer a, @RequestParam(defaultValue = "1") Integer b) {
        Integer r = a - b;
        log.info("/add, host:" + registration.getHost() + ", service_id:" + registration.getServiceId() + ", result:" + r);
        return r;
    }

}
