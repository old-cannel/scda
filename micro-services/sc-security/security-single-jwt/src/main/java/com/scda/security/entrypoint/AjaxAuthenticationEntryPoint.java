package com.scda.security.entrypoint;

import com.alibaba.fastjson.JSONObject;
import com.scda.common.response.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: liuqi
 * @Date: 2019/1/10 20:07
 * @Description: 需要登录
 */
@Component
@Slf4j
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        log.info("请求需要登录，原因：{}",e.getMessage());
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.getWriter().write(JSONObject.toJSONString(ResponseVo.noLogin()));
    }
}