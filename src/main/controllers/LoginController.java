package main.controllers;

import main.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("userLogin")
public class LoginController {
    private final static Logger LOGGER = Logger.getLogger(LoginServlet.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String askCredentials() {
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "login", required = true) String login,
                              @RequestParam(value = "password", required = true) String password)
    {
        ModelAndView mav = new ModelAndView();
        LOGGER.debug("Login/Password: " + login + "/" + password);
        if (userService.auth(login, password) != null) {
            mav.addObject("userLogin", login);
            mav.setViewName("redirect:/listStudents");
        } else {
            mav.setViewName("redirect:/");
        }

        return mav;
    }
}
