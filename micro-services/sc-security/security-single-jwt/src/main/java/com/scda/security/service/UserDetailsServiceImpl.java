package com.scda.security.service;

import com.scda.security.vo.SysUserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: liuqi
 * @Date: 2019/1/10 20:51
 * @Description: 根据用户名读取用户信息，权限信息；
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService, Serializable {
    @Autowired
    private MySecurityService mySecurityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("登陆用户名：{}", username);
//获取用户信息
        SysUserVo sysUserVo = mySecurityService.loadByUserName(username);
        if (sysUserVo == null || StringUtils.isBlank(sysUserVo.getId())) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        String roleIds = mySecurityService.loadByUserId(sysUserVo.getId());
        if (StringUtils.isNotBlank(roleIds)) {
            //角色
            List<GrantedAuthority> grantedAuthorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(roleIds);
            sysUserVo.setAuthorities(grantedAuthorityList);
        }

        return sysUserVo;

    }


}