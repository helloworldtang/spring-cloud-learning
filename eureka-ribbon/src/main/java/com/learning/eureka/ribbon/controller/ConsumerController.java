package com.learning.eureka.ribbon.controller;

import com.learning.eureka.ribbon.service.ComputeService;
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
@RestController
public class ConsumerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private ComputeService computeService;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(HttpServletRequest request,
                      @RequestParam(value = "a") Integer a,
                      @RequestParam(value = "b") Integer b) {
        LOGGER.info("from:{}", request.getRequestURL());
        return computeService.addService(a, b);
    }

}
