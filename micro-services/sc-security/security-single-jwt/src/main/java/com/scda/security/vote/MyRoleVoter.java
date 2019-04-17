package com.scda.security.vote;

import com.scda.security.service.MySecurityService;
import com.scda.security.vo.SysRoleMenuVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import util.MySecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * @Auther: liuqi
 * @Date: 2019/1/17 10:26
 * @Description: 自定义权限验证逻辑-从redis读取所有的资源权限关系来进行验证
 */
@Component
public class MyRoleVoter implements AccessDecisionVoter<Object> {
    @Autowired
    private MySecurityService mySecurityService;

    private static String PREFIX = "ROLE_";

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object o, Collection<ConfigAttribute> attributes) {
//        数据库内角色权限
        List<SysRoleMenuVo> roleMenuVoList = mySecurityService.loadAllRroleMenuIds();
        Collection<GrantedAuthority> userRoleIds = (Collection<GrantedAuthority>) authentication.getAuthorities();
        return checkRole(userRoleIds, getNeedRoleId(((FilterInvocation) o).getRequest(), roleMenuVoList)) ? 1 : -1;
    }

    /**
     * 根据当前request获取需要的权限
     *
     * @param request
     * @param roleMenuVoList
     * @return 为空时则说明不需要权限
     */
    private String getNeedRoleId(HttpServletRequest request, List<SysRoleMenuVo> roleMenuVoList) {
        if (roleMenuVoList == null || roleMenuVoList.size() < 1) {
            return null;
        }

        for (SysRoleMenuVo srmv : roleMenuVoList) {
            if (StringUtils.isNotBlank(srmv.getUrl())) {
                boolean b = new AntPathRequestMatcher(srmv.getUrl()).matches(request);
                if (b) {
                    return srmv.getRoleId();
                }
            }

        }
        return null;
    }

    /**
     * 权限检查
     *
     * @param userRoleIds
     * @param needRoleId
     * @return true 有权限；false 没有权限
     */
    private boolean checkRole(Collection<GrantedAuthority> userRoleIds, String needRoleId) {
        if (StringUtils.isBlank(needRoleId)) {
            return true;
        }
        if (userRoleIds != null && userRoleIds.size() > 0) {
            for (GrantedAuthority ga : userRoleIds) {
                if ((PREFIX + needRoleId).equals(ga.getAuthority())) {
                    return true;
                }
            }
        }
        //        是超级管理员
        if (StringUtils.isNotBlank(MySecurityContextHolder.getUser().getAdminFlag()) && "1".equals(MySecurityContextHolder.getUser().getAdminFlag())) {
            return true;
        }
        return false;
    }


}
