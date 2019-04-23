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

    @Length(max = 256, message = "链接长度不能大于256")
    private String url;

    //排序
    private int sort;

    @NotBlank(message = "显示标识不能为空")
    private String showFlag;

    @Length(max = 256, message = "备注长度不能大于256")
    private String remark;


    //上级编码
    private String supCode;
    //上级名称
    private String supName;
    //上级编码集合
    private String supCodes;
    //上级名称集合
    private String supNames;

    @NotBlank(message = "菜单深度不能为空")
    private String deep;


}
