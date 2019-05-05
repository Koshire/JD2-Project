package by.itacademy.akulov.servlets;

import by.itacademy.akulov.entity.CourseType;
import by.itacademy.akulov.services.CourseTypeService;
import by.itacademy.akulov.utils.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet({"/all-types"})
public class CourseTypeServlet extends HttpServlet {

    private CourseTypeService courseTypeService = CourseTypeService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<CourseType> set = this.courseTypeService.getAll();
        req.setAttribute("allTypes", set);
        this.getServletContext()
                .getRequestDispatcher(JspPath.get("course-types"))
                .forward(req, resp);
    }
}
