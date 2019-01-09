package com.scda.demo.controller;

import com.scda.business.common.entity.demo.DemoVo;
import com.scda.common.db.page.BasePage;
import com.scda.common.response.ResponseVo;
import com.scda.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
    public String hello() {
        return demoService.selectById().getName();
    }

    @PostMapping("/pages")
    public ResponseVo pages(BasePage page, @RequestBody DemoVo demoVo) {
        return ResponseVo.success(demoService.pageList(page, demoVo));
    }
    @PutMapping("/add")
    public ResponseVo add(@Valid @RequestBody DemoVo demoVo, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<String> results=new ArrayList<>();
            bindingResult.getAllErrors().forEach(error -> results.add(error.getDefaultMessage()));
            return ResponseVo.validFail(results);
        }
        return ResponseVo.success(demoService.insert(demoVo));
    }

    @PostMapping("/update")
    public ResponseVo update(@RequestBody DemoVo demoVo){
        return ResponseVo.success(demoService.update(demoVo));
    }

    @DeleteMapping("/delete/{code}")
    public ResponseVo delete(@PathVariable("code") String code){
       return ResponseVo.success(demoService.delete(code));
    }

}
