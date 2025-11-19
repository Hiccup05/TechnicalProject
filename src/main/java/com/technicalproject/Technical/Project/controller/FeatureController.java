package com.technicalproject.Technical.Project.controller;

import com.technicalproject.Technical.Project.Response.ApiResponse;
import com.technicalproject.Technical.Project.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/feature")
public class FeatureController {
    private final EmailService emailService;

    @PostMapping("/send_email")
    public ResponseEntity<ApiResponse> sendEmail(@RequestParam String user,@RequestParam String topic,@RequestParam String message){
        try{
            emailService.sendEmail(message, user, topic);
            return ResponseEntity.ok(new ApiResponse("Message send successfully", null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Due to some reason, message couldnt be send.", e));
        }
    }

    @PostMapping("/broadcast")
    public ResponseEntity<ApiResponse> broadcastEmail(@RequestParam String user,@RequestParam String topic,@RequestParam String message){
        try{
            emailService.sendEmail(message, user, topic);
            return ResponseEntity.ok(new ApiResponse("Message send successfully", null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Due to some reason, message couldnt be send.", e));
        }
    }

    @PostMapping("/weather_information")
    public ResponseEntity<ApiResponse> weatherInformation(@RequestParam String message){
        try{
            emailService.broadCastWeatherInfo(message);
            return ResponseEntity.ok(new ApiResponse("Message send successfully", null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Due to some reason, message couldnt be send.", e));
        }
    }

}
