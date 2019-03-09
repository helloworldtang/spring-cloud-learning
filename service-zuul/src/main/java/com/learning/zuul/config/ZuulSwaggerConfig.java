/**
 * @Auther: cheng.tang
 * @Date: 2019/3/9
 * @Description:
 */
package com.learning.zuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: cheng.tang
 * @Date: 2019/3/9
 * @Description:
 */
@Configuration
@EnableSwagger2
public class ZuulSwaggerConfig {

    private final ZuulProperties zuulProperties;

    @Autowired
    public ZuulSwaggerConfig(ZuulProperties zuulProperties) {
        this.zuulProperties = zuulProperties;
    }

    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        return () -> {
            List<SwaggerResource> resources = new ArrayList<>();
            zuulProperties.getRoutes().values().forEach(route -> {
                        String path = zuulProperties.getPrefix();
                        if (zuulProperties.isStripPrefix()) {
                            path = path + "/" + route.getId();
                        }
                        resources.add(createResource(route.getServiceId(), path));
                    }
            );
            return resources;
        };
    }

    private SwaggerResource createResource(String serviceId, String path) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(serviceId);
        /**
         * 每个分组的的URL。
         * 在本例中，存放swagger数据的json地址为：
         * http://localhost:8711/api/feign/v2/api-docs
         * http://localhost:8711/api/ribbon/v2/api-docs
         */
        swaggerResource.setLocation(path + "/v2/api-docs");
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }

}
