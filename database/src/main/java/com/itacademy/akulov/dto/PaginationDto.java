package com.itacademy.akulov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationDto {

    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Integer totalElements;
    private String parameter1;
    private String parameter2;
    private String parameter3;
    private String parameter4;
}
