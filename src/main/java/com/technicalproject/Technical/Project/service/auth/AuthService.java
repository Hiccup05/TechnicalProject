package com.technicalproject.Technical.Project.service.auth;

import com.technicalproject.Technical.Project.Repository.UserRepository;
import com.technicalproject.Technical.Project.Request.LoginRequestDto;
import com.technicalproject.Technical.Project.Request.SignUpRequest;
import com.technicalproject.Technical.Project.Response.LoginResponseDto;
import com.technicalproject.Technical.Project.exception.ResourceAlreadyFound;
import com.technicalproject.Technical.Project.model.CustomUser;
import com.technicalproject.Technical.Project.model.User;
import com.technicalproject.Technical.Project.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto logIn(LoginRequestDto requestDto) {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(requestDto.getUserName(), requestDto.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authentication);
            String token = jwtUtil.generateTokenFromUser(authenticate);
            CustomUser principal = (CustomUser) authentication.getPrincipal();
            return new LoginResponseDto(principal.getId(), token);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUser(SignUpRequest request) {
        userRepository.findByUserName(request.getUserName())
                .ifPresentOrElse(user ->
                        {throw new ResourceAlreadyFound("User from this email already exists.");},
                        ()->{
                            User user=new User();
                            user.setFirstName(request.getFirstName());
                            user.setLastName(request.getLastName());
                            user.setUserName(request.getUserName());
                            user.setPassword(passwordEncoder.encode(request.getPassword()));
                            userRepository.save(user);
                        });
    }
}
