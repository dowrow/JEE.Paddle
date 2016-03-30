package business.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;

import business.wrapper.TrainingCreationWrapper;
import business.wrapper.TrainingWrapper;

@Controller
public class TrainingController {

	public boolean createTraining(String username, TrainingCreationWrapper trainingCreationWrapper) {
		// TODO Auto-generated method stub
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

}
