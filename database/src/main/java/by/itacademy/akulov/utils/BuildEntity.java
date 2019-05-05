package by.itacademy.akulov.utils;

import by.itacademy.akulov.entity.CourseType;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;

@UtilityClass
public class BuildEntity {

    @SneakyThrows
    public static CourseType buildCourseType(ResultSet resultSet) {

        return CourseType.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .build();
    }

}