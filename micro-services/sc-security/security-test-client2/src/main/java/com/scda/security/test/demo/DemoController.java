package com.scda.security.test.demo;

import com.scda.common.response.ResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuqi
 * @Date: 2019/1/22 10:12
 * @Description:
 */
@RestController
public class DemoController {
    @GetMapping("hello/{name}")
    public ResponseVo hello(@PathVariable("name") String name){
        return ResponseVo.success("Hello "+name+" !");
    }
}
