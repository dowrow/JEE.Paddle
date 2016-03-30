package business.controllers;

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
	private TrainingDao trainingDao;

	public boolean createTraining(String username, TrainingCreationWrapper wrapper) {
		User trainer = userDao.findByUsernameOrEmail(username);
		Court court = courtDao.findById(wrapper.getCourtId());
		Training training = new Training(wrapper.getStartDate(), wrapper.getEndDate(), court, trainer);
		trainingDao.save(training);
		return false;
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
