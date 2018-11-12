package com.scda;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Auther: liuqi
 * @Date: 2018/11/9 16:43
 * @Description:
 */
@SpringCloudApplication()
@EnableEurekaClient
public class DemoBootStrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(DemoBootStrap.class).web(true).run(args);
    }

}
