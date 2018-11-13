package com.scda.controller;

import com.scda.common.response.ResponseVo;
import com.scda.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuqi
 * @Date: 2018/11/9 16:46
 * @Description:
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @GetMapping("/test/{id}")
    public ResponseVo test(@PathVariable("id") String id) {
        return ResponseVo.success(demoService.findById(id));
    }
}
