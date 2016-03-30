package business.wrapper;

import java.util.Calendar;

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

}
