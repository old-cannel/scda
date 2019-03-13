package com.scda.websocket.controller;

import com.scda.common.response.ResponseVo;
import com.scda.websocket.service.MyFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: liuqi
 * @Date: 2019/3/12 15:36
 * @Description: 我的朋友
 */
@RestController
public class MyFriendsController {

    @Autowired
    private MyFriendsService myFriendsService;

    /**
     * 我的朋友
     *
     * @return
     */
    @RequestMapping("/myfriends")
    public ResponseVo myFriends() {
        String userName =  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        List<String> friends=myFriendsService.findMyFriends(userName);
        if(friends!=null){
            return ResponseVo.success(friends);
        }else{
            return ResponseVo.noData();
        }

    }
}
