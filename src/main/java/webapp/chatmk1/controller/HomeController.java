package webapp.chatmk1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.WebSocketSession;
import webapp.chatmk1.service.ChatService;
import webapp.chatmk1.webSocket.WebSocketChathandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class HomeController {

    @Autowired
    ChatService chatService;
    @Autowired
    WebSocketChathandler socketChathandler;

    @GetMapping("/")
    public ModelAndView getHome(ModelAndView mav) {

        mav.setViewName("templates/chat.html");
        return mav;
    }

    @GetMapping(value="/send")
    public Map<String, String> sendMessage(@RequestParam("userId") String userId, @RequestParam("message") String message) {

        try {
            socketChathandler.sendToAll(message);
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
        return new HashMap<>();
    }

    //todo 재 접속시 기존 대화 내용(닉네임시간)등을 가져와야한다.

//    @GetMapping(value = "/test")
//    public void testMessage(@RequestParam("message")String message) {
//        try {
//            socketChathandler.sendToAll(message);
//        } catch (IOException e) {
//            log.info("e : {}", e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }
}
