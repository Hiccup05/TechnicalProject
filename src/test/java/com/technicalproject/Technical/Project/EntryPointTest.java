package com.technicalproject.Technical.Project;

import com.technicalproject.Technical.Project.Repository.UserRepository;
import com.technicalproject.Technical.Project.Request.LoginRequestDto;
import com.technicalproject.Technical.Project.Request.SignUpRequest;
import com.technicalproject.Technical.Project.Response.LoginResponseDto;
import com.technicalproject.Technical.Project.security.jwt.JwtUtil;
import com.technicalproject.Technical.Project.service.auth.AuthService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class EntryPointTest {
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private  JwtUtil jwtUtil;
    @Autowired
    private  ModelMapper modelMapper;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private AuthService authService;

    @Test
    void testSignIn(){
        SignUpRequest request=new SignUpRequest("Hiccup","Viking","viking@gmail.com","hiccup123");
        authService.createUser(request);

    }

    @Disabled
    @Test
    void testLogIn(){
        LoginRequestDto loginRequestDto=new LoginRequestDto();
        loginRequestDto.setPassword("hiccup123");
        loginRequestDto.setUserName("viking@gmail.com");
        LoginResponseDto loginResponseDto = authService.logIn(loginRequestDto);
    }
}
