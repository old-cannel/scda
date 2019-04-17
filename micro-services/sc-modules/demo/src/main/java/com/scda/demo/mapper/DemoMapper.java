package com.scda.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scda.business.common.vo.demo.DemoVo;
import com.scda.common.db.page.BasePage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: liuqi
 * @Date: 2018/11/14 14:56
 * @Description:
 */

@Repository
public interface DemoMapper extends BaseMapper<DemoVo> {
    /**
     * 自定义sql
     * @param page
     * @param demoVo
     * @return
     */
    @Select({"<script>","SELECT code,id from demo where del_flag='0' <if test='demo.code !=null'> and code=#{demo.code}</if>","</script>"})
    List<DemoVo> selectPageByCustom(BasePage<DemoVo> page, @Param("demo") DemoVo demoVo);
}
