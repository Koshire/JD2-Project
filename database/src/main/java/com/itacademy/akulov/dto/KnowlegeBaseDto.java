package com.itacademy.akulov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KnowlegeBaseDto {

    private Long id;
    private CourseDtoC courseDtoC;
    private LoginDto userKnowlegeBase;
    private String localDate;
    private String text;
    //private List<KBComments> kbComments = new ArrayList<>();
}
