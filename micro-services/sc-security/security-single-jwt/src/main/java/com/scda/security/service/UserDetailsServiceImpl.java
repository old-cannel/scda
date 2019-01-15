package com.scda.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @Auther: liuqi
 * @Date: 2019/1/10 20:51
 * @Description: 根据用户名读取用户信息，权限信息；
 * todo 这里可以根据项目实际情况使用
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService, Serializable {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("登陆用户名：{}", username);
        // 这里可以根据用户名到数据库中查询用户,获得数据库中得到的密码（这里不进行查询操作，使用固定代码）
        String password = "123456";
        // 这里查询该账户是否过期，这里使用固定代码，假设没有过期
        boolean accountNonExpired = true;
        // 这里查询该账户被删除，假设没有被删除
        boolean enabled = true;
        // 这里查询该账户认证是否过期，假设没有过期
        boolean credentialsNonExpired = true;
        // 查询该账户是否被锁定，假设没有被锁定
        boolean accountNonLocked = true;

        // 关于密码的加密，应该是在创建用户的时候进行的，这里仅仅是举例模拟
        return new User(username, password,
                enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked,
                AuthorityUtils.commaSeparatedStringToAuthorityList("user,finance"));
    }
}