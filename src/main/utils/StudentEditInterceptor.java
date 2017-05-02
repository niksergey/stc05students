package main.utils;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

public class StudentEditInterceptor extends HandlerInterceptorAdapter {
    private final static Logger LOGGER = Logger.getLogger(StudentEditInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Principal userPrincipal = request.getUserPrincipal();

        LOGGER.debug("In pre-handle");
        if (userPrincipal != null) {
            LOGGER.debug(userPrincipal.getName());
        }
//        if (userPrincipal.getAuthorities().contains("ROLE_ADMIN") ) {
//            LOGGER.debug("ADMIN");
//        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.debug("In post handle");
        super.postHandle(request, response, handler, modelAndView);
    }
}
