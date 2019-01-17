package com.scda.security.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.scda.common.db.entity.BaseFieldVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Auther: liuqi
 * @Date: 2019/1/17 15:58
 * @Description: 菜单
 */
@TableName("sys_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenuVo extends BaseFieldVo {
    //编码
    private String code;

    @NotBlank(message = "名称不能为空")
    @Length(max = 30, message = "名称长度不能大于30")
    private String name;

    @Length(max = 256, message = "备注长度不能大于256")
    private String remark;

    @Length(max = 256, message = "链接长度不能大于256")
    private String url;

    @NotBlank(message = "菜单深度不能为空")
    private String deep;
    //上级编码
    private String supCode;
    //上级名称
    private String supName;
    //上级编码集合
    private String supCodes;
    //上级名称集合
    private String supNames;
    //排序
    private int sort;
    //类型,0菜单,1api
    @NotBlank(message = "菜单类型不能为空")
    private String type;
}
