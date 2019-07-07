package com.itacademy.akulov.mapper;

import com.itacademy.akulov.dto.KnowlegeBaseDto;
import com.itacademy.akulov.entity.KnowlegeBase;
import org.mapstruct.Mapper;

import java.time.LocalDate;

@Mapper
public class KnowlegeBaseMapper implements BaseMapper<KnowlegeBase, KnowlegeBaseDto> {

    private static final KnowlegeBaseMapper INSTANCE = new KnowlegeBaseMapper();

    private LoginMapper loginMapper = LoginMapper.getInstance();
    private CourseMapper courseMapper = CourseMapper.INSTANCE;

    @Override
    public KnowlegeBaseDto mapToDto(KnowlegeBase entity) {
        return KnowlegeBaseDto.builder()
                .courseDtoC(courseMapper.courseToCourseCDto(entity.getCourse()))
                .id(entity.getId())
                .localDate(entity.getLocalDate().toString())
                .userKnowlegeBase(loginMapper.mapToDto(entity.getUserKnowlegeBase()))
                .text(entity.getText())
                .build();
    }

    @Override
    public KnowlegeBase mapToEntity(KnowlegeBaseDto dto) {
        return KnowlegeBase.builder()
                .id(dto.getId())
                .course(courseMapper.courseDtoCToCourse(dto.getCourseDtoC()))
                .localDate(LocalDate.parse(dto.getLocalDate()))
                .text(dto.getText())
                .userKnowlegeBase(loginMapper.mapToEntity(dto.getUserKnowlegeBase()))
                .build();
    }

    public static KnowlegeBaseMapper getInstance() {
        return INSTANCE;
    }
}
