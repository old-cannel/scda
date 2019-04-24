package com.scda.security.feign;

import com.scda.security.config.WebSecurityConfig;
import com.scda.security.util.MySecurityContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: liuqi
 * @Date: 2019/4/23 17:37
 * @Description: feign请求拦截追加会话信息
 * 多个服务之间直接会话传递
 */
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = MySecurityContextHolder.getToken();
        if (StringUtils.isNotBlank(token) && !"null".equals(token)) {
            requestTemplate.header(WebSecurityConfig.TOKEN_HEADER, token);
        }
        ;
    }
}
