package webapp.chatmk1.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Autowired
    RedisYmlConfig config;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redis = new RedisStandaloneConfiguration();
        redis.setHostName(config.getHost());
        redis.setPort(config.getPort());
        redis.setPassword(config.getPassword());
        LettuceConnectionFactory lettuceConnection = new LettuceConnectionFactory(redis);
        return lettuceConnection;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    }

    /**
     * set get
     */
    @Bean
    public ValueOperations<String, Object> getValueOperator() {
        return redisTemplate().opsForValue();
    }

    @Bean
    public ZSetOperations<String, Object> zSetOperations() {
        return redisTemplate().opsForZSet();
    }

    @Bean
    public SetOperations<String, Object> setOperations() {
        return redisTemplate().opsForSet();
    }

    @Bean
    public HashOperations<String, Object, Object> gethashOperations() {
        return redisTemplate().opsForHash();
    }

    @Bean
    public HyperLogLogOperations<String, Object> getHyperLogLogOperations() {
        return redisTemplate().opsForHyperLogLog();
    }

    @Bean
    public StreamOperations<String, Object, Object> getStreamOperations() {
        return redisTemplate().opsForStream();
    }

    @Bean
    public ClusterOperations<String, Object> getClusterOperations() {
        return redisTemplate().opsForCluster();
    }

}
