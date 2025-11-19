package com.technicalproject.Technical.Project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.technicalproject.Technical.Project.model.Weather;
import com.technicalproject.Technical.Project.service.redis.RedisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
@Slf4j
public class WeatherService {
    private final RestTemplate restTemplate;
    private final RedisService redisService;

    @Value("${WAPI}")
    private String url;
    
    @Value("${KEY}")
    private String key;

    private final String CITY="Pokhara";

    public Weather getWeather() throws JsonProcessingException {
        Weather weather = redisService.getFromRedis(CITY, Weather.class);
        if(weather!=null) {
            return weather;
        }
        String finalUrl=url.replace("KEY",key).replace("location",CITY);
        log.info("Hitting the final url {}",finalUrl);
        ResponseEntity<Weather> weatherResponse = restTemplate.exchange(finalUrl, HttpMethod.GET, null, Weather.class);
        weather= weatherResponse.getBody();
        redisService.setInRedis(CITY,Weather.class, 5);
        return weather;
    }
}
