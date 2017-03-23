package com.learning.feign.config;

import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tang.cheng on 2017/3/23.
 */
@EnableHystrixDashboard //http://ip:${server.port}/hystrix
@Configuration
public class DashboardConfig {
}
