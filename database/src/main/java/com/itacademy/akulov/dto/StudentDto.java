package com.itacademy.akulov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {

    private Long id;
    private String email;
    private String password;
    private String role;
    private String name;
    private String middle;
    private String last;
    private String phone;
    private boolean blockList;
    private List<ResultDto> results;
}
