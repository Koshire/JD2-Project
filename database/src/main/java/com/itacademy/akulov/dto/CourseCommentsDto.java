package com.itacademy.akulov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CourseCommentsDto {

    private Long id;
    private LoginDto user;
    private CourseDtoC course;
    private String comment;
}
