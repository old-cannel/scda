package com.scda.security.service;

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

        // 这里查询该账户认证是否过期，登录的时候默认是没有过期的
        boolean credentialsNonExpired = true;

        // todo 这个地方可以从数据库获取
        String password = "123456";
        // 这里查询该账户是否过期
        boolean accountNonExpired = true;
        // 这里查询该账户被启用
        boolean enabled = true;
        // 查询该账户是否被锁定，假设没有被锁定
        boolean accountNonLocked = true;
        //角色
        List<GrantedAuthority> grantedAuthorityList=AuthorityUtils.commaSeparatedStringToAuthorityList("user,finance");
        // 关于密码的加密，应该是在创建用户的时候进行的，这里仅仅是举例模拟
        return new User(username, password,
                enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked,grantedAuthorityList
                );
    }
}