package com.scda.security.vo;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: liuqi
 * @Date: 2019/4/2 10:43
 * @Description:
 */
public class MyAuthenticationDetails extends WebAuthenticationDetails {
    private SysUserVo sysUserVo;

    private String jwtToken;
    /**
     * Records the remote address and will also set the session Id if a session already
     * exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    public MyAuthenticationDetails(HttpServletRequest request) {
        super(request);
    }

    public SysUserVo getSysUserVo() {
        return sysUserVo;
    }

    public void setSysUserVo(SysUserVo sysUserVo) {
        this.sysUserVo = sysUserVo;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
