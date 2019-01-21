package com.scda.security.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.scda.security.vo.SysRoleMenuVo;
import com.scda.security.vo.SysUserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: liuqi
 * @Date: 2019/1/18 14:13
 * @Description:
 */
@Repository
public interface MySecurityMapper extends BaseMapper<SysUserVo> {
    /**
     * 用户角色ids
     * @param userId
     * @return
     */
    @Select("SELECT\n" +
            "\tGROUP_CONCAT( sr.id ) AS roleIds \n" +
            "FROM\n" +
            "\tsys_user_role sur\n" +
            "\tINNER JOIN sys_role sr ON sr.del_flag = '0' \n" +
            "\tAND sr.id = sur.role_id \n" +
            "WHERE\n" +
            "\tsur.del_flag = '0'\n" +
            "\tAND sur.user_id = #{userId}")
    String selectUrls(@Param("userId") String userId);

    @Select("SELECT\n" +
            "\tsrm.role_id,sm.url\n" +
            "FROM\n" +
            "\tsys_role_menu srm \n" +
            "\tINNER JOIN sys_menu sm ON sm.del_flag = '0' \n" +
            "\tAND sm.STATUS = '1' \n" +
            "\tAND sm.id = srm.menu_id \n" +
            "WHERE\n" +
            "\tsrm.del_flag = '0' ")
    List<SysRoleMenuVo> loadAllRroleMenuIds();
}
