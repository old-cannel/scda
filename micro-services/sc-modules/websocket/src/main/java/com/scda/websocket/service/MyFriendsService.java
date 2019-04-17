package com.scda.websocket.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scda.security.vo.SysUserVo;
import com.scda.websocket.mapper.MyfriendsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: liuqi
 * @Date: 2019/3/12 15:36
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class MyFriendsService {
    @Autowired
    private MyfriendsMapper myfriendsMapper;

    public List<String> findMyFriends(String userName) {
        QueryWrapper<SysUserVo> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("user_name");
        queryWrapper.ne("user_name",userName);
        List<SysUserVo> sysUserVoList= myfriendsMapper.selectList(queryWrapper);
        if(sysUserVoList!=null&&sysUserVoList.size()>0){
             return sysUserVoList.stream().map(v-> {return v.getUserName();}).collect(Collectors.toList());
        }
        return null;
    }

}
