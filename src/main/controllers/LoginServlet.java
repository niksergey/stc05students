package main.controllers;

import main.services.UserService;
import main.services.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginServlet extends HttpServlet {
    @Autowired
    private UserService userService;

    private final static Logger LOGGER = Logger.getLogger(LoginServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        LOGGER.debug("Login/Password: " + login + "/" + password);
        if (userService.auth(login, password) != null) {
            req.getSession().setAttribute("userLogin", login);
            resp.sendRedirect(req.getContextPath() + "/listStudents");
        } else {
            resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
        }
    }
}
