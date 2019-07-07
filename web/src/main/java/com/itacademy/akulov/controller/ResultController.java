package com.itacademy.akulov.controller;

import com.itacademy.akulov.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResultController {

    private ResourceBundleMessageSource resourceBundleMessageSource;
    private ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService,
                            ResourceBundleMessageSource resourceBundleMessageSource) {
        this.resourceBundleMessageSource = resourceBundleMessageSource;
        this.resultService = resultService;
    }

    @GetMapping(value = "/results/{id}")
    public String openResultPage(Model model, @PathVariable Long id) {
        model.addAttribute("results", resultService.getByCourse(id));
        return "result/results";
    }

    @PostMapping(value = "/upresult")
    public String updateResult(Model model,
                               @RequestParam(name = "userId") Long courseId,
                               @RequestParam (name = "courseId") Long userId,
                               @RequestParam (name = "result") Integer result) {
        Long apply = resultService.update(userId, courseId, result);
        boolean go = apply > 0;
        model.addAttribute("message",
                go
                        ? resourceBundleMessageSource.getMessage("message.update.success", null, LocaleContextHolder.getLocale())
                        : resourceBundleMessageSource.getMessage("message.update.error", null, LocaleContextHolder.getLocale()));

        return "message/message-page";
    }
}
