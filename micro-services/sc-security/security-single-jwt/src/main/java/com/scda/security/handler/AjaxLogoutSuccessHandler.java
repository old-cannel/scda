package com.scda.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.scda.common.response.ResponseVo;
import com.scda.common.utils.TokenJwtRedisUtil;
import com.scda.security.config.WebSecurityConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: liuqi
 * @Date: 2019/1/10 20:07
 * @Description: 退出成功
 */
@Component
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    private TokenJwtRedisUtil tokenJwtRedisUtil;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String jwtToken = request.getHeader(WebSecurityConfig.TOKEN_HEADER);
//        存在认证的token，解析出来放到UsernamePasswordAuthenticationToken，这样就可以直接进行权限认证了
        if (StringUtils.isNotBlank(jwtToken)) {
            tokenJwtRedisUtil.deleteToken(jwtToken);
        }
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSONObject.toJSONString(ResponseVo.success("退出成功")));
    }
}