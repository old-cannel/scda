package com.scda.security.service;

import com.scda.security.vo.SysUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("登陆用户名：{}", username);

        //这个地方可以从数据库获取
        String password = "123456";
        // 这里查询该账户是否过期
        int accountNonExpired = 1;
        // 这里查询该账户被启用
        int enabled = 1;

        int nonLocked=1;

        //角色
        List<GrantedAuthority> grantedAuthorityList=AuthorityUtils.commaSeparatedStringToAuthorityList("user,finance");

        SysUserVo sysUserVo=new SysUserVo();
        sysUserVo.setUserName(username);
        sysUserVo.setPassword(password);
        sysUserVo.setEnabled(enabled);
        sysUserVo.setNonExpired(accountNonExpired);
        sysUserVo.setNonLocked(nonLocked);
        sysUserVo.setAuthorities(grantedAuthorityList);

        /**系统扩展字段**/
        sysUserVo.setWorkNum("002");
        sysUserVo.setCode("20190117000001");
        sysUserVo.setFullName("系统管理员");

        return sysUserVo;

    }
}