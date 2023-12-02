package com.lhy.springLettuce.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lhy.springLettuce.Domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@SpringBootTest
class RedisConfigTest {

    @Autowired
    RedisRepository redisRepository;

    @BeforeEach
    void preset() {
        /*
        Map<String, String> preInsert = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            preInsert.put(String.valueOf(i), "가_"+ String.valueOf(i));
        }
        redisRepository.multiSet(preInsert);
        */
    }

    //@AfterEach
    void reset() {
        /*
        long startTimeMs = System.currentTimeMillis();
        Set<String> keys = redisRepository.keys();
        for (String key : keys) {
            redisRepository.getExpire(key);
        }
        log.info("걸린시간1 : {} ms", System.currentTimeMillis() - startTimeMs);
        */
        //36초 1000개
        //210초 10000개

        long startTimeMs = System.currentTimeMillis();
        Set<String> keys = redisRepository.keys();
        redisRepository.getRedisTemplate().executePipelined((RedisCallback<Object>) connection -> {
            for (String key : keys) {
                log.info("삭제되는 키: {}", key);
                connection.keyCommands().del(key.getBytes(StandardCharsets.UTF_8));
            }
            return null;

        });
        log.info("걸린시간1 : {} ms", System.currentTimeMillis() - startTimeMs);
        // 0.7초 1000개
        // 2.5초 10000개

    }

    @Test
    void templateTest1() {
        redisRepository.set("temp", "123");
    }

    @Test
    void tempateTest2() {
        String getString = redisRepository.get("temp");
        log.info("get String : {}", getString);
    }

    @Test
    void templateTest3() {
        log.info("test : {}", redisRepository.multiGet(List.of("1", "2")));
    }

    @Test
    @DisplayName("클래스 객체를 레디스에 넣기")
    void templateTest4() throws Exception {
        Person person = Person.builder().name("훈영").age(33).build();
        redisRepository.setPerson("훈영", person);

        Person resultPerson = redisRepository.getPerson("훈영");
        log.info("result : {}", resultPerson.getAge());
    }
}