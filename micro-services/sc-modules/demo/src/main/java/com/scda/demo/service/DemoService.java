package com.scda.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scda.business.common.vo.demo.DemoVo;
import com.scda.common.db.util.BaseFieldValueUtils;
import com.scda.common.utils.IdUtils;
import com.scda.demo.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "scda", key="#root.targetClass+#root.methodName")
    public DemoVo selectById() {
//        return "Hello Service!";
        return demoMapper.selectById("1");
    }

    public IPage<DemoVo> pageList(IPage page, DemoVo demoVo) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.setEntity(demoVo);
        return demoMapper.selectPage(page, queryWrapper);
    }

    /**
     * 新增
     * @param demoVo
     * @return
     */
    @Transactional(readOnly = false)
    public int insert(DemoVo demoVo) {
        demoVo.setId(IdUtils.getUUID());
        BaseFieldValueUtils.setCommonField4Insert(demoVo,"demo","001");
        return demoMapper.insert(demoVo);
    }

    /**
     * 更新
     * @param demoVo
     * @return
     */
    @Transactional(readOnly = false)
    public int update(DemoVo demoVo) {
        BaseFieldValueUtils.setCommonField4Update(demoVo,"demo","003");
        UpdateWrapper updateWrapper = new UpdateWrapper();
        DemoVo dv=new DemoVo();
        dv.setCode(demoVo.getCode());
        updateWrapper.setEntity(dv);
        return  demoMapper.update(demoVo,updateWrapper);
    }

    /**
     * 删除
     * @param code
     * @return
     */
    @Transactional(readOnly = false)
    public int delete(String code){
        QueryWrapper queryWrapper=new QueryWrapper();
        DemoVo dv=new DemoVo();
        dv.setCode(code);
        queryWrapper.setEntity(dv);
        return demoMapper.delete(queryWrapper);
    }
}
