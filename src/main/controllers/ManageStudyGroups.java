package main.controllers;

import main.services.GroupService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManageStudyGroups extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(ManageStudyGroups.class);

    @Autowired
    private GroupService studyGroupsService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqType = req.getParameter("requestType");
        if (reqType == null) {
            resp.sendRedirect(req.getContextPath() + "/groups/list");
            return;
        }
        switch (reqType) {
            case "delete":
                String id = req.getParameter("groupId");
                int idGroup = Integer.valueOf(id);
                LOGGER.debug("groupId " + idGroup);
                if (!studyGroupsService.deleteGroup(idGroup)) {
                    resp.sendRedirect(req.getContextPath() + "/jsp/error.jsp");
                    return;
                }
                break;
            case "add":
                String name = req.getParameter("name");
                if (!studyGroupsService.addGroup(name)) {
                    resp.sendRedirect(req.getContextPath() + "/jsp/error.jsp");
                    return;
                }
                break;
        }
        req.setAttribute("userLogin", "admin");
        resp.sendRedirect(req.getContextPath() + "/groups/list");
    }
}
