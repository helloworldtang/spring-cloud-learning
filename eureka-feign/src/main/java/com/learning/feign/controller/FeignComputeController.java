package com.learning.feign.controller;

import com.learning.feign.service.ComputeClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tang.cheng on 2017/3/23.
 */
@Api(tags = "计算服务-feign")
@RestController
public class FeignComputeController {

    @Autowired
    private ComputeClient computeClient;

    @ApiOperation("加法运算")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@RequestParam(value = "a", defaultValue = "10") Integer a,
                      @RequestParam(value = "b", defaultValue = "20") Integer b) {
        return computeClient.add(a, b);
    }

}
