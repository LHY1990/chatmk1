package webapp.chatmk1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import webapp.chatmk1.webSocket.WebSocketChathandler;

@EnableWebSocket
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    WebSocketChathandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 여기서 하위 모든 루트에서 웹소켓을 쓴다는것
        registry.addHandler(webSocketHandler, "/chatSocket").setAllowedOrigins("*").addInterceptors(new HttpSessionHandshakeInterceptor());
    }
}

