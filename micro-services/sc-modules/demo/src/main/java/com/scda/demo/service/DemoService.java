package com.scda.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scda.business.common.vo.demo.DemoVo;
import com.scda.common.db.page.BasePage;
import com.scda.common.exception.RollBackHandle;
import com.scda.common.response.ResponseVo;
import com.scda.demo.mapper.DemoMapper;
import com.scda.security.vo.EntityUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: liuqi
 * @Date: 2018/11/14 14:53
 * @Description:
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class DemoService extends ServiceImpl<DemoMapper, DemoVo> {


    /**
     * @param page
     * @param demoVo
     * @return
     */
    public BasePage<DemoVo> pageList(BasePage<DemoVo> page, DemoVo demoVo) {

        /*非自定义sql分页*/
        /* QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.setEntity(demoVo);
        return baseMapper.selectPage(page, queryWrapper);*/
        /*自定义sql分页*/
        page.setRecords(baseMapper.selectPageByCustom(page, demoVo));
        return page;
    }

    /**
     * 新增
     *
     * @param demoVo
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseVo insert(DemoVo demoVo) {
        EntityUtil.beforInsert(demoVo, "demo");
        return ResponseVo.success(baseMapper.insert(demoVo));
    }

    /**
     * 更新
     *
     * @param demoVo
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseVo update(DemoVo demoVo) {
        EntityUtil.beforUpdate(demoVo, "demo");
        UpdateWrapper updateWrapper = new UpdateWrapper();
        DemoVo dv = new DemoVo();
        dv.setCode(demoVo.getCode());
        updateWrapper.setEntity(dv);
        if (1 != baseMapper.update(demoVo, updateWrapper)) {
            RollBackHandle.rollBack("未更新成功");
        }
        return ResponseVo.success("更新记录成功");
    }

    /**
     * 删除
     *
     * @param code
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseVo delete(String code) {
        //        物理删除
        QueryWrapper queryWrapper = new QueryWrapper();
        DemoVo dv = new DemoVo();
        dv.setCode(code);
        queryWrapper.setEntity(dv);

        if (1 != baseMapper.delete(queryWrapper)) {
            RollBackHandle.rollBack("物理删除失败");
        }
        return ResponseVo.success("物理删除成功");
    }
}
