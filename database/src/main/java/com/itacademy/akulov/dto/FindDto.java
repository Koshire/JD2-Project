package com.itacademy.akulov.dto;

import com.itacademy.akulov.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindDto {

    private String fio;
    private Role role;
    private Boolean blockList;
    private Long viewLimit;
    private Long page;
    private Long pages;
    private Long size;
}
