package com.scda.security.config;

import com.scda.security.entrypoint.AjaxAuthenticationEntryPoint;
import com.scda.security.filter.JwtAuthenticationTokenFilter;
import com.scda.security.handler.AjaxAccessDeniedHandler;
import com.scda.security.handler.AjaxAuthenticationFailureHandler;
import com.scda.security.handler.AjaxAuthenticationSuccessHandler;
import com.scda.security.handler.AjaxLogoutSuccessHandler;
import com.scda.security.vote.MyRoleVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: liuqi
 * @Date: 2019/1/10 16:50
 * @Description: web安全配置
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //请求头令牌标识
    public static String TOKEN_HEADER = "Authorization";

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
//                jwt模式没有跨域问题
                .csrf().disable()
                // 禁用headers缓存
                .headers().cacheControl()
                .and().and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                //自定义登录url
                .formLogin().loginProcessingUrl(LOGIN_URL)
                .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler).permitAll()
                .and()
//                自定义退出url
                .logout().logoutUrl(LOGOUT_URL)
                .logoutSuccessHandler(logoutSuccessHandler).permitAll()
                .and()
                //用来解决匿名用户访问无权限资源时的异常
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                // 用来解决认证过的用户访问无权限资源时的异常
                .accessDeniedHandler(accessDeniedHandler)
                .and()
//                资源权限配置
                .authorizeRequests()
//                这里配置不需要权限的资源
                .antMatchers("/goods/**").permitAll()
                //其他任何请求都要权限验证
                .anyRequest().authenticated()
                .accessDecisionManager(accessDecisionManager())

//                .antMatchers("/order/**").hasRole("user")
        ;

    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters
                = Arrays.asList(
                new WebExpressionVoter(),
//                自定义的权限校验
                new MyRoleVoter(),
                new RoleVoter(),
                new AuthenticatedVoter());
        return new UnanimousBased(decisionVoters);
    }

}
