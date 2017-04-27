package main.controllers;


import main.services.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/listStudents")
public class ListControllerMVC {
    private final static Logger LOGGER = Logger.getLogger(ListController.class);

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public String showList(Model model) {
        LOGGER.info("IN NEW LIST");
        model.addAttribute("infoText", "Список студентов");
        model.addAttribute("list", studentService.getAllStudents());
        return "listStudents";
    }
}
