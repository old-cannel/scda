package com.scda;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Auther: liuqi
 * @Date: 2018/11/9 16:43
 * @Description:
 */
@SpringCloudApplication
@ComponentScan(basePackages={"com.scda"},includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION)})
@MapperScan(basePackages = "com.scda")
@EnableTransactionManagement
@EnableEurekaClient
public class DemoBootStrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(DemoBootStrap.class).web(true).run(args);
    }

}
