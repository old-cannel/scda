package com.scda.common.valid;

import com.scda.common.response.ResponseVo;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: liuqi
 * @Date: 2019/4/17 10:25
 * @Description: vo验证统一处理
 */
public class ValidHandle {

    /**
     * 将验证错误信息统一返回
     * @param bindingResult
     * @return
     */
    public static ResponseVo validFail(BindingResult bindingResult){
        List<String> results=new ArrayList<>();
        bindingResult.getAllErrors().forEach(error -> results.add(error.getDefaultMessage()));
        return ResponseVo.validFail(results);
    }
}
