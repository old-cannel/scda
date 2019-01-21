package com.scda.security.controller;

import com.scda.common.response.ResponseVo;
import com.scda.common.utils.TokenJwtRedisUtil;
import com.scda.security.config.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: liuqi
 * @Date: 2019/1/16 14:34
 * @Description: token刷新服务
 */
@RestController
public class TokenRefreshController {
    @Autowired
    private TokenJwtRedisUtil tokenJwtRedisUtil;

    /**
     * 基于jwt的token刷新服务
     *
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/token/refresh")
    public ResponseVo refreshToken(HttpServletRequest httpServletRequest) {
        String jwtToken = httpServletRequest.getHeader(WebSecurityConfig.TOKEN_HEADER);
        return ResponseVo.success(tokenJwtRedisUtil.refreshToken(jwtToken));

    }
}
