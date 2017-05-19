package main.controllers.listeners;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class MySessionListener implements HttpSessionListener {
    private final static Logger LOGGER =
            Logger.getLogger(MySessionListener.class);
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        LOGGER.debug("session " + httpSessionEvent.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
