package com.scda.websocket.handle;

import com.alibaba.fastjson.JSONObject;
import com.scda.common.utils.RedisUtil;
import com.scda.websocket.vo.WebrtcMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

/**
 * @Auther: liuqi
 * @Date: 2019/3/1 17:26
 * @Description: websocket 处理类-基于webrtc,会话放到redis
 */
@Slf4j
@Component
public class MyWebSocketHandler extends TextWebSocketHandler {
    //websocket 会话key前缀
    private final static String KEY_PREFIX = "websocket_session_";

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.info("my websocket,{} send message:{}", session.getId(), message.getPayload());

        WebrtcMessage webrtcMessage = JSONObject.parseObject(message.getPayload(), WebrtcMessage.class);
        WebSocketSession toUserSession = (WebSocketSession) redisUtil.get(KEY_PREFIX + webrtcMessage.getUsername());
        if (toUserSession == null) {
            throw new RuntimeException("对方不在线上");
        }
        webrtcMessage.setFromUserName(redisUtil.get(KEY_PREFIX + session.getPrincipal()).toString());
        sendMessage(session, toUserSession, webrtcMessage);

    }

    /**
     * 客户端会话链接成功，保存会话到redis
     *
     * @param session
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("{} into my websocket ", session.getId());
        redisUtil.set(KEY_PREFIX + session.getPrincipal().getName(), session, 60 * 60 * 2);
    }

    /**
     * 发送消息
     *
     * @param fromSession   发送会话
     * @param toSession     接收会话
     * @param webrtcMessage 发送内容
     */
    public void sendMessage(WebSocketSession fromSession, WebSocketSession toSession, WebrtcMessage webrtcMessage) {
        if (toSession == null && fromSession != null) {
            log.warn("接收人不能为空");
            backSend(fromSession, "接收人不能为空");
            return;
        }
        if (webrtcMessage == null && fromSession != null) {
            log.warn("发送内容不能为空");
            backSend(fromSession, "发送内容不能为空");
            return;
        }
        try {
            toSession.sendMessage(new TextMessage(JSONObject.toJSONString(webrtcMessage)));
        } catch (IOException e) {
            log.warn("消息发送给接收人IO异常，已提示发送人重新发送！");
            if (fromSession != null) {
                backSend(fromSession, "消息发送失败，请重新发送");
            }

        }
    }

    /**
     * 返回错误提示
     *
     * @param session
     * @param tip
     */
    private void backSend(WebSocketSession session, String tip) {
        try {
            session.sendMessage(new TextMessage("tip"));
        } catch (IOException e1) {
            log.error("异常系统提示发送人消息IO异常，发送人信息:{}，提示消息：{}", session.getId(), tip);
        }
    }

}
