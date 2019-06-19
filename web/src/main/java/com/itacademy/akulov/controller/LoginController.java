package com.itacademy.akulov.controller;

import com.itacademy.akulov.dto.PaginationDto;
import com.itacademy.akulov.dto.LoginDto;
import com.itacademy.akulov.entity.Role;
import com.itacademy.akulov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.itacademy.akulov.util.ServiceHelper.isPresent;

@Controller
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String openLoginPage(Model model, @RequestParam(value = "role", required = false) Role role,
                                @RequestParam(value = "fio", required = false) String fio,
                                @RequestParam(value = "blocklist", required = false) Boolean blocklist,
                                @RequestParam("page") Long page,
                                @RequestParam("view") Long view
    ) {
        PaginationDto paginationDto = PaginationDto.builder()
                .fio(fio)
                .blockList(blocklist)
                .page(page)
                .viewLimit(view)
                .build();
        if (isPresent(role)) {
            paginationDto.setRole(role);
        }
        paginationDto.setSize(userService.getSizeByCustom(paginationDto));

        paginationDto.setPages(
                paginationDto.getSize() % paginationDto.getViewLimit() > 0
                        ? paginationDto.getSize() / paginationDto.getViewLimit() + 1
                        : paginationDto.getSize() / paginationDto.getViewLimit());

        List<LoginDto> list = userService.getByCustomLO(paginationDto);


        model.addAttribute("roles", Role.values());
        model.addAttribute("role", isPresent(paginationDto.getRole()) ?
                paginationDto.getRole().getName() :
                "");
        model.addAttribute("allLogins", list);
        model.addAttribute("pages", paginationDto.getPages());
        model.addAttribute("view", paginationDto.getViewLimit());
        model.addAttribute("fio", paginationDto.getFio());
        model.addAttribute("page",
                paginationDto.getPage() == 0 ? 1 : paginationDto.getPage());
        return "login";
    }
}
