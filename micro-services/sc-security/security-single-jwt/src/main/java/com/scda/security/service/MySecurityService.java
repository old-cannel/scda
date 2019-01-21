package com.scda.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scda.common.utils.RedisUtil;
import com.scda.security.mapper.MySecurityMapper;
import com.scda.security.vo.SysRoleMenuVo;
import com.scda.security.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: liuqi
 * @Date: 2019/1/18 14:10
 * @Description: 安全服务
 */
@Service
@Transactional(readOnly = true)
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
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", userName);
        return mySecurityMapper.selectOne(queryWrapper);
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
}
