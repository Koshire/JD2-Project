package com.itacademy.akulov.controller;

import com.google.gson.Gson;
import com.itacademy.akulov.dto.LoginDto;
import com.itacademy.akulov.dto.ResponseDto;
import com.itacademy.akulov.entity.CourseType;
import com.itacademy.akulov.entity.Role;
import com.itacademy.akulov.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private Gson gson;
    private ResourceBundleMessageSource resourceBundleMessageSource;

    @Autowired
    public AuthController(UserService userService,
                          PasswordEncoder passwordEncoder,
                          Gson gson,
                          ResourceBundleMessageSource resourceBundleMessageSource) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.gson = gson;
        this.resourceBundleMessageSource = resourceBundleMessageSource;
    }

    @GetMapping(value = "/login")
    public String openLoginPage(Model model, @RequestParam(name = "message", required = false) String message) {
        model.addAttribute("message", message);
        return "auth/login";
    }

    @PostMapping(value = "/hometranslate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    String translate() {
        Map<String, Map<String, String>> map = new HashMap<>();
        Map<String, String> ru = new HashMap<>();
        Map<String, String> en = new HashMap<>();

        en.put("details", "Details");
        ru.put("details", "Подробности");
        en.put("duration", "Course duration: ");
        ru.put("duration", "Длительность курса: ");
        en.put("start", "Course start: ");
        ru.put("start", "Начало курса: ");
        en.put("description", "Course description: ");
        ru.put("description", "Начало курса: ");
        en.put("name", "Course name: ");
        ru.put("name", "Название курса: ");
        en.put("gratis", "Goog day: ");
        ru.put("gratis", "Добрый день: ");
        en.put("userexist", "User already exist.");
        ru.put("userexist", "Пользователь уже существует.");
        en.put("incorremail", "Email is incorrect.");
        ru.put("incorremail", "Электронная почта не корректна.");
        en.put("corremail", "Email is correct.");
        ru.put("corremail", "Электронная почта корректна.");
        en.put("incorrpass", "Password is incorrect or less than 7 symbol.");
        ru.put("incorrpass", "Пароль не корректен или содержит менее 7 символов");
        en.put("corrpass", "Password is correct.");
        ru.put("corrpass", "Пароль корректный.");
        en.put("matchpass", "Password confirmed.");
        ru.put("matchpass", "Пароль подтвержден.");
        en.put("nomatchpass", "Password not confirmed.");
        ru.put("nomatchpass", "Пароль не совпадает.");
        en.put("nophone", "Phone not correct.");
        ru.put("nophone", "Телефон введен не корректно.");
        en.put("phone", "Phone correct.");
        ru.put("phone", "Телефон введен корректно.");

        map.put("en_US", en);
        map.put("ru_RU", ru);

        return gson.toJson(map);
    }

    @PostMapping("/checklogin")
    public @ResponseBody
    String checkLogin(@RequestBody String login) {
        return userService.getLogin(login).isPresent() ? "true" : "false";
    }

    @GetMapping("/error-registration")
    public String openErrorLoginPage(Model model) {
        return "auth/error-registration";
    }

    @GetMapping("/registration")
    public String openRegPage() {
        return "auth/registration";
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    String registerUser(Model model,
                        @RequestBody String jsonRegData) {

        StringBuilder result = new StringBuilder();
        Long id = -1L;
        Gson g = new Gson();
        LoginDto loginDto = g.fromJson(jsonRegData, LoginDto.class);
        loginDto.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        if (loginDto.getRole().equals(Role.STUDY.getName())) {
            id = userService.saveStudentUser(loginDto);

            //result.append(resourceBundleMessageSource.getMessage("reg.student.ready", null, LocaleContextHolder.getLocale()));
        } else if (loginDto.getRole().equals(Role.TEACH.getName())) {
            id = userService.saveTeacherUser(loginDto);
            //result.append(resourceBundleMessageSource.getMessage("reg.teacher.ready", null, LocaleContextHolder.getLocale()));
        }
        return g.toJson(ResponseDto.builder()
                .resp(id != -1L ? "login" : "error-registration")
                .mess(result.toString())
                .build());
    }

    @PostMapping(value = "/getuser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    String getAuthUserDetail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        StringBuilder st = new StringBuilder();
        if (userService.getLogin(auth.getName()).isPresent()) {
            LoginDto loginDto = userService.getLogin(auth.getName()).get();
            loginDto.setPassword("");
            st.append(new Gson().toJson(loginDto));
        }
        return st.toString();
    }

    @GetMapping("/home")
    public String openHomePage(Model model) {
        model.addAttribute("roles", CourseType.values());
        return "home/home";
    }
}
