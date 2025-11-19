package com.technicalproject.Technical.Project;

import com.technicalproject.Technical.Project.service.WeatherService;
import com.technicalproject.Technical.Project.service.redis.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisService redisService;
    @Autowired
    WeatherService weatherService;

    @Test
    public void checkRedis(){
        weatherService.getWeather();
    }
}
