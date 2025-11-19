package com.technicalproject.Technical.Project.service.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    ObjectMapper objectMapper=new ObjectMapper();

    public <T> T getFromRedis(String key, Class<T> object) throws JsonProcessingException {
        Object o = redisTemplate.opsForValue().get(key);
        return objectMapper.readValue(o.toString(), object);
    }

    public void setInRedis(String key, Object object, int ttl) throws JsonProcessingException {
        String objectInString = objectMapper.writeValueAsString(object);
        redisTemplate.opsForValue().set(key, objectInString, ttl, TimeUnit.MINUTES);
    }
}
