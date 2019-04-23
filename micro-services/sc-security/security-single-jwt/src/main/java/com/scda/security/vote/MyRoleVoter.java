package com.scda.security.vote;

import com.scda.security.service.MySecurityService;
import com.scda.security.util.MySecurityContextHolder;
import com.scda.security.vo.SysRoleApiVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Auther: liuqi
 * @Date: 2019/1/17 10:26
 * @Description: 自定义权限验证逻辑-从redis读取所有的资源权限关系来进行验证
 * 1.用户--》角色--》菜单--》操作--》api
 * 2.客户端访问api，系统加载角色对应api（如果改集合为空，则验证通过）
 * 3.系统根据访问api找到需要的角色集合
 * 4.根据上一步的角色与用户的角色比较
 *  4.1 如果需要角色为空则通过
 *  4.2 如果是超级管理员则通过
 *  4.2 如果需要角色再用户内则通过
 *
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
        List<SysRoleApiVo> roleMenuVoList = mySecurityService.loadRoleApi();
        Collection<GrantedAuthority> userRoleIds = (Collection<GrantedAuthority>) authentication.getAuthorities();
        return checkRole(userRoleIds, getNeedRoleId(((FilterInvocation) o).getRequest(), roleMenuVoList)) ? 1 : -1;
    }

    /**
     * 根据当前request获取需要的权限
     *
     * @param request
     * @param roleApiVos
     * @return 为空时则说明不需要权限
     */
    private List<String> getNeedRoleId(HttpServletRequest request, List<SysRoleApiVo> roleApiVos) {

        if (roleApiVos == null || roleApiVos.size() < 1) {
            return null;
        }
        List<String> list=new ArrayList<>();
        for (SysRoleApiVo srmv : roleApiVos) {
            if (StringUtils.isNotBlank(srmv.getPath())) {
                boolean b = new AntPathRequestMatcher(srmv.getPath(), srmv.getRequestMethod().toUpperCase()).matches(request);
                if (b) {
                    list.add(srmv.getRoleId());
                }
            }

        }
        if(list!=null&&!list.isEmpty()){
            return list;
        }
        return null;
    }

    /**
     * 权限检查
     *
     * @param userRoleIds
     * @param needRoleIds
     * @return true 有权限；false 没有权限
     */
    private boolean checkRole(Collection<GrantedAuthority> userRoleIds, List<String> needRoleIds) {
        if (needRoleIds==null) {
            return true;
        }
        if (userRoleIds != null && userRoleIds.size() > 0) {
            for (GrantedAuthority ga : userRoleIds) {
               if(valid(ga.getAuthority(),needRoleIds)){
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

    /**
     * 验证用户角色是否在需要的角色集合里面
     * @param authority
     * @param needRoleIds
     * @return
     */
    private boolean valid(String authority, List<String> needRoleIds) {
        for(String needRoleId:needRoleIds){
            if ((PREFIX + needRoleId).equals(authority)) {
                return true;
            }
        }
        return false;
    }


}
