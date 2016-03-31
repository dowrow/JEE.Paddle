package business.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import business.wrapper.TrainingCreationWrapper;
import business.wrapper.TrainingWrapper;
import business.wrapper.UserWrapper;
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

	public TrainingWrapper createTraining(String trainerUsername, TrainingCreationWrapper wrapper) {
		if (isAvailableCourt(wrapper.getCourtId(), wrapper.getStartDate(), wrapper.getEndDate())) {
			reserveCourt(wrapper.getCourtId(), wrapper.getStartDate(), wrapper.getEndDate(), trainerUsername);		
		} else {
			return null;
		}
		User trainer = userDao.findByUsernameOrEmail(trainerUsername);
		Court court = courtDao.findById(wrapper.getCourtId());
		Training training = new Training(wrapper.getStartDate(), wrapper.getEndDate(), court, trainer);
		trainingDao.save(training);
		return new TrainingWrapper(training);
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
	
	private void freeCourt(int courtId, Calendar startDate, Calendar endDate, String trainerUsername) {
		for (Calendar datetime = startDate; datetime.compareTo(endDate) >= 0; datetime.add(Calendar.WEEK_OF_YEAR, 1)) {
			reserveController.freeCourt(courtId, datetime, trainerUsername);
		}
	}

	public boolean deleteTraining(int id) {
		if (!existsTraining(id)){
			return false;
		}
		Training training = trainingDao.findOne(id);
		freeCourt(training.getCourt().getId(), training.getStartDate(), training.getEndDate(), training.getTrainer().getUsername());
		trainingDao.delete(id);
		return true;
	}

	public boolean existsTraining(int id) {
		return (trainingDao.exists(id));
	}

	public boolean deletePupilFromTraining(int trainingId, int pupilId) {
		Training training = trainingDao.findOne(trainingId);
		User pupil = userDao.findOne(pupilId);
		if (!training.getPupils().contains(pupil)) {
			return false;
		}
		training.removePupil(pupil);
		trainingDao.save(training);
		return true;
	}

	public List<TrainingWrapper> getAvailableTrainings() {
		List<Training> trainings = trainingDao.findAll();
		List<TrainingWrapper> availableTrainings = new ArrayList<TrainingWrapper>();
		
		for (Training training: trainings) {
			if (training.getStartDate().compareTo(Calendar.getInstance()) >= 0) {
				availableTrainings.add(new TrainingWrapper(training));
			}
		}
		return availableTrainings;
	}

	public boolean addPupilToTraining(int trainingId, String username) {
		Training training = trainingDao.findOne(trainingId);
		User pupil = userDao.findByUsernameOrEmail(username);
		if (!training.addPupil(pupil)) {
			return false;
		}
		trainingDao.save(training);
		return true;
	}


	public List<UserWrapper> getPupils(int id) {
		List<UserWrapper> pupils = new ArrayList<>();
		Training training = trainingDao.findOne(id);
		for (User pupil : training.getPupils()) {
			pupils.add(new UserWrapper(pupil));
		}
		return pupils;
	}

}
