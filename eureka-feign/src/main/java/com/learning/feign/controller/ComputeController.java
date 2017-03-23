package com.learning.feign.controller;

import com.learning.feign.service.ComputeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tang.cheng on 2017/3/23.
 */
@RestController
public class ComputeController {
    @Autowired
    private ComputeClient computeClient;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@RequestParam(value = "a", defaultValue = "10") Integer a,
                      @RequestParam(value = "b", defaultValue = "20") Integer b) {
        return computeClient.add(a, b);
    }

}
