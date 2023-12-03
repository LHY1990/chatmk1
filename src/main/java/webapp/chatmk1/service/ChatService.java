package webapp.chatmk1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp.chatmk1.redis.RedisRepository;

@Service
public class ChatService {
    @Autowired
    RedisRepository redisRepository;



}
