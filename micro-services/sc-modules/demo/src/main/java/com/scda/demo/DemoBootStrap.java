package com.scda.demo;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Auther: liuqi
 * @Date: 2018/11/14 14:29
 * @Description:
 */
@SpringCloudApplication
@MapperScan(basePackages = "com.scda.*.mapper*")
@ComponentScan("com.scda")
@EnableTransactionManagement
public class DemoBootStrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(DemoBootStrap.class).web(true).run(args);
    }

}
