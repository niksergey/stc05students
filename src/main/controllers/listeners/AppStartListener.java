package main.controllers.listeners;

import main.utils.SendMailTLS;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;


public class AppStartListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        DOMConfigurator.configure(AppStartListener.class.getClassLoader().getResource("log4j.xml"));

        ServletContext ctx = servletContextEvent.getServletContext();
        String adminEmail = ctx.getInitParameter("adminEmail");
        String msg = "[" + new Date() + "] " + ctx.getContextPath() + ctx.getServerInfo();
        SendMailTLS.sendMail(adminEmail, "Students app started", msg,
                null);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
