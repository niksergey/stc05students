package main.controllers;

import main.services.StudentServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManageStudentServlet extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(ManageStudentServlet.class);
    private  final static StudentServiceImpl studentService = new StudentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqType = req.getParameter("requestType");
        if (reqType == null) {
            resp.sendRedirect(req.getContextPath() + "/listStudents");
            return;
        }
        switch (reqType) {
            case "delete":
                String id = req.getParameter("studentId");
                LOGGER.debug("studentId " + id);
                if (!studentService.deleteStudent(id)) {
                    resp.sendRedirect(req.getContextPath() + "/jsp/error.jsp");
                    return;
                }
                break;
            case "add":
                String name = req.getParameter("name");
                String age = req.getParameter("age");
                String groupId = req.getParameter("groupId");
                if (!studentService.addStudent(name, age, groupId)) {
                    resp.sendRedirect(req.getContextPath() + "/error.jsp");
                    return;
                }
                break;
        }
        req.setAttribute("userLogin", "admin");
        resp.sendRedirect(req.getContextPath() + "/listStudents");
    }
}
