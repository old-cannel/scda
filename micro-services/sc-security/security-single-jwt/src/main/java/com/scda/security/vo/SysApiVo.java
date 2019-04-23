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
 * @Description: api
 */
@TableName("sys_api")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysApiVo extends BaseFieldVo {
    //编码
    private String code;

    @NotBlank(message = "名称不能为空")
    @Length(max = 100, message = "名称长度不能大于100")
    private String name;

    //api地址路径
    @NotBlank(message = "api地址路径不能为空")
    @Length(max = 200, message = "名称长度不能大于200")
    private String path;

    //请求方法
    @NotBlank(message = "请求方法不能为空")
    @Length(max = 10, message = "名称长度不能大于10")
    private String requestMethod;


}
