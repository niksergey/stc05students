package main.controllers;

import main.services.UserService;
import main.services.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginServlet extends HttpServlet {
    private static UserService userService = new UserServiceImpl();
    private final static Logger LOGGER = Logger.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        LOGGER.debug("Login/Password: " + login + "/" + password +  ":" + req.getContextPath());
        if (userService.auth(login, password) != null) {
            req.getSession().setAttribute("userLogin", login);
            resp.sendRedirect(req.getContextPath() + "/listStudents");
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
}
