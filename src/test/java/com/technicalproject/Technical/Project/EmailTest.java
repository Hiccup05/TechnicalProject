package com.technicalproject.Technical.Project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicalproject.Technical.Project.service.email.EmailService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EmailTest {
    @Autowired
    private EmailService emailService;

    @Disabled
    @Test
    public void sendMesageTest(){
        emailService.sendEmail("Receiving message means email test is successful", "paudelbipin05@gmail.com","Email transfer testing");
    }

    @Disabled
    @Test
    public void checkFormattedMessage(){
        emailService.formatMessage("Nothing to show here");
    }

    @Disabled
    @Test
    public void weatherInfoSenderCheck(){
        emailService.broadCastWeatherInfo("Uhmm");
        emailService.broadCastWeatherInfo("");
    }
}
