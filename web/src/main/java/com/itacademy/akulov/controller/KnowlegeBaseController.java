package com.itacademy.akulov.controller;

import com.itacademy.akulov.dto.CourseDtoC;
import com.itacademy.akulov.dto.KnowlegeBaseDto;
import com.itacademy.akulov.dto.LoginDto;
import com.itacademy.akulov.services.KnowlegeBaseService;
import com.itacademy.akulov.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.time.LocalDate;

@Controller
//@Log4j
public class KnowlegeBaseController extends HttpServlet {

    private KnowlegeBaseService knowlegeBaseService;
    private ResourceBundleMessageSource resourceBundleMessageSource;
    private ServletContext servletContext;


    private static final String FILE_NAME = "kb.txt";
    private static final String PATH = "/";

    @Autowired
    public KnowlegeBaseController(KnowlegeBaseService knowlegeBaseService,
                                  ResourceBundleMessageSource resourceBundleMessageSource,
                                  ServletContext servletContext) {
        this.knowlegeBaseService = knowlegeBaseService;
        this.resourceBundleMessageSource = resourceBundleMessageSource;
        this.servletContext = servletContext;
    }

    @GetMapping(value = "/getkb")
    public String getAllKnowlegeBase(Model model) {
        model.addAttribute("kbs", knowlegeBaseService.getAllKb());
        return "knowlege-base/show-kb";
    }

    @PostMapping(value = "/kbdata")
    public String sendKbData(Model model,
                             @RequestParam(name = "courseId") Long courseId,
                             @RequestParam(name = "userId") Long userId,
                             @RequestParam(name = "date") String date,
                             @RequestParam(name = "text") String text) {
        KnowlegeBaseDto knowlegeBaseDto = KnowlegeBaseDto.builder()
                .text(text)
                .userKnowlegeBase(LoginDto.builder()
                        .id(userId)
                        .build())
                .localDate(date)
                .courseDtoC(CourseDtoC.builder()
                        .id(courseId)
                        .build())
                .build();
        Long id = knowlegeBaseService.createKb(knowlegeBaseDto);
        ServiceHelper.setMessage(model, id > 0, resourceBundleMessageSource);
        return "message/message-page";
    }

    @GetMapping(value = "/createkb/{courseId}/{userId}")
    public String createKnowlegeBase(Model model,
                                     @PathVariable(name = "courseId") Long courseId,
                                     @PathVariable(name = "userId") Long userId) {
        model.addAttribute("courseId", courseId);
        model.addAttribute("userId", userId);
        model.addAttribute("date", LocalDate.now());
        return "knowlege-base/create-kb";
    }

    @GetMapping(value = "/getkbfile/{id}")
    public ResponseEntity<byte[]> getKnowlegeBaseFile(
            Model model,
            @PathVariable(name = "id") Long id) throws IOException {
        byte [] hi = "Hello".getBytes();
        System.out.println();
        KnowlegeBaseDto knowlegeBaseDto = knowlegeBaseService.convertById(id);
        return ServiceHelper.setResource(knowlegeBaseDto.toString().getBytes(), "kb.txt");
    }

    @GetMapping(value = "/kbdelete/{id}")
    public String kbdelete(Model model, @PathVariable(name = "id") Long id) {
        boolean result = knowlegeBaseService.delete(id);
        ServiceHelper.setMessage(model, result, resourceBundleMessageSource);
        return "message/message-page";
    }
}
