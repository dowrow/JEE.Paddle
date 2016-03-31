package web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;

import business.controllers.TrainingController;
import business.wrapper.TrainingCreationWrapper;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class Presenter {
    
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
    	if (bindingResult.hasErrors()) {
    		List<FieldError> errors = bindingResult.getFieldErrors(); 
    		String invalidFields = "";
    		for (FieldError error : errors) {
    			invalidFields += error.getField();
    			if (errors.indexOf(error) != (errors.size() - 1)) {
    				invalidFields += ",";
    			}
    		}
    		
    		model.addAttribute("error", "El valor de estos campos no es válido: " + invalidFields);
    	} else {
    		if (trainingController.createTraining("trainer01", wrapper) != null) {
        		model.addAttribute("createdTraining", wrapper);
        	} else {
        		model.addAttribute("error", "Esa pista no está disponible en el horario seleccionado.");
        	}
    	}
    	return theme + "/create-training-result";
    }
    
    @RequestMapping("/show-trainings")
    public String showTrainings(Model model) {
    	model.addAttribute("trainings", trainingController.getAvailableTrainings());
        return theme + "/show-trainings";
    }
}