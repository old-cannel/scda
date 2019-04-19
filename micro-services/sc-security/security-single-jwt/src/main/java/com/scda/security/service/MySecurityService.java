package com.scda.security.service;

import com.alibaba.fastjson.JSONObject;
import com.scda.common.utils.RedisUtil;
import com.scda.common.utils.TokenJwtRedisUtil;
import com.scda.security.mapper.MySecurityMapper;
import com.scda.security.util.MySecurityContextHolder;
import com.scda.security.vo.SysRoleApiVo;
import com.scda.security.vo.SysRoleMenuOperationVo;
import com.scda.security.vo.SysUserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: liuqi
 * @Date: 2019/1/18 14:10
 * @Description: 安全服务
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MySecurityService {
    @Autowired
    private MySecurityMapper mySecurityMapper;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 系统角色对应api => key
     */
    private static final String SYS_ROLE_API = "sys_role_api";


    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     */
    public SysUserVo loadByUserName(String userName) {

        //获取用户信息
        SysUserVo sysUserVo = mySecurityMapper.loadByUserName(userName);
        if (sysUserVo == null || StringUtils.isBlank(sysUserVo.getId())) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        String roleIds = selectRoleIds(sysUserVo.getId());
        if (StringUtils.isNotBlank(roleIds)) {
            //角色
            List<GrantedAuthority> grantedAuthorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(roleIds);
            sysUserVo.setAuthorities(grantedAuthorityList);
        }
        return sysUserVo;
    }

    /**
     * 获取用户角色
     *
     * @param userId
     * @return多个角色英文逗号隔开
     */
    public String selectRoleIds(String userId) {
        return mySecurityMapper.selectRoleIds(userId);
    }

    /**
     * 读取系统所有的角色对应api信息
     *
     * @return
     */
    @Cacheable(value = SYS_ROLE_API, key = "T(String).valueOf(#root.target)")
    public List<SysRoleApiVo> loadRoleApi() {
        return mySecurityMapper.loadRoleApi();
    }

    /**
     * 更新系统所有的角色对应api信息
     *
     * @return
     */
    @CachePut(value = SYS_ROLE_API, key = "T(String).valueOf(#root.target)")
    public List<SysRoleApiVo> updateRoleApi() {
        return mySecurityMapper.loadRoleApi();
    }

    /**
     * 刷新用户信息
     */
    public void refreshUser() {
        if (!redisUtil.set(TokenJwtRedisUtil.TOKEN_KEY + MySecurityContextHolder.getToken(), JSONObject.parseObject(JSONObject.toJSONString(loadByUserName(MySecurityContextHolder.getUserName()))))) {
            throw new RuntimeException("更新用户信息失败，请刷新重试");
        }
    }

    /**
     * 用户对应的菜单或操作
     *
     * @return {"menus":[{"id":"","name":"","url",""},...],"operationCodes":["1","2"...]}
     */
    public SysRoleMenuOperationVo getMenus() {
        SysRoleMenuOperationVo sysRoleMenuOperationVo = new SysRoleMenuOperationVo();
//        超级管理员
        if ("1".equals(MySecurityContextHolder.getUser().getAdminFlag())) {
            //所有操作码
            sysRoleMenuOperationVo.setOperationCodes(mySecurityMapper.loadRoleOperation());
            //所有菜单
            sysRoleMenuOperationVo.setMenus(mySecurityMapper.loadMenus());
        } else {
            List<String> roleIds = MySecurityContextHolder.getRoleIds();
            if (roleIds != null && !roleIds.isEmpty()) {
                //用户角色id对应操作码
                sysRoleMenuOperationVo.setOperationCodes(mySecurityMapper.loadRoleOperationByRoleIds(roleIds));
                //用户角色id对应菜单
                sysRoleMenuOperationVo.setMenus(mySecurityMapper.loadMenusByRoleIds(roleIds));
            }
        }

        return sysRoleMenuOperationVo;
    }
}
