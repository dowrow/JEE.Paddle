package business.wrapper;

import java.util.Calendar;

public class TrainingCreationWrapper {
	
	private int courtId;
	
	private Calendar startDate;
	
	private Calendar endDate;
	
	public TrainingCreationWrapper() {
		
	}
	
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

	public void setCourtId(int courtId) {
		this.courtId = courtId;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	
	
}
