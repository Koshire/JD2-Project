package com.itacademy.akulov.services;

import com.itacademy.akulov.dto.CourseCommentsDto;
import com.itacademy.akulov.dto.CourseCommentsDtoReq;
import com.itacademy.akulov.dto.PaginationDto;
import com.itacademy.akulov.entity.Course;
import com.itacademy.akulov.entity.CourseComments;
import com.itacademy.akulov.entity.User;
import com.itacademy.akulov.mapper.CourseCommentsMapper;
import com.itacademy.akulov.repository.CourseCommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseCommentsService {

    private CourseCommentsRepository courseCommentsRepository;
    private UserService userService;
    private CourseService courseService;
    private CourseCommentsMapper courseCommentsMapper = CourseCommentsMapper.getInstance();

    @Autowired
    public CourseCommentsService(CourseCommentsRepository courseCommentsRepository, UserService userService, CourseService courseService) {
        this.courseCommentsRepository = courseCommentsRepository;
        this.userService = userService;
        this.courseService = courseService;
    }

    public Page<CourseCommentsDto> getParamCourseComments(PaginationDto paginationDto) {
        Pageable pageable = PageRequest.of(paginationDto.getPage(), paginationDto.getPageSize());
        Page<CourseComments> page = courseCommentsRepository.findAllByCourseId(Long.parseLong(paginationDto.getParameter1()), pageable);
        long total = page.getTotalElements();
        Integer pages = page.getTotalPages();
        List<CourseCommentsDto> list = page.get().map(courseCommentsMapper::entityToDto).collect(Collectors.toList());

        return new PageImpl<CourseCommentsDto>(list, pageable, total);
    }

    public List<CourseCommentsDto> getAllByUserId(Long userId) {
        return courseCommentsRepository.findAllByUserId(userId)
                .stream()
                .map(courseCommentsMapper::entityToDto).collect(Collectors.toList());
    }

    public boolean deleteById(Long commentId) {
        long result = 0L;
        if (courseCommentsRepository.existsById(commentId)) {
            courseCommentsRepository.deleteById(commentId);
            result =1L;
        }
        return result > 0;
    }

    public boolean saveComment(CourseCommentsDtoReq courseCommentsDtoReq) {
        CourseComments courseComments = null;
        int result = 0;
        if (userService.getById(courseCommentsDtoReq.getUserId()).isPresent()
                && courseService.getById(courseCommentsDtoReq.getCourseId()).isPresent()) {
            courseComments = courseCommentsRepository.save(CourseComments.builder()
                    .user(new User().setId(courseCommentsDtoReq.getUserId()))
                    .comment(courseCommentsDtoReq.getComment())
                    .course(Course.builder().id(courseCommentsDtoReq.getCourseId()).build())
                    .build());
            result = 1;
        }
        return result > 0;
    }
}
