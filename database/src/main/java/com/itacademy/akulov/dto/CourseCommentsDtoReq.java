package com.itacademy.akulov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CourseCommentsDtoReq {

    private Long id;
    private Long userId;
    private Long courseId;
    private String comment;
}
