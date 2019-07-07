package com.itacademy.akulov.controller;

import com.google.gson.Gson;
import com.itacademy.akulov.dto.CourseCommentsDtoReq;
import com.itacademy.akulov.dto.CourseDto;
import com.itacademy.akulov.dto.PaginationDto;
import com.itacademy.akulov.dto.ResponseDto;
import com.itacademy.akulov.entity.CourseTeacher;
import com.itacademy.akulov.entity.CourseType;
import com.itacademy.akulov.entity.Result;
import com.itacademy.akulov.services.CourseCommentsService;
import com.itacademy.akulov.services.CourseService;
import com.itacademy.akulov.util.ServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private CourseService courseService;
    private CourseCommentsService courseCommentsService;
    private Gson gson;
    private ResourceBundleMessageSource resourceBundleMessageSource;

    @Autowired
    public CourseController(CourseService courseService,
                            Gson gson,
                            ResourceBundleMessageSource resourceBundleMessageSource,
                            CourseCommentsService courseCommentsService) {
        this.resourceBundleMessageSource = resourceBundleMessageSource;
        this.courseService = courseService;
        this.gson = gson;
        this.courseCommentsService = courseCommentsService;
    }

    @GetMapping(value = "/course")
    public String getCourseProfile(Model model, @RequestParam(name = "id") Long id) {
        CourseDto courseDto = courseService.getCourseDyId(id);
        model.addAttribute("types", CourseType.values());
        model.addAttribute("courseData", courseDto);
        return "course/courseprofile";
    }

    @GetMapping(value = "/createcourse")
    public String getCourseOpen(Model model) {
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("types", CourseType.values());
        return "course/coursecreate";
    }

    @PostMapping(value = "/beteacher", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    String beTeacher(@RequestBody String course) {
        CourseDto courseDto = gson.fromJson(course, CourseDto.class);
        StringBuilder st = new StringBuilder();
        String addTeacher = resourceBundleMessageSource.getMessage("add.teacher", null, LocaleContextHolder.getLocale());
        String removeTeacher = resourceBundleMessageSource.getMessage("remove.teacher", null, LocaleContextHolder.getLocale());
        if (courseDto.getInitUser() != 0 && courseDto.getId() != 0) {
            CourseTeacher.UserCourse uc = courseService.beCourseTeacher(
                    courseDto.getInitUser(), courseDto.getId());
            st.append(uc.getCourseId() != 0 && uc.getUserId() != 0
                    ? addTeacher : removeTeacher);
        }

        return gson.toJson(ResponseDto.builder()
                .mess(st.toString())
                .build());
    }

    @PostMapping(value = "/bestudent", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    String beStudent(@RequestBody String course) {
        CourseDto courseDto = gson.fromJson(course, CourseDto.class);
        StringBuilder st = new StringBuilder();
        String addStudent = resourceBundleMessageSource.getMessage("add.student", null, LocaleContextHolder.getLocale());
        String removeStudent = resourceBundleMessageSource.getMessage("remove.student", null, LocaleContextHolder.getLocale());

        if (courseDto.getInitUser() != 0 && courseDto.getId() != 0) {
            Result.UserCourse uc = courseService.beCourseStudent(
                    courseDto.getInitUser(), courseDto.getId());
            st.append(uc.getCourseId() != 0 && uc.getUserId() != 0
                    ? addStudent : removeStudent);
        }

        return gson.toJson(ResponseDto.builder()
                .mess(st.toString())
                .build());
    }

    @PostMapping(value = "/createcourse", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    String getCourseCreate(@RequestBody String course) {
        CourseDto courseDto = gson.fromJson(course, CourseDto.class);
        if (courseDto.getType().equals("")) {
            courseDto.setType(CourseType.FULL_TIME.getName());
        }
        Long courseId = 0L;
        StringBuilder st = new StringBuilder();
        if (courseDto.getInitUser() != 0) {
            courseId = courseService.saveCourse(courseDto);
            courseDto.setId(courseId);
            String addCourse = resourceBundleMessageSource.getMessage("add.new.course", null, LocaleContextHolder.getLocale());
            st.append(addCourse);
        }

        if (courseId != 0) {
            CourseTeacher.UserCourse uc = courseService.beCourseTeacher(
                    courseDto.getInitUser(), courseDto.getId());
            String addTeacher = resourceBundleMessageSource.getMessage("add.teacher", null, LocaleContextHolder.getLocale());
            st.append(addTeacher);
        }

        return gson.toJson(ResponseDto.builder()
                .mess(st.toString())
                .build());
    }

    @PostMapping(value = "/getcourses", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    String getAllCourses(@RequestBody String pagination) {
        PaginationDto paginationDto = gson.fromJson(pagination, PaginationDto.class);
        return gson.toJson(courseService.getAllCourse(paginationDto));
    }

    @PostMapping(value = "/coursecomments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    String getAllCourseComments(@RequestBody String pagination) {
        PaginationDto paginationDto = gson.fromJson(pagination, PaginationDto.class);
        return gson.toJson(courseCommentsService.getParamCourseComments(paginationDto));
    }

    @GetMapping(value = "/delccomments/{id}")
    public String deleteMyCourseComments(Model model, @PathVariable(name = "id") Long commentId) {
        boolean result = courseCommentsService.deleteById(commentId);
        ServiceHelper.setMessage(model, result, resourceBundleMessageSource);
        return "message/message-page";
    }

    @GetMapping(value = "/myccomments/{id}")
    public String getMyCourseComments(Model model, @PathVariable(name = "id") Long userId) {
        model.addAttribute("comments", courseCommentsService.getAllByUserId(userId));
        model.addAttribute("iserId", userId);
        return "user/ccomments";
    }

    @GetMapping(value = "/getcourse/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    String getById(@PathVariable(value = "id") Long id) {
        CourseDto courseDto = courseService.getCourseDyId(id);
        return gson.toJson(courseDto);
    }

    @GetMapping(value = "/deletecourse/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    String deleteById(@PathVariable(value = "id") Long id) {
        boolean result = courseService.deleteDyId(id);
        return result ? gson.toJson(ResponseDto.builder()
                .resp(resourceBundleMessageSource.getMessage("course.delete", null, LocaleContextHolder.getLocale()))
                .mess(resourceBundleMessageSource.getMessage("course.button.show", null, LocaleContextHolder.getLocale()))
                .build())
                : gson.toJson(ResponseDto.builder()
                .resp(resourceBundleMessageSource.getMessage("course.delete.error", null, LocaleContextHolder.getLocale()))
                .mess(resourceBundleMessageSource.getMessage("course.button.show", null, LocaleContextHolder.getLocale()))
                .build());
    }

    @PostMapping(value = "/updatecourse", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    String updateById(@RequestBody String course) {
        CourseDto courseDto = gson.fromJson(course, CourseDto.class);
        Long id = courseService.updateCourse(courseDto);
        return id > 0 ? gson.toJson(ResponseDto.builder()
                .resp(resourceBundleMessageSource.getMessage("course.update", null, LocaleContextHolder.getLocale()))
                .mess(resourceBundleMessageSource.getMessage("course.button.show", null, LocaleContextHolder.getLocale()))
                .build())
                : gson.toJson(ResponseDto.builder()
                .resp(resourceBundleMessageSource.getMessage("course.update.error", null, LocaleContextHolder.getLocale()))
                .mess(resourceBundleMessageSource.getMessage("course.button.show", null, LocaleContextHolder.getLocale()))
                .build());
    }

    @PostMapping(value = "/createcomment", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    String getCommentCreate(@RequestBody String course) {
        CourseCommentsDtoReq cdtr = gson.fromJson(course, CourseCommentsDtoReq.class);
        return courseCommentsService.saveComment(cdtr) ? gson.toJson(ResponseDto.builder()
                .resp(resourceBundleMessageSource.getMessage("course.comment.create", null, LocaleContextHolder.getLocale()))
                .build())
                : gson.toJson(ResponseDto.builder()
                .resp(resourceBundleMessageSource.getMessage("course.comment.error", null, LocaleContextHolder.getLocale()))
                .build());
    }
}
