package com.technicalproject.Technical.Project.service;

import com.technicalproject.Technical.Project.model.Weather;
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

    @Value("${WAPI}")
    private String url;
    
    @Value("${KEY}")
    private String key;

    public Weather getWeather(){
        String finalUrl=url.replace("KEY",key).replace("location","pokhara");
        log.info("Hitting the final url {}",finalUrl);
        ResponseEntity<Weather> weather = restTemplate.exchange(finalUrl, HttpMethod.GET, null, Weather.class);
        return weather.getBody();
    }
}
