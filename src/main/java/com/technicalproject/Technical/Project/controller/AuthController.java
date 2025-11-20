package com.technicalproject.Technical.Project.controller;

import com.technicalproject.Technical.Project.Request.LoginRequestDto;
import com.technicalproject.Technical.Project.Request.SignUpRequest;
import com.technicalproject.Technical.Project.Response.ApiResponse;
import com.technicalproject.Technical.Project.Response.LoginResponseDto;
import com.technicalproject.Technical.Project.Response.SignUpResponse;
import com.technicalproject.Technical.Project.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequestDto requestDto){
        try{
            LoginResponseDto loginResponseDto = authService.logIn(requestDto);
            return ResponseEntity.ok(new ApiResponse(null,loginResponseDto));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signIn(@RequestBody SignUpRequest request){
        try{
            authService.createUser(request);
            return ResponseEntity.ok(new ApiResponse(null,new SignUpResponse("Sign up successful", "You may proceed to login")));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(e.getMessage(),null));
        }
    }
}
