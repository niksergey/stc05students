package main.controllers;


import main.services.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentsController {
    private final static Logger LOGGER = Logger.getLogger(StudentsController.class);

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String showList(Model model) {
        model.addAttribute("students", studentService.getAllStudents());

        return "studentsList";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/students/add", method = RequestMethod.GET)
    public String showAddForm(Model model) {
        return "studentAddForm";
    }

    @RequestMapping(value = "/students/add", method = RequestMethod.POST)
    public ModelAndView addStudent(@RequestParam("name") String name,
                                   @RequestParam("age") Integer age,
                                   @RequestParam("groupId") Integer groupId) {
        ModelAndView mav = new ModelAndView();
        if (!studentService.addStudent(name, age, groupId) ) {
            LOGGER.warn("Error during insert student");
            mav.addObject("controllerMsg", "Не удалось добавить студента");
        }
        mav.setViewName("redirect:/students");
        return mav;
    }

    @RequestMapping(value = "/students/edit", method = RequestMethod.GET)
    public String showEditForm(Model model, @RequestParam("id") Integer id) {
        model.addAttribute("student", studentService.getById(id));
        return "studentEditForm";
    }

    @RequestMapping(value = "/students/edit", method = RequestMethod.POST)
    public ModelAndView makeEdit(@RequestParam("name") String name,
                                 @RequestParam("age") Integer age,
                                 @RequestParam("groupId") Integer groupId,
                                 @RequestParam("id") Integer id) {

        ModelAndView mav = new ModelAndView();
        if (studentService.updateStudent(name, age, groupId, id) ) {
            mav.setViewName("redirect:/students");
        } else {
            mav.addObject("id", id);
            mav.setViewName("redirect:/students/edit");
        }
        return mav;
    }

    @RequestMapping(value = "/students/delete", method = RequestMethod.POST)
    public ModelAndView deleteStudent(@RequestParam("id") Integer id) {
        studentService.deleteStudent(id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/students");
        return mav;
    }
}