package com.technicalproject.Technical.Project.service.email;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.technicalproject.Technical.Project.model.User;
import com.technicalproject.Technical.Project.model.Weather;
import com.technicalproject.Technical.Project.service.WeatherService;
import com.technicalproject.Technical.Project.service.redis.RedisService;
import com.technicalproject.Technical.Project.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final static Logger logger=LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    private final UserService userService;
    private final WeatherService weatherService;
    private final RedisService redisService;

    public void sendEmail(String message, String receiver, String topic){
        logger.info("Trying to send email");
        try {
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setTo(receiver);
            mailMessage.setText(message);
            mailMessage.setSubject(topic);
            mailSender.send(mailMessage);
            logger.info(String.valueOf(mailMessage));
        } catch (MailException e) {
            logger.warn("Error occurred while sending email to {}", receiver);
            throw new RuntimeException(e);
        }
    }

    public void broadCastEmail(String message,String topic) throws RuntimeException {
        List<String> users = redisService.getFromRedis("users", List.class);
        if(users!=null){
            for(String user: users){
                sendEmail(message, user, topic);
            }
        }
        else {
            users = userService.getUsers().stream()
                    .map(User::getUserName).toList();
            redisService.setInRedis("users",users, 5);
            for(String user: users){
                sendEmail(message, user, topic);
            }
        }
    }

    public void broadCastWeatherInfo(String message) throws RuntimeException {
        String topic="Weather Information";
        String formattedMessage=formatMessage(message);
        broadCastEmail(formattedMessage, topic);
    }

    public String formatMessage(String message) throws RuntimeException {
        Weather weather=weatherService.getWeather();
        return message+" \n"+"Weather Information\n Temperature:"+weather.getCurrent().getTemperature()
                +"\n Weather code:"+weather.getCurrent().getWeatherCode()
                +"\n Observation time:"+weather.getCurrent().getObservationTime();
    }

}
