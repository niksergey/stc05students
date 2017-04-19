package main.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.models.pojo.Student;
import main.services.StudentService;
import main.services.StudentServiceInterface;

/**
 * Created by sergey on 19.04.17.
 */
public class ListController extends HttpServlet {

    private static StudentServiceInterface studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("value", "Hello student");

        req.setAttribute("list", studentService.getAllStudents());
        getServletContext().getRequestDispatcher("/list.jsp").forward(req, resp);
    }
}
