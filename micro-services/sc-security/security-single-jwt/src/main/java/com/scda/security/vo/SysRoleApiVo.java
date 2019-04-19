package com.scda.security.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: liuqi
 * @Date: 2019/4/18 16:27
 * @Description: 角色api
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleApiVo implements Serializable {
    //角色id
    private String roleId;
    //角色名称
    private String roleName;
    //菜单id
    private String menuId;
    //菜单名称
    private String menuName;
    //操作id
    private String operationId;
    //操作名称
    private String operationName;
    //操作编码
    private String operationCode;
    //apiid
    private String apiId;
    //api路径
    private String path;
    //api请求方法
    private String requestMethod;
}
