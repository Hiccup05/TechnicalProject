package com.technicalproject.Technical.Project.service.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {

    private final StringRedisTemplate redisTemplate;

    ObjectMapper objectMapper=new ObjectMapper();

    public <T> T getFromRedis(String key, Class<T> object){

        try {
            Object o = redisTemplate.opsForValue().get(key);
            if(o==null){
                return null;
            }
            return objectMapper.readValue(o.toString(), object);
        } catch (JsonProcessingException e) {
            log.error("Error while returning the objects from redis.   {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void setInRedis(String key, Object object, int ttl){
            try{
                String objectInString = objectMapper.writeValueAsString(object);
                redisTemplate.opsForValue().set(key, objectInString, ttl, TimeUnit.MINUTES);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
    }
}
