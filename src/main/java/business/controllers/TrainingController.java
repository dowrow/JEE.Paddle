package business.controllers;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import business.wrapper.TrainingCreationWrapper;
import business.wrapper.TrainingWrapper;
import data.daos.CourtDao;
import data.daos.TrainingDao;
import data.daos.UserDao;
import data.entities.Court;
import data.entities.Training;
import data.entities.User;

@Controller
public class TrainingController {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CourtDao courtDao;
	
	@Autowired
	private ReserveController reserveController;
	
	@Autowired
	private TrainingDao trainingDao;

	public boolean createTraining(String trainerUsername, TrainingCreationWrapper wrapper) {
		if (isAvailableCourt(wrapper.getCourtId(), wrapper.getStartDate(), wrapper.getEndDate())) {
			reserveCourt(wrapper.getCourtId(), wrapper.getStartDate(), wrapper.getEndDate(), trainerUsername);		
		} else {
			return false;
		}
		User trainer = userDao.findByUsernameOrEmail(trainerUsername);
		Court court = courtDao.findById(wrapper.getCourtId());
		Training training = new Training(wrapper.getStartDate(), wrapper.getEndDate(), court, trainer);
		trainingDao.save(training);
		return true;
	}


	private boolean isAvailableCourt(int courtId, Calendar startDate, Calendar endDate) {
		for (Calendar datetime = startDate; datetime.compareTo(endDate) >= 0; datetime.add(Calendar.WEEK_OF_YEAR, 1)) {
			if (!reserveController.isAvailable(courtId, datetime)) {
				return false;
			}
		}
		return true;
	}
	
	private void reserveCourt(int courtId, Calendar startDate, Calendar endDate, String trainerUsername) {
		for (Calendar datetime = startDate; datetime.compareTo(endDate) >= 0; datetime.add(Calendar.WEEK_OF_YEAR, 1)) {
			reserveController.reserveCourt(courtId, datetime, trainerUsername);
		}
	}

	public boolean deleteTraining(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean existsTraining(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deletePupilFromTraining(int trainingId, int pupilId) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<TrainingWrapper> getAvailableTrainings() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addPupilToTraining(int trainingId, String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
