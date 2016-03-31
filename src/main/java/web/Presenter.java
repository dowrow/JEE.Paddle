package web;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import business.controllers.TrainingController;
import business.wrapper.TrainingCreationWrapper;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class Presenter {

    @Autowired
    private ServletContext servletContext;
    
    @Autowired
    private TrainingController trainingController;
    
    private String theme = "thymeleaf";

    public Presenter() {
    }

    // Se ejecuta siempre y antes. Añade un atributo al Model
    @ModelAttribute("now")
    public String now() {
        return new SimpleDateFormat("EEEE, d MMM yyyy HH:mm:ss").format(new Date());
    }

    @RequestMapping("/home")
    public String home(Model model) {
        return theme + "/home";
    }
    
    @RequestMapping(value = "/create-training", method = RequestMethod.GET)
    public String createTraining(Model model) {
    	model.addAttribute("trainingCreationWrapper", new TrainingCreationWrapper());
    	return theme + "/create-training";
    }
    
    @RequestMapping(value = "/create-training", method = RequestMethod.POST)
    public String createTrainingSubmit(TrainingCreationWrapper wrapper, BindingResult bindingResult, Model model) {
    	
        return theme + "/create-training-result";
    }
    
    @RequestMapping("/show-trainings")
    public String showTrainings(Model model) {
    	model.addAttribute("trainings", trainingController.getAvailableTrainings());
        return theme + "/show-trainings";
    }
}