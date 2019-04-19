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
 * @Description: 操作
 */
@TableName("sys_operation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysOperationVo extends BaseFieldVo {
    //编码
    private String code;

    @NotBlank(message = "名称不能为空")
    @Length(max = 100, message = "名称长度不能大于100")
    private String name;

    //菜单id
    @NotBlank(message = "菜单不能为空")
    private String menuId;


}
