package com.scda.common.valid;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: liuqi
 * @Date: 2019/1/9 15:19
 * @Description: 自定义格式校验-必须是数字
 */
@Slf4j
public class IsNumberValidator implements ConstraintValidator<IsNumber, String> {
    static Pattern pattern = Pattern.compile("[0-9]*");
    @Override
    public void initialize(IsNumber isNumber) {
        log.debug("数字验证初始化");
    }

    /**
     *
     * @param value 前端过来的值
     * @param constraintValidatorContext 校验器的校验环境
     * @return true 成功；false 失败
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isBlank(value)){
            return false;
        }
        Matcher isNum = pattern.matcher(value);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
