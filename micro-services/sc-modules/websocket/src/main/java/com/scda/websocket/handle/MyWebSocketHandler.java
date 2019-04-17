package com.scda.websocket.handle;

import com.alibaba.fastjson.JSONObject;
import com.scda.websocket.vo.WebrtcMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: liuqi
 * @Date: 2019/3/1 17:26
 * @Description: websocket 处理类-基于webrtc
 */
@Slf4j
@Component
public class MyWebSocketHandler extends TextWebSocketHandler {
    private static Map<String, WebSocketSession> webSocketSessionMap = new HashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.info("websocket：{} send message:{}", session.getPrincipal().getName(), message.getPayload());

        WebrtcMessage webrtcMessage = JSONObject.parseObject(message.getPayload(), WebrtcMessage.class);
        WebSocketSession toUserSession = webSocketSessionMap.get(webrtcMessage.getUsername());

        webrtcMessage.setFromUserName(session.getPrincipal().getName());
        if (toUserSession == null) {
            backSend(session, "对方不在线！");
        } else {
            sendMessage(session, toUserSession, webrtcMessage);
        }


    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        log.info("{} close websocket ", session.getPrincipal().getName());
        webSocketSessionMap.remove(session.getPrincipal().getName());
    }

    /**
     * 客户端会话链接成功，保存会话到redis
     *
     * @param session
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("{} open websocket ", session.getPrincipal().getName());
        webSocketSessionMap.put(session.getPrincipal().getName(), session);
    }

    /**
     * 发送消息
     *
     * @param fromSession   发送会话
     * @param toSession     接收会话
     * @param webrtcMessage 发送内容
     */
    public void sendMessage(WebSocketSession fromSession, WebSocketSession toSession, WebrtcMessage webrtcMessage) {
        log.info("***************发送人：{}-->>接收人：{}---->>消息内容：{}***************",fromSession.getPrincipal().getName(),toSession.getPrincipal().getName(),JSONObject.toJSONString(webrtcMessage));
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
            WebrtcMessage webrtcMessage=new WebrtcMessage();
            webrtcMessage.setFromUserName("系统");
            webrtcMessage.setMessage(tip);
            session.sendMessage(new TextMessage(JSONObject.toJSONString(webrtcMessage)));
        } catch (IOException e1) {
            log.error("异常系统提示发送人消息IO异常，发送人信息:{}，提示消息：{}", session.getPrincipal().getName(), tip);
        }
    }

}
