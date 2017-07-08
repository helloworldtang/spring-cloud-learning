package com.learning.client.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tangcheng on 7/9/2017.
 */
@RestController
public class HiController {

    @RequestMapping("hi")
    public String hi(@RequestParam String msg) {
        return "echo:" + msg;
    }

}
