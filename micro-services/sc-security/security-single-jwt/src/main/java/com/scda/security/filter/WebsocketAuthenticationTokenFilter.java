package com.scda.security.filter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scda.common.utils.TokenJwtRedisUtil;
import com.scda.security.config.WebSecurityConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: liuqi
 * @Date: 2019/3/13
 * @Description: websocket-》token
 * OncePerRequestFilter 可以保证每个请求进来一次
 */
@Component
public class WebsocketAuthenticationTokenFilter extends OncePerRequestFilter {
    public final static String TOKEN_KEY="sec-websocket-protocol";
    @Autowired
    private TokenJwtRedisUtil tokenJwtRedisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = httpServletRequest.getHeader(TOKEN_KEY);
        //存在认证的token，解析出来放到UsernamePasswordAuthenticationToken，这样就可以直接进行权限认证了
        if (StringUtils.isNotBlank(jwtToken)) {
            //解析验证token,获取用户名和权限
            JSONObject info = tokenJwtRedisUtil.decodeVerifyToken(jwtToken);
            //判断是否存在权限
            StringBuffer stringBuffer = new StringBuffer();;
            if (info.containsKey("authorities")) {
                JSONArray jsonArray = JSONObject.parseArray(info.getString("authorities"));
                if (jsonArray != null && jsonArray.size() > 0) {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject o = (JSONObject) jsonArray.get(i);
                        stringBuffer.append("ROLE_").append(o.getString("authority")).append(",");

                    }
                }
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            }
            //存在用户名
            if (info.containsKey("username")) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(info.getString("username"), null, AuthorityUtils.commaSeparatedStringToAuthorityList(stringBuffer.toString()));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
