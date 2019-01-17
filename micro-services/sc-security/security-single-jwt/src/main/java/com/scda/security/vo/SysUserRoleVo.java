package com.scda.security.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.scda.common.db.entity.BaseFieldVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: liuqi
 * @Date: 2019/1/17 15:58
 * @Description: 用户角色
 */
@TableName("sys_user_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRoleVo extends BaseFieldVo {
    //用户id
    private String userId;
    //角色id
    private String roleId;

}
