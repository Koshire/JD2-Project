package com.itacademy.akulov.controller;

import com.itacademy.akulov.dto.LoginDto;
import com.itacademy.akulov.dto.StudentDto;
import com.itacademy.akulov.dto.TeacherDto;
import com.itacademy.akulov.entity.Role;
import com.itacademy.akulov.services.StudentUserService;
import com.itacademy.akulov.services.TeacherUserSerice;
import com.itacademy.akulov.services.UserService;
import com.itacademy.akulov.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private UserService userService;
    private StudentUserService studentUserService;
    private TeacherUserSerice teacherUserSerice;
    private PasswordEncoder passwordEncoder;
    private ResourceBundleMessageSource resourceBundleMessageSource;

    @Autowired
    public UserController(UserService userService,
                          StudentUserService studentUserService,
                          TeacherUserSerice teacherUserSerice,
                          PasswordEncoder passwordEncoder,
                          ResourceBundleMessageSource resourceBundleMessageSource) {
        this.userService = userService;
        this.studentUserService = studentUserService;
        this.teacherUserSerice = teacherUserSerice;
        this.passwordEncoder = passwordEncoder;
        this.resourceBundleMessageSource = resourceBundleMessageSource;
    }


    @GetMapping(value = "/ublock/{id}")
    public String blockUsers(Model model, @PathVariable Long id) {
        boolean result = userService.blockById(id);
        ServiceHelper.setMessage(model, result, resourceBundleMessageSource);
        return "message/message-page";
    }

    @GetMapping(value = "/udelete/{id}")
    public String deleteUsers(Model model, @PathVariable Long id) {
        boolean result = userService.deleteById(id);
        ServiceHelper.setMessage(model, result, resourceBundleMessageSource);
        return "message/message-page";
    }

    @GetMapping(value = "/allusers")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "admin/useradmin";
    }

    private void setUserData(String roles, Model model, Long id) {
        if (roles.contains(Role.ADMIN.getName())
                && userService.getById(id).isPresent()) {
            LoginDto authUser = userService.getById(id).get();
            model.addAttribute("user", authUser);
        } else if (roles.contains(Role.TEACH.getName())
                && teacherUserSerice.getById(id).isPresent()) {
            TeacherDto teacherDto = teacherUserSerice.getById(id).get();
            model.addAttribute("user", teacherDto);
        } else if (roles.contains(Role.STUDY.getName())
                && studentUserService.getById(id).isPresent()) {
            StudentDto studentDto = studentUserService.getById(id).get();
            model.addAttribute("user", studentDto);
        }
    }

    @GetMapping(value = "/uprofile/{id}")
    public String openLoginPage(Model model, @PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        setUserData(auth.toString(), model, id);
        model.addAttribute("userId", id);
        return "user/userprofile";
    }

    @GetMapping(value = "/aprofile/{id}/{role}")
    public String openLoginPage(Model model, @PathVariable Long id,
                                @PathVariable String role) {
        setUserData(role, model, id);
        return "user/userprofile";
    }

    @PostMapping(value = "/upprofile")
    public String updateUser(
            Model model,
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "newPassword") String newPassword,
            @RequestParam(name = "currentPassword") String currentPassword,
            @RequestParam(name = "uname") String firstname,
            @RequestParam(name = "mname") String middleName,
            @RequestParam(name = "lname") String lastName,
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "role") String role
    ) {
        LoginDto loginDto = LoginDto.builder()
                .id(id)
                .role(role)
                .password(!"".equals(newPassword) ? currentPassword : passwordEncoder.encode(newPassword))
                .email(email)
                .name(firstname)
                .middle(middleName)
                .last(lastName)
                .phone(phone)
                .build();
        Long userId = userService.updateUser(loginDto);

        boolean result = userId > 0;
        ServiceHelper.setMessage(model, result, resourceBundleMessageSource);
        return "message/message-page";
    }
}
