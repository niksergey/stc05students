package main.controllers;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import main.services.StudentServiceImpl;
import main.services.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


public class ListController extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(ListController.class);

    @Autowired
    private StudentService studentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("infoText", "Список студентов");
        req.setAttribute("list", studentService.getAllStudents());
        getServletContext().getRequestDispatcher("/jsp/listStudents.jsp").forward(req, resp);
    }
}
