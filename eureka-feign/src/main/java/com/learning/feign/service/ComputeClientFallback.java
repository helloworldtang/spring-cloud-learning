package com.learning.feign.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by tang.cheng on 2017/3/23.
 */
@Service
public class ComputeClientFallback implements ComputeClient {
    @Override
    public String add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        return "error:" + a + "," + b;
    }
}
