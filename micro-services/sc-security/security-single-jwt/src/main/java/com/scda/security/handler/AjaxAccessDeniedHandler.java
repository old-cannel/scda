package com.scda.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.scda.common.response.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: liuqi
 * @Date: 2019/1/10 20:07
 * @Description: 拒绝访问，没有权限
 */
@Component
@Slf4j
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        log.error("访问拒绝，异常：{}", accessDeniedException.getMessage());
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSONObject.toJSONString(ResponseVo.accessDenied(accessDeniedException.getMessage())));
    }
}