package business.api;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import business.api.exceptions.InvalidDateException;
import business.api.exceptions.InvalidTrainingException;
import business.api.exceptions.NotFoundCourtIdException;
import business.api.exceptions.NotFoundTrainingIdException;
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
    	this.validateDates(wrapper.getStartDate(), wrapper.getEndDate());
    	if (!trainingController.createTraining(activeUser.getUsername(), wrapper)) {
    		throw new InvalidTrainingException("No se pudo reservar la pista en las fechas seleccionada");
    	}
    	
    }

	private void validateDates(Calendar startDate, Calendar endDate) throws InvalidDateException {
		Calendar today = Calendar.getInstance();
		if (startDate.compareTo(today) < 0) {
			throw new InvalidDateException("La fecha de inicio debe ser posterior al día actual");
	    	
		}
		if (endDate.compareTo(today) < 0) {
			throw new InvalidDateException("La fecha de fin debe ser posterior al día actual");
	    	
		}
		if (startDate.compareTo(endDate) >= 0) {
			throw new InvalidDateException("El rango de fechas es incorrecto.");
		}
	}
	
	 @RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
	 public void deleteTraining(@PathVariable int id) throws NotFoundTrainingIdException {
	        if (!trainingController.deleteTraining(id)) {
	        	throw new NotFoundTrainingIdException();
	        }
	 }
}
