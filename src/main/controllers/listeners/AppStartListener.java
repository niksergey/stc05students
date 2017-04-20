package main.controllers.listeners;

import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by sergey on 20.04.17.
 */
public class AppStartListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DOMConfigurator.configure("fileLog.xml");

        ServletContext ctx = servletContextEvent.getServletContext();
        String adminEmail = ctx.getInitParameter("adminEmail");
        SendMailTLS.sendMail("me@nikser.ru", "log from Library",
                "hello", null);

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
