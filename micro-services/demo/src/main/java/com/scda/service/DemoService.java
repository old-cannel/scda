package com.scda.service;

import com.scda.common.entity.demo.DemoVo;
import com.scda.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: liuqi
 * @Date: 2018/11/13 14:58
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class DemoService {
    @Autowired
    private DemoMapper demoMapper;

    public DemoVo findById(String id) {
        return demoMapper.selectById(id);
    }
}
