package by.itacademy.akulov.servlets;

import by.itacademy.akulov.dto.LoginDto;
import by.itacademy.akulov.services.UserService;
import by.itacademy.akulov.utils.JspPath;

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
        List<LoginDto> list = userService.getAll();
        req.setAttribute("allLogins", list);
        this.getServletContext()
                .getRequestDispatcher(JspPath.get("all-logins"))
                .forward(req, resp);
    }
}
