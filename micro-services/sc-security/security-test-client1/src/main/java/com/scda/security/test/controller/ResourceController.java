package com.scda.security.test.controller;

import com.scda.business.common.vo.demo.DemoVo;
import com.scda.common.response.ResponseVo;
import com.scda.security.test.feign.DemoFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuqi
 * @Date: 2019/1/10 16:07
 * @Description:
 */
@RestController
public class ResourceController {
    @Autowired
    private DemoFeign demoFeign;

    @GetMapping("/goods/{id}")
    public ResponseVo good(@PathVariable("id") String id) {
        return ResponseVo.success("商品id:" + id);
    }

    @GetMapping("/order/{id}")
    public ResponseVo order(@PathVariable("id") String id) {
        return ResponseVo.success("订单id:" + id);
    }

    @PostMapping("/feign")
    public ResponseVo feign() {
        ResponseVo responseVo = demoFeign.list(new DemoVo());
        return responseVo;
    }

}
