package com.itacademy.akulov.servlets;

import com.itacademy.akulov.dto.FindDto;
import com.itacademy.akulov.dto.LoginDto;
import com.itacademy.akulov.dto.ViewDto;
import com.itacademy.akulov.entity.Role;
import com.itacademy.akulov.services.UserService;
import com.itacademy.akulov.services.ViewService;
import com.itacademy.akulov.utils.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/all-logins"})
public class AllLoginsServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String role = req.getParameter("role");
        FindDto findDto = FindDto.builder()
                .fio(req.getParameter("fio"))
                .blockList(Boolean.getBoolean(req.getParameter("blocklist")))
                .build();
        if (!role.isEmpty()) {
            findDto.setRole(Role.valueOf(role));
        }

        ViewDto viewDto = ViewService.calculate(ViewDto.builder()
                .limit(Long.parseLong(req.getParameter("view")))
                .page(Long.parseLong(req.getParameter("page")))
                .size(userService.getSizeByCustom(findDto))
                .build());

        findDto.setLimit(viewDto.getLimit());
        findDto.setOffset(viewDto.getOffset());

        List<LoginDto> list = userService.getByCustomLO(findDto);

        req.setAttribute("allLogins", list);
        req.setAttribute("pages", viewDto.getNums());
        req.setAttribute("view", viewDto.getLimit());
        req.setAttribute("fio", findDto.getFio());
        req.setAttribute("page",
                viewDto.getPage() == 0 ? 1 : viewDto.getPage());

        this.getServletContext()
                .getRequestDispatcher(JspPath.get("all-logins"))
                .forward(req, resp);
    }
}
