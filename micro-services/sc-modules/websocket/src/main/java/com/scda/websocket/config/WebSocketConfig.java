package com.scda.websocket.config;

import com.scda.websocket.handle.MyWebSocketHandler;
import com.scda.websocket.interceptor.MyWebSocketProtocolInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * @Auther: liuqi
 * @Date: 2019/1/22 16:08
 * @Description: websocket 配置
 */
@Configuration
@EnableWebSocket
@Slf4j
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler, "/chat").setAllowedOrigins("*")
                .addInterceptors(new MyWebSocketProtocolInterceptor())
                .withSockJS();
    }


}