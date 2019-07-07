package com.itacademy.akulov.controller;

import com.itacademy.akulov.entity.CourseType;
import com.itacademy.akulov.services.KnowlegeBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class KnowlegeBaseController {

    KnowlegeBaseService knowlegeBaseService;
    private ResourceBundleMessageSource resourceBundleMessageSource;

    @Autowired
    public KnowlegeBaseController(KnowlegeBaseService knowlegeBaseService,
                                  ResourceBundleMessageSource resourceBundleMessageSource) {
        this.knowlegeBaseService = knowlegeBaseService;
        this.resourceBundleMessageSource = resourceBundleMessageSource;
    }

    @GetMapping(value = "/createcourse")
    public String getCourseOpen(Model model) {
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("types", CourseType.values());
        return "course/coursecreate";
    }

    @PostMapping(value = "/getkb")
    public String getAllCourses(Model model) {
        model.addAttribute("kbs", knowlegeBaseService.getAllKb());
        return "knowlege-base/show-kb";
    }

}
