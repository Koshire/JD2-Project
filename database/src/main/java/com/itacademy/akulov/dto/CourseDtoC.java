package com.itacademy.akulov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDtoC {
    private Long id;
    private Long initUser;
    private String type;
    private String startDate;
    private Integer duration;
    private String name;
    private String description;
    private String plan;
    private Long version;
}
