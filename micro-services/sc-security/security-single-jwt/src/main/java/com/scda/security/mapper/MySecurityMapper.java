package com.scda.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scda.security.vo.SysMenuVo;
import com.scda.security.vo.SysRoleApiVo;
import com.scda.security.vo.SysUserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.access.method.P;
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
     *
     * @param userId
     * @return
     */
    @Select("SELECT\n" +
            "\tGROUP_CONCAT( sr.id ) AS roleIds \n" +
            "FROM\n" +
            "\tsys_user_role sur\n" +
            "\tINNER JOIN sys_role sr ON sr.del_flag = '0' \n" +
            "\tAND sr.id = sur.role_id  \n" +
            "\tAND sr.status='1' \n" +
            "WHERE\n" +
            "\tsur.del_flag = '0'\n" +
            "\tAND sur.user_id = #{userId}")
    String selectRoleIds(@Param("userId") String userId);

    /**
     * 角色对应api
     *
     * @return
     */
    @Select("select \n" +
            "*\n" +
            "from \n" +
            "v_api_role")
    List<SysRoleApiVo> loadRoleApi();

    /**
     * 所有操作
     *
     * @return
     */
    @Select("SELECT \n" +
            "code\n" +
            "from sys_operation so\n" +
            "where so.del_flag='0'")
    List<String> loadRoleOperation();


    /**
     * 某些角色对应操作
     *
     * @return
     */
    @Select({"<script>",
            "SELECT\n" +
                    "\toperation_code \n" +
                    "FROM\n" +
                    "\tv_user_operation WHERE\n" +
                    "\trole_id IN (" +
                    "<foreach collection='roleIds' item='id'  separator=','>" +
                    "#{id}" +
                    "</foreach>)",
            "</script>"})
    List<String> loadRoleOperationByRoleIds(@Param("roleIds") List<String> roleIds);


    /**
     * 所有可用菜单
     * @return
     */
    @Select("SELECT\n" +
            "\tid,\n" +
            "\tNAME,\n" +
            "\turl \n" +
            "FROM\n" +
            "\tsys_menu \n" +
            "WHERE\n" +
            "\tdel_flag = '0' \n" +
            "\tAND show_flag = '1' order by sort asc")
    List<SysMenuVo> loadMenus();

    /**
     * 用户角色对应菜单
     * @param roleIds
     * @return
     */
    @Select({"<script>",
            "SELECT \n" +
                    "id,name,url\n" +
                    "from v_role_menu where" +
                    "\trole_id IN (" +
                    "<foreach collection='roleIds' item='id'  separator=','>" +
                    "#{id}" +
                    "</foreach>) ",
            "</script>"})
    List<SysMenuVo> loadMenusByRoleIds(@Param("roleIds") List<String> roleIds);
}
