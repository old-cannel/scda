package com.scda.security.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.scda.common.db.entity.BaseFieldVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Auther: liuqi
 * @Date: 2019/1/17 15:58
 * @Description: 角色菜单
 */
@TableName("sys_role_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleMenuVo extends BaseFieldVo {


    //角色id
    private String roleId;

    //菜单id
    private String menuId;


    //菜单id
    @NotBlank(message = "操作不能为空")
    private String operationId;


}
