package com.scda.security.controller;

import com.scda.common.response.ResponseVo;
import com.scda.common.utils.TokenJwtRedisUtil;
import com.scda.security.config.WebSecurityConfig;
import com.scda.security.service.MySecurityService;
import com.scda.security.util.MySecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: liuqi
 * @Date: 2019/1/16 14:34
 * @Description: token服务
 */
@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private TokenJwtRedisUtil tokenJwtRedisUtil;
    @Autowired
    private MySecurityService mySecurityService;

    /**
     * 基于jwt的token刷新服务
     *
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/refresh")
    public ResponseVo refreshToken(HttpServletRequest httpServletRequest) {
        String jwtToken = httpServletRequest.getHeader(WebSecurityConfig.TOKEN_HEADER);
        return ResponseVo.success(tokenJwtRedisUtil.refreshToken(jwtToken));

    }

    /**
     * 给前端用户信息
     *
     * @return
     */
    @PostMapping("/user")
    public ResponseVo getUser() {
        return ResponseVo.success(MySecurityContextHolder.getUser());
    }

    /**
     * 刷新用户信息
     *
     * @return
     */
    @PostMapping("/user/refresh")
    public ResponseVo refreshUser() {
        mySecurityService.refreshUser();
        return ResponseVo.success("刷新成功");
    }

    /**
     * 刷新系统权限
     *
     * @return
     */
    @PostMapping("/auth")
    public ResponseVo refreshRoleApi() {
        mySecurityService.updateRoleApi();
        return ResponseVo.success("刷新成功");
    }


    /**
     * 用户可用的菜单和操作
     *
     * @return
     */
    @PostMapping("/user/menus")
    public ResponseVo getMenus() {
        return ResponseVo.success(mySecurityService.getMenus());
    }
}
