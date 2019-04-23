package com.scda.security.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scda.common.utils.TokenJwtRedisUtil;
import com.scda.security.vo.MyAuthenticationDetails;
import com.scda.security.vo.SysUserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @Auther: liuqi
 * @Date: 2019/4/23 14:01
 * @Description: 测试环境注入会话
 */
@Component
public class TestAuthenticationSuccess {
    @Autowired
    private TokenJwtRedisUtil tokenJwtRedisUtil;

    public void token() {
        SysUserVo user = (SysUserVo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//      token里面去掉敏感字段
        user.setPassword(null);
        setContext(tokenJwtRedisUtil.createToken(JSONObject.parseObject(JSONObject.toJSONString(user))));
    }

    private void setContext(String jwtToken) {

        if (StringUtils.isNotBlank(jwtToken) && !"null".equals(jwtToken)) {
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
                MyAuthenticationDetails authenticationDetail = new MyAuthenticationDetails(new MockHttpServletRequest());
                authenticationDetail.setSysUserVo(info.toJavaObject(SysUserVo.class));
                authenticationDetail.setJwtToken(jwtToken);
                authentication.setDetails(authenticationDetail);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
    }
}
