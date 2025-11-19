package com.technicalproject.Technical.Project.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityUtil {
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
