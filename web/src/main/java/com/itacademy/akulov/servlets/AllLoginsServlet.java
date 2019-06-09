package com.itacademy.akulov.servlets;

import com.itacademy.akulov.config.Config;
import com.itacademy.akulov.dto.FindDto;
import com.itacademy.akulov.dto.LoginDto;
import com.itacademy.akulov.entity.Role;
import com.itacademy.akulov.services.UserService;
import com.itacademy.akulov.utils.JspPath;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/all-logins"})
public class AllLoginsServlet extends HttpServlet {

    private ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

    private UserService userService = applicationContext.getBean(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String role = req.getParameter("role");
        FindDto findDto = FindDto.builder()
                .fio(req.getParameter("fio"))
                .blockList(Boolean.getBoolean(req.getParameter("blocklist")))
                .page(Long.parseLong(req.getParameter("page")))
                .viewLimit(Long.parseLong(req.getParameter("view")))
                .build();
        if (!role.isEmpty()) {
            findDto.setRole(Role.valueOf(role));
        }
        findDto.setSize(userService.getSizeByCustom(findDto));
        findDto.setPages(
                findDto.getSize() % findDto.getViewLimit() > 0
                        ? findDto.getSize() / findDto.getViewLimit() + 1
                        : findDto.getSize() / findDto.getViewLimit());

        List<LoginDto> list = userService.getByCustomLO(findDto);

        req.setAttribute("allLogins", list);
        req.setAttribute("pages", findDto.getPages());
        req.setAttribute("view", findDto.getViewLimit());
        req.setAttribute("fio", findDto.getFio());
        req.setAttribute("page",
                findDto.getPage() == 0 ? 1 : findDto.getPage());

        this.getServletContext()
                .getRequestDispatcher(JspPath.get("all-logins"))
                .forward(req, resp);
    }
}
