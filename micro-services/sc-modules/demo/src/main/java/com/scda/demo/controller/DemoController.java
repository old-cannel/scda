package com.scda.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scda.business.common.vo.demo.DemoVo;
import com.scda.common.db.page.BasePage;
import com.scda.common.response.ResponseVo;
import com.scda.common.valid.ValidHandle;
import com.scda.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Auther: liuqi
 * @Date: 2018/11/14 14:47
 * @Description: curd示例
 */
@RestController
@RequestMapping("/example")
public class DemoController {
    @Autowired
    private DemoService demoService;


    /**
     * 分页列表
     *
     * @param page
     * @param demoVo
     * @return
     */
    @PostMapping("")
    public ResponseVo pages(BasePage page, @RequestBody DemoVo demoVo) {
        return ResponseVo.success(demoService.pageList(page, demoVo));
    }

    /**
     * 不分页列表
     *
     * @param demoVo
     * @return
     */
    @PostMapping("/list")
    public ResponseVo list(@RequestBody DemoVo demoVo) {
        QueryWrapper<DemoVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(demoVo);
        return ResponseVo.success(demoService.list(queryWrapper));
    }

    /**
     * 根据id获取详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseVo get(@PathVariable("id") String id) {
        return ResponseVo.success(demoService.getById(id));
    }

    /**
     * put方法有幂等性，多次提交都只操作一次（适用于也可以确定数据的唯一性提交）
     * post 方法非幂等性，每次提交都更新或创建资源
     * 新增
     *
     * @param demoVo
     * @param bindingResult
     * @return
     */
    @PostMapping("/add")
    public ResponseVo add(@Valid @RequestBody DemoVo demoVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidHandle.validFail(bindingResult);
        }
        return demoService.insert(demoVo);
    }

    /**
     * 更新
     *
     * @param demoVo
     * @return
     */
    @PostMapping("/update")
    public ResponseVo update(@RequestBody DemoVo demoVo) {
        return ResponseVo.success(demoService.update(demoVo));
    }

    /**
     * 删除
     *
     * @param code
     * @return
     */
    @DeleteMapping("/{code}")
    public ResponseVo delete(@PathVariable("code") String code) {
        return demoService.delete(code);
    }

}
