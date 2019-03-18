package com.learning.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * spring-cloud-learning
 * <p>
 * 没有fallback的报错：
 * {
 * "timestamp": 1552926478805,
 * "status": 500,
 * "error": "Internal Server Error",
 * "exception": "java.lang.RuntimeException",
 * "message": "com.netflix.client.ClientException: Load balancer does not have available server for client: compute-service",
 * "path": "/add"
 * }
 * 添加fallback的报错信息：
 *{
 *   "msg": "microserver error"
 * }
 * @author tangcheng
 * @date 3/18/2019 11:54 PM
 */
@Slf4j
@Component
public class ZuulFallback implements FallbackProvider {

    @Override
    public ClientHttpResponse fallbackResponse(Throwable cause) {
        log.error("fallbackResponse(Throwable cause) {}", cause.getMessage(), cause);
        return fallbackResponse();
    }

    @Override
    public String getRoute() {
        return "*";//可以配置指定的路由，值为serverId，如 feign-consumer
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        log.warn("ClientHttpResponse fallbackResponse()");
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                //定义自己的错误
                JSONObject errorTips = new JSONObject();
                errorTips.put("msg", "microserver error");
                return new ByteArrayInputStream((errorTips.toJSONString()).getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }


}
