package com.scda.demo.controller;

import com.scda.common.db.page.BasePage;
import com.scda.common.entity.demo.DemoVo;
import com.scda.common.response.ResponseVo;
import com.scda.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuqi
 * @Date: 2018/11/14 14:47
 * @Description:
 */
@RestController
public class DemoController {
    @Autowired
    private DemoService demoService;
    @GetMapping("/hello")
    public String hello(){
//        return "Hello World!";
        return demoService.selectById().getName();
    }
    @GetMapping("pages")
    public ResponseVo pages(BasePage page, DemoVo demoVo){
        return ResponseVo.success(demoService.pageList(page,demoVo));
    }


}
