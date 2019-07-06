package com.itacademy.akulov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {

    private Long id;
    private String email;
    private String password;
    private String role;
    private String name;
    private String middle;
    private String last;
    private String phone;
    private String language;
}
