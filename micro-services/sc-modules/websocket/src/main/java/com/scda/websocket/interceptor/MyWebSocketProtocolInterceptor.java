package com.scda.websocket.interceptor;

import com.scda.security.filter.WebsocketAuthenticationTokenFilter;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @Auther: liuqi
 * @Date: 2019/3/13 10:42
 * @Description: websocket 自定义协议支持
 */
public class MyWebSocketProtocolInterceptor  implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        String token = serverHttpRequest.getHeaders().getFirst(WebsocketAuthenticationTokenFilter.TOKEN_KEY);
        if(StringUtils.isNotBlank(token)){
            serverHttpResponse.getHeaders().set(WebsocketAuthenticationTokenFilter.TOKEN_KEY,token);
        }
    }
}
