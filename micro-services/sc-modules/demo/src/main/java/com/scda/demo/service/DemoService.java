package com.scda.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scda.common.entity.demo.DemoVo;
import com.scda.demo.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: liuqi
 * @Date: 2018/11/14 14:53
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class DemoService {
    @Autowired
    private DemoMapper demoMapper;

    public DemoVo selectById() {
//        return "Hello Service!";
        return demoMapper.selectById("1");
    }
    public IPage<DemoVo> pageList(IPage page,DemoVo demoVo){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.setEntity(demoVo);
        return demoMapper.selectPage(page,queryWrapper);
    }
}
