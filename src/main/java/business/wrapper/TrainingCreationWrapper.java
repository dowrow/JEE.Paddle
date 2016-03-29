package business.wrapper;

import java.util.Calendar;

public class TrainingCreationWrapper {
	private int courtId;
	public TrainingCreationWrapper(int courtId, Calendar startDate, Calendar endDate) {
		super();
		this.courtId = courtId;
		this.startDate = startDate;
		this.endDate = endDate;
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
	private Calendar startDate;
	private Calendar endDate;
}