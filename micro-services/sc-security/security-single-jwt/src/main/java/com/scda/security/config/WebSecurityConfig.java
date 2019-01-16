package com.scda.security.config;

import com.scda.security.entrypoint.AjaxAuthenticationEntryPoint;
import com.scda.security.filter.JwtAuthenticationTokenFilter;
import com.scda.security.handler.AjaxAccessDeniedHandler;
import com.scda.security.handler.AjaxAuthenticationFailureHandler;
import com.scda.security.handler.AjaxAuthenticationSuccessHandler;
import com.scda.security.handler.AjaxLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Auther: liuqi
 * @Date: 2019/1/10 16:50
 * @Description: web安全配置
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //请求头令牌标识
    public static String TOKEN_HEADER="Authorization";

    //登录的url
    public static String LOGIN_URL = "/loginjwt";
    //退出登录的url
    public static String LOGOUT_URL = "/logoutjwt";

    @Autowired
    private AjaxLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AjaxAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AjaxAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AjaxAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private AjaxAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //取消session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/goods/**").permitAll()
                .antMatchers("/order/**").hasRole("user")
                //其他任何请求都要认证授权
                .anyRequest().authenticated()
                .and()
                //自定义登录url
                .formLogin().loginProcessingUrl(LOGIN_URL)
                .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler).permitAll()
                .and()
//                自定义退出url
                .logout().logoutUrl(LOGOUT_URL)
                .logoutSuccessHandler(logoutSuccessHandler).permitAll()
                .and()
                .csrf().disable();

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
//用来解决匿名用户访问无权限资源时的异常
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
// 用来解决认证过的用户访问无权限资源时的异常
                .accessDeniedHandler(accessDeniedHandler);
        // 禁用headers缓存
        http.headers().cacheControl();

    }

}
