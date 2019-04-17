package com.scda.security.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scda.common.response.ResponseVo;
import com.scda.common.utils.RedisUtil;
import com.scda.common.utils.TokenJwtRedisUtil;
import com.scda.security.mapper.MySecurityMapper;
import com.scda.security.vo.SysMenuVo;
import com.scda.security.vo.SysRoleMenuVo;
import com.scda.security.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.MySecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: liuqi
 * @Date: 2019/1/18 14:10
 * @Description: 安全服务
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class MySecurityService {
    @Autowired
    private MySecurityMapper mySecurityMapper;
    @Autowired
    private RedisUtil redisUtil;

    private static final String ROLE_MENU_KEY = "role_menu";

    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     */
    public SysUserVo loadByUserName(String userName) {
        return mySecurityMapper.loadByUserName(userName);
    }

    /**
     * 获取用户角色
     *
     * @param userId
     * @return多个角色英文逗号隔开
     */
    public String loadByUserId(String userId) {
        return mySecurityMapper.selectUrls(userId);
    }

    /**
     * 读取系统所有的角色id对应的菜单url
     *
     * @return
     * key roleId
     * value url
     */
    @Cacheable(value = "scda", key = "#root.methodName")
    public List<SysRoleMenuVo> loadAllRroleMenuIds() {
        List<SysRoleMenuVo> roleMenuVos = (List<SysRoleMenuVo>) redisUtil.get(ROLE_MENU_KEY);
        if (roleMenuVos == null || roleMenuVos.size() <= 0) {
            roleMenuVos = mySecurityMapper.loadAllRroleMenuIds();
            if (roleMenuVos != null && roleMenuVos.size() > 0) {
                redisUtil.set(ROLE_MENU_KEY, roleMenuVos);
            }
        }
        return roleMenuVos;
    }

    /**
     * 刷新用户信息
     */
    public void refreshUser(){
        if (!redisUtil.set(TokenJwtRedisUtil.TOKEN_KEY+ MySecurityContextHolder.getToken(), JSONObject.parseObject(JSONObject.toJSONString(loadByUserName(MySecurityContextHolder.getUserName()))))) {
            throw new RuntimeException("更新用户信息失败，请刷新重试");
        }
    }

    /**
     * 用户对应的菜单或功能
     * @return
     */
    public List<SysMenuVo> getMenus() {
//        超级管理员
        if("1".equals(MySecurityContextHolder.getUser().getAdminFlag())){
            //所有菜单
            return mySecurityMapper.getAllMenus();
        }
        List<String> roleIds=MySecurityContextHolder.getRoleIds();
        if(roleIds==null||roleIds.isEmpty()&&!"1".equals(MySecurityContextHolder.getUser().getAdminFlag())){
            return new ArrayList<>();
        }
        return mySecurityMapper.getMenusByUserRoleIds(roleIds);
    }
}
