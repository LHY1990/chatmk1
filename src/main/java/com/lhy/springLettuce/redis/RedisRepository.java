package com.lhy.springLettuce.redis;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhy.springLettuce.Domain.Person;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import javax.xml.datatype.Duration;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
public class RedisRepository {
    @Getter
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    static ObjectMapper objectMapper = new ObjectMapper();
    static StringRedisSerializer redisSerializer = new StringRedisSerializer();

    @Autowired
    private ValueOperations<String, Object> valueOperator;
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;
    @Autowired
    private SetOperations<String, Object> setOperations;
    @Autowired
    private HashOperations<String, Object, Object> hashOperations;
    @Autowired
    private HyperLogLogOperations<String, Object> hyperLogLogOperations;

    final long TTL = 60;

    //keys
    Set<String> keys() {
        return redisTemplate.keys("*");
    }

    void getExpire(String key) {
        log.info("getExpire : {}", redisTemplate.opsForValue().getAndDelete(key));
    }


    //set
    void set(String key, String value) {
        valueOperator.set(key, value, TTL, TimeUnit.SECONDS);
    }

    //get
    String get(String key) {
        //return valueOperator.get(key);
        return valueOperator.get(key).toString();
    }

    //mset
    void multiSet(Map<String, String> multiSetMap) {
        valueOperator.multiSet(multiSetMap);
    }

    //mget
    List<Object> multiGet(List<String> keys) {
        return valueOperator.multiGet(keys);
    }


    void setPerson(String key, Person person) throws JsonProcessingException {

        valueOperator.set(key, person);
    }

    Person getPerson(String key) throws IOException, ParseException {
        Person person = Person.class.cast(valueOperator.get(key));
        log.info("person : {}", person);

        return person;
    }
    //회사에서는 어떻게 처리했나 확인해보자

}
