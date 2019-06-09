package com.itacademy.akulov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewDto {

    private Long limit;
    private Long offset;
    private Long nums;
    private Long size;
    private Long page;
}
