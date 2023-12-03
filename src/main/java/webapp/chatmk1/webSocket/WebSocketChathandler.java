package webapp.chatmk1.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class WebSocketChathandler extends TextWebSocketHandler {
    private static final Map<String, WebSocketSession> userSocketMap = new HashMap<>();

    //최초 웹소켓 연결시
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        log.info("소켓이 연결됌");
        super.afterConnectionEstablished(webSocketSession);
        userSocketMap.put(webSocketSession.getId(), webSocketSession);

    }

    // 연결 종료시
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        log.info("소켓이 해제 됌");
        userSocketMap.remove(webSocketSession.getId());
        super.afterConnectionClosed(webSocketSession, closeStatus);

    }

    // 메시지를 발송하면 동작하는 메소드
    @Override
    public void handleTextMessage(WebSocketSession targetSocketSession, TextMessage message) throws IOException {
        targetSocketSession.sendMessage(message);
    }

    public void sendToAll(String message) throws IOException {
        TextMessage textMessage = new TextMessage(message.getBytes(StandardCharsets.UTF_8));

        for (WebSocketSession socket : userSocketMap.values()) {
            handleTextMessage(socket, textMessage);
        }
    }

}
