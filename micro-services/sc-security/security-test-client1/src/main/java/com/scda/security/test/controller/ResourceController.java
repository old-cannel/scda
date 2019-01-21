package com.scda.security.test.controller;

import com.scda.common.response.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuqi
 * @Date: 2019/1/10 16:07
 * @Description:
 */
@RestController
public class ResourceController {
    @GetMapping("/goods/{id}")
    public ResponseVo good(@PathVariable("id") String id){
        return ResponseVo.success("商品id:"+id);
    }
    @GetMapping("/order/{id}")
    public ResponseVo order(@PathVariable("id") String id){
        return ResponseVo.success("订单id:"+id);
    }
}
