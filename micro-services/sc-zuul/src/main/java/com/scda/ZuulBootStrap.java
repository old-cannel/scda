package com.scda;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;

/**
 * @Auther: liuqi
 * @Date: 2018/11/9 14:13
 * @Description:
 */
@SpringCloudApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulBootStrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulBootStrap.class).web(true).run(args);
    }

    @Bean
    @RefreshScope
    @ConfigurationProperties("zuul")
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }
}
