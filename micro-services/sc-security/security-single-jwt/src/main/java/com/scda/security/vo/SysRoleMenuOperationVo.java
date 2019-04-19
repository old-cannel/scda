package com.scda.security.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: liuqi
 * @Date: 2019/4/18 16:27
 * @Description: 角色操作
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleMenuOperationVo implements Serializable  {
    //用户可显示的菜单
    private List<SysMenuVo> menus;
    //用户可进行的操作
    private List<String> operationCodes;


}
