package com.technicalproject.Technical.Project.Request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequestDto {
    private String userName;
    private String password;
}
