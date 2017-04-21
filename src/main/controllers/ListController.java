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
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


public class ListController extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(ListController.class);

    private static StudentServiceInterface studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("value", "Hello student");
        req.setAttribute("list", studentService.getAllStudents());
        getServletContext().getRequestDispatcher("/listStudents.jsp").forward(req, resp);
    }
}
