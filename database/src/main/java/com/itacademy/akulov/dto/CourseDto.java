package com.itacademy.akulov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private Long id;
    private Long initUser;
    private String type;
    private String startDate;
    private Integer duration;
    private String name;
    private String description;
    private String plan;
    private List<LoginDto> students;
    private List<LoginDto> teachers;
    private Long version;
}
