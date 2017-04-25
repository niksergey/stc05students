package main.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import main.services.StudentServiceImpl;
import main.services.StudentService;
import org.apache.log4j.Logger;


public class ListController extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(ListController.class);

    private static StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("infoText", "Список студентов");
        req.setAttribute("list", studentService.getAllStudents());
        getServletContext().getRequestDispatcher("/jsp/listStudents.jsp").forward(req, resp);
    }
}
