package com.scda.security.filter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scda.common.utils.TokenJwtRedisUtil;
import com.scda.security.config.WebSecurityConfig;
import com.scda.security.vo.MyAuthenticationDetails;
import com.scda.security.vo.SysUserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: liuqi
 * @Date: 2019/1/15 19:36
 * @Description: jwt-》token 将前台的jwt拿出来转换成token
 * OncePerRequestFilter 可以保证每个请求进来一次
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private TokenJwtRedisUtil tokenJwtRedisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = httpServletRequest.getHeader(WebSecurityConfig.TOKEN_HEADER);
//        存在认证的token，解析出来放到UsernamePasswordAuthenticationToken，这样就可以直接进行权限认证了
        if (StringUtils.isNotBlank(jwtToken)&&!"null".equals(jwtToken)) {
            //解析验证token,获取用户名和权限
            JSONObject info = tokenJwtRedisUtil.decodeVerifyToken(jwtToken);
            //判断是否存在权限
            StringBuffer stringBuffer = new StringBuffer();
            ;
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
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                MyAuthenticationDetails authenticationDetail = new MyAuthenticationDetails(httpServletRequest);
                authenticationDetail.setSysUserVo(info.toJavaObject(SysUserVo.class));
                authenticationDetail.setJwtToken(jwtToken);
                authentication.setDetails(authenticationDetail);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
