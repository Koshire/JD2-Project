package com.itacademy.akulov.services;

import com.itacademy.akulov.dto.CourseDto;
import com.itacademy.akulov.dto.CourseDtoC;
import com.itacademy.akulov.dto.LoginDto;
import com.itacademy.akulov.dto.PaginationDto;
import com.itacademy.akulov.entity.Course;
import com.itacademy.akulov.entity.CourseTeacher;
import com.itacademy.akulov.entity.Result;
import com.itacademy.akulov.mapper.CourseMapper;
import com.itacademy.akulov.mapper.LoginMapper;
import com.itacademy.akulov.repository.CourseRepository;
import com.itacademy.akulov.repository.CourseTeacherRepository;
import com.itacademy.akulov.repository.ResultRepository;
import com.itacademy.akulov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseService {

    private CourseRepository courseRepository;
    private CourseMapper courseMapper = CourseMapper.INSTANCE;
    private LoginMapper loginMapper = LoginMapper.getInstance();
    private CourseTeacherRepository courseTeacherRepository;
    private UserRepository userRepository;
    private ResultRepository resultRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository,
                         CourseTeacherRepository courseTeacherRepository,
                         ResultRepository resultRepository,
                         UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.courseTeacherRepository = courseTeacherRepository;
        this.userRepository = userRepository;
        this.resultRepository = resultRepository;
    }

    public Optional<Course> getById(Long id) {
        return courseRepository.findById(id);
    }

    public Page<CourseDtoC> getAllCourse(PaginationDto pagination) {
        return courseRepository
                .getPaginationRule(pagination)
                .map(courseMapper::courseToCourseCDto);
    }

    public CourseDto getCourseDyId(Long id) {
        CourseDto dto = null;
        if (courseRepository.findById(id).isPresent()) {
            Course course = courseRepository.findById(id).get();
            dto = courseMapper.courseToCourseDto(courseRepository.findById(id).get());
            List<LoginDto> listStudents = course.getStudents().stream().map(loginMapper::mapToDto).collect(Collectors.toList());
            List<LoginDto> listTeachers = course.getTeachers().stream().map(loginMapper::mapToDto).collect(Collectors.toList());
            dto.setStudents(listStudents);
            dto.setTeachers(listTeachers);
        }
        return dto;
    }

    public boolean deleteDyId(Long id) {
        boolean result = false;
        if (courseRepository.findById(id).isPresent()) {
            courseRepository.deleteById(id);
            result = true;
        }
        return result;
    }

    public CourseTeacher.UserCourse beCourseTeacher(Long userId, Long courseId) {
        CourseTeacher.UserCourse uc = new CourseTeacher.UserCourse(userId, courseId);
        CourseTeacher courseTeacher = CourseTeacher.builder()
                .userCourse(uc)
                .build();
        if (!courseTeacherRepository.findById(uc).isPresent()) {
            uc = courseTeacherRepository.save(courseTeacher).getUserCourse();
        } else {
            courseTeacherRepository.delete(courseTeacher);
            uc = new CourseTeacher.UserCourse(0L, 0L);
        }
        return uc;
    }

    public Result.UserCourse beCourseStudent(Long userId, Long courseId) {
        Result.UserCourse uc = new Result.UserCourse(userId, courseId);
        Result result = null;
        if (courseRepository.findById(courseId).isPresent()
                && userRepository.findById(userId).isPresent()) {
            result = Result.builder()
                    .userCourse(uc)
                    .course(courseRepository.findById(courseId).get())
                    .userResult(userRepository.findById(userId).get())
                    .build();
        }
        if (!resultRepository.findById(uc).isPresent() && result != null) {
            uc = resultRepository.save(result).getUserCourse();
        } else {
            resultRepository.delete(result);
            uc = new Result.UserCourse(0L, 0L);
        }
        return uc;
    }

    public void deleteCourseTeacher(Long userId, Long courseId) {
        CourseTeacher.UserCourse uc = new CourseTeacher.UserCourse(userId, courseId);
        CourseTeacher courseTeacher = CourseTeacher.builder()
                .userCourse(uc)
                .build();
        if (courseTeacherRepository.findById(uc).isPresent()) {
            courseTeacherRepository.delete(courseTeacher);
        }
    }

    public Long saveCourse(CourseDto courseDto) {
        return courseRepository
                .save(courseMapper.courseDtoToCourse(courseDto))
                .getId();

    }

    public Long updateCourse(CourseDto courseDto) {
        Long id = 0L;
        if (courseRepository.findById(courseDto.getId()).isPresent()) {
            try {
                Course course = courseRepository.findById(courseDto.getId()).get();
                course = courseMapper.courseDtoToCourse(courseDto);
                id = courseRepository.save(course).getId();
            } catch (Exception e) {
                System.out.println("ОШИБОЧКА ! ! !");
            }
        }
        return id;
    }
}
