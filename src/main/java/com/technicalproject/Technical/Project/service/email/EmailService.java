package com.technicalproject.Technical.Project.service.email;

import com.technicalproject.Technical.Project.model.User;
import com.technicalproject.Technical.Project.model.Weather;
import com.technicalproject.Technical.Project.service.WeatherService;
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

    public void sendEmail(String message, String receiver, String topic){
        logger.info("Trying to send email");
        try {
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setTo(receiver);
            mailMessage.setText(message);
            mailMessage.setSubject(topic);
            mailSender.send(mailMessage);
        } catch (MailException e) {
            logger.warn("Error occured while sending email to {}", receiver);
            throw new RuntimeException(e);
        }
    }

    public void broadCastEmail(String message,String topic){
        List<String> users = userService.getUsers().stream()
                .map(User::getUserName).toList();
        for(String user: users){
            sendEmail(message, user, topic);
        }
    }

    public void broadCastWeatherInfo(String message){
        String topic="Weather Information";
        String formattedMessage;
        if(message.isBlank()){
            formattedMessage=formatMessage();
        }else{
            formattedMessage=formatMessage(message);
        }
        broadCastEmail(formattedMessage, topic);
    }

    public String formatMessage(String message){
        Weather weather=weatherService.getWeather();
        return message+" \n"+"Weather Information\n Temperature:"+weather.getCurrent().getTemperature()
                +"\n Weather code:"+weather.getCurrent().getWeatherCode()
                +"\n Observation time:"+weather.getCurrent().getObservationTime();
    }

    public String formatMessage(){
        Weather weather=weatherService.getWeather();
        return "Weather Information"+"\n Temperature:"+weather.getCurrent().getTemperature()
                +"\n Weather code:"+weather.getCurrent().getWeatherCode()
                +"\n Observation time:"+weather.getCurrent().getObservationTime();
    }
}
