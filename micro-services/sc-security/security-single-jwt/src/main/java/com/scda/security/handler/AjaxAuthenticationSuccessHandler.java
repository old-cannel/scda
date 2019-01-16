package com.scda.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.scda.common.response.ResponseVo;
import com.scda.common.utils.JwtUtils;
import com.scda.common.utils.TokenJwtRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: liuqi
 * @Date: 2019/1/10 20:07
 * @Description: 登录成功
 */
@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private TokenJwtRedisUtil tokenJwtRedisUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        User user = (User) authentication.getPrincipal();
        String jwtToken = tokenJwtRedisUtil.createToken(JSONObject.parseObject(JSONObject.toJSONString(user)));
        response.getWriter().write(JSONObject.toJSONString(ResponseVo.success(jwtToken)));
    }
}