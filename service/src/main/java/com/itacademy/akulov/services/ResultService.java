package com.itacademy.akulov.services;

import com.itacademy.akulov.dto.ResultDto;
import com.itacademy.akulov.entity.Result;
import com.itacademy.akulov.mapper.ResultMapper;
import com.itacademy.akulov.repository.CourseRepository;
import com.itacademy.akulov.repository.ResultRepository;
import com.itacademy.akulov.repository.StudentUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResultService {

    private ResultMapper resultMapper = ResultMapper.getInstance();
    private ResultRepository resultRepository;
    private CourseRepository courseRepository;
    private StudentUserRepository studentUserRepository;

    public ResultService(ResultRepository resultRepository,
                         CourseRepository courseRepository,
                         StudentUserRepository studentUserRepository) {
        this.resultRepository = resultRepository;
        this.courseRepository = courseRepository;
        this.studentUserRepository = studentUserRepository;
    }

    public List<ResultDto> getByCourse(Long courseId){
        return resultRepository.findByCourseId(courseId)
                .stream()
                .map(resultMapper::mapToDto).collect(Collectors.toList());
    }

    public Optional<Result> getByEId(Long courseId, Long userId){
        return resultRepository.findById(Result.UserCourse.builder()
                .userId(userId)
                .courseId(courseId)
                .build());
    }

    public Long update(Long userId, Long courseId, Integer result) {
        Long apply = 0L;
        if (courseRepository.findById(courseId).isPresent()
                && studentUserRepository.findById(userId).isPresent()
        && resultRepository.findById(Result.UserCourse.builder()
                .courseId(courseId)
                .userId(userId)
                .build()).isPresent()) {
            Result old = resultRepository.findById(Result.UserCourse.builder()
                    .courseId(courseId)
                    .userId(userId)
                    .build()).get();
            old = Result.builder()
                    .userCourse(Result.UserCourse.builder()
                            .courseId(courseId)
                            .userId(userId)
                            .build())
                    .course(courseRepository.findById(courseId).get())
                    .userResult(studentUserRepository.findById(userId).get())
                    .result(result)
                    .build();
            apply = resultRepository.save(old).getCourse().getId();
        }

        return apply;
    }


}
