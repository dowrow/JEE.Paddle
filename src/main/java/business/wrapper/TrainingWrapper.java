package business.wrapper;

import java.util.Calendar;

import data.entities.Training;

public class TrainingWrapper {
	
	private int id;
	
	private int trainerId;

	private int courtId;
	
	private Calendar startDate;

	private Calendar endDate;

	public int getId() {
		return id;
	}

	public int getTrainerId() {
		return trainerId;
	}

	public int getCourtId() {
		return courtId;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public TrainingWrapper(int id, int trainerId, int courtId, Calendar startDate, Calendar endDate) {
		super();
		this.id = id;
		this.trainerId = trainerId;
		this.courtId = courtId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public TrainingWrapper(Training training) {
		super();
		this.id = training.getId();
		this.trainerId = training.getTrainer().getId();
		this.courtId = training.getCourt().getId();
		this.startDate = training.getStartDate();
		this.endDate = training.getEndDate();
	}
	
	public TrainingWrapper() {
		
	}
	
	@Override
	public String toString() {
		return "Id. del entrenador: " + this.getTrainerId() + ". " +
				"Pista nº: " + this.getCourtId() + ". " + 
				"Empieza el: " + this.getStartDate().getTime() + ". " +
				"Termina el: " + this.getEndDate().getTime() + ". ";

	}
}
