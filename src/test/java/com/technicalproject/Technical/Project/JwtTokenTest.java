package com.technicalproject.Technical.Project;

import com.technicalproject.Technical.Project.security.jwt.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtTokenTest {
    @Autowired
    private JwtUtil jwtUtil;
    @Test
    void testKey(){

        Assertions.assertTrue(jwtUtil.validateToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiaXBpbkBnbWFpbC5jb20iLCJhdXRob3JpdHkiOltdLCJpYXQiOjE3NjM2NDc3MDIsImV4cCI6MTc2MzY0NzkxOH0.ctXyhZ2fe8xdC12Z3RwReDfDIpjhTxtGfldBrr9KSls"));
    }
}
