package com.scda.security.utils;

import com.scda.common.test.ApplicationTest;
import com.scda.security.test.Test1BootStrap;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.ServletContext;

/**
 * @Auther: liuqi
 * @Date: 2019/1/19 09:41
 * @Description: url匹配测试
 */
@SpringBootTest(classes = Test1BootStrap.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class TestAntPathRequestMatcher extends ApplicationTest {
    @Autowired
    private ServletContext servletContext;

    @Test
    public void testUrlMatcher() {
//        请求方法（可选）
        String httpMethod = HttpMethod.POST.name();
//        模式化表达式
        String pattern = "/order/*/detail";
//        大小写敏感（可选），默认是true
        boolean caseSensitive = true;
        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders.post("/order/14234343/detail");

        AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(pattern, httpMethod, caseSensitive);
        Assert.assertEquals(true, antPathRequestMatcher.matches(requestBuilders.buildRequest(servletContext)));
    }
}
