package com.scda.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.scda.common.response.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: liuqi
 * @Date: 2019/1/10 20:07
 * @Description: 登录失败
 */
@Component
@Slf4j
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
       log.error("用户登录失败，异常：{}", exception.getMessage());
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSONObject.toJSONString(ResponseVo.fail(exception.getMessage())));
    }
}
