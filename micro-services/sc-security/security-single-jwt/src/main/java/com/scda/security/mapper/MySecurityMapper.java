package com.scda.security.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.scda.security.vo.SysMenuVo;
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

    @Select("SELECT\n" +
            "\tub.*,\n" +
            "\tsu.work_num,\n" +
            "\tsu.user_header,\n" +
            "\tsu.full_name,\n" +
            "\tsu.id_num,\n" +
            "\tsu.phone_call,\n" +
            "\tsu.mobile_num,\n" +
            "\tsu.email,\n" +
            "\tsu.src_org_code,\n" +
            "\tsu.position,\n" +
            "\tsu.entry_time,\n" +
            "\tsu.remark\n" +
            "FROM\n" +
            "\tuser_base ub\n" +
            "\tINNER JOIN sys_user su ON su.user_code = ub.CODE \n" +
            "\tAND su.del_flag = '0' \n" +
            "WHERE\n" +
            "\tub.del_flag = '0' \n" +
            "\tand ub.user_name=#{userName}")
    SysUserVo loadByUserName(String userName);
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
            "\tAND sr.id = sur.role_id  \n" +
            "\tAND sr.status='1' \n"+
            "WHERE\n" +
            "\tsur.del_flag = '0'\n" +
            "\tAND sur.user_id = #{userId}")
    String selectUrls(@Param("userId") String userId);

    /**
     * 菜单对应的角色
     * @return
     */
    @Select("SELECT\n" +
            "\tsrm.role_id,\n" +
            "\tsrm.menu_id \n" +
            "FROM\n" +
            "\tsys_role sr\n" +
            "\tINNER JOIN sys_role_menu srm ON srm.del_flag = '0' \n" +
            "\tAND srm.role_id = sr.id\n" +
            "\tINNER JOIN sys_menu sm ON sm.del_flag = '0'  \n" +
            "WHERE\n" +
            "\tsr.del_flag = '0' \n" +
            "\tAND sr.STATUS = '1'")
    List<SysRoleMenuVo> loadAllRroleMenuIds();

    @Select({"<script>","SELECT\n" +
            "\tsm.* \n" +
            "FROM\n" +
            "\tsys_role_menu srm\n" +
            "\tINNER JOIN sys_menu sm ON sm.del_flag = '0' \n" +
            "WHERE\n" +
            "\tsrm.del_flag = '0'\n" +
            "\tand srm.role_id in"+ " <foreach collection='roleIds' item='id' open='(' end=')' separator=','>"
            + "#{id}"
            + "</foreach>)\n","</script>"})
    List<SysMenuVo> getMenusByUserRoleIds(@Param("roleIds") List<String> roleIds);

    /**
     * 系统所有菜单
     * @return
     */
    @Select("SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\tsys_menu\n" +
            "WHERE\n" +
            "\tdel_flag = '0'")
    List<SysMenuVo> getAllMenus();
}
