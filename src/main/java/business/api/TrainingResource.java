package business.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import business.api.exceptions.InvalidDateException;
import business.api.exceptions.InvalidTrainingException;
import business.api.exceptions.NotFoundCourtIdException;
import business.controllers.CourtController;
import business.controllers.TrainingController;
import business.wrapper.TrainingCreationWrapper;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.TRAININGS)
public class TrainingResource {

    private TrainingController trainingController;
    
    private CourtController courtController;
    
    @Autowired
    public void setTrainingController(TrainingController trainingController) {
        this.trainingController = trainingController;
    }
    
    
    @Autowired
    public void setCourtController(CourtController courtController) {
        this.courtController = courtController;
    }
    

    @RequestMapping(method = RequestMethod.POST)
    public void createTraining(@AuthenticationPrincipal User activeUser, 
    		@RequestBody TrainingCreationWrapper wrapper) throws
    		NotFoundCourtIdException, InvalidDateException, InvalidTrainingException {
    	if (!courtController.exist(wrapper.getCourtId())) {
    		throw new NotFoundCourtIdException();
    	}
    	if (!trainingController.isValidStartDate(wrapper.getStartDate())) {
    		throw new InvalidDateException("La fecha de inicio del entrenamiento no es válida");
    	}
    	if (!trainingController.createTraining(activeUser.getUsername(), wrapper)) {
    		throw new InvalidTrainingException("No pudo reservar la pista en la fecha seleccionada");
    	}
    }
}
