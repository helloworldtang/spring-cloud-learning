package com.learning.eureka.ribbon.controller;

import com.learning.eureka.ribbon.service.ComputeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tang.cheng on 2017/3/20.
 */
@Api(tags = "计算服务-ribbon")
@RestController
public class RibbonConsumerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RibbonConsumerController.class);

    @Autowired
    private ComputeService computeService;

    @ApiOperation("加法运算")
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(HttpServletRequest request,
                      @RequestParam(value = "a") Integer a,
                      @RequestParam(value = "b") Integer b) {
        LOGGER.info("from:{}", request.getRequestURL());
        return computeService.addService(a, b);
    }

}
