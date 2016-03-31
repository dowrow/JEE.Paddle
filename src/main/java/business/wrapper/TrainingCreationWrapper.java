package business.wrapper;

import java.util.Calendar;

public class TrainingCreationWrapper {
	
	public int courtId;
	
	public Calendar startDate;
	
	public Calendar endDate;
	
	public TrainingCreationWrapper() {
		this.startDate = Calendar.getInstance();
		this.startDate.add(Calendar.DAY_OF_MONTH, 1);
		this.endDate = Calendar.getInstance();
		this.endDate.add(Calendar.MONTH, 1);
	}
	
	public TrainingCreationWrapper(int courtId, Calendar startDate, Calendar endDate) {
		super();
		startDate.set(Calendar.MINUTE, 0);
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.MILLISECOND, 0);
		endDate.set(Calendar.MINUTE, 0);
		endDate.set(Calendar.SECOND, 0);
		endDate.set(Calendar.MILLISECOND, 0);
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
		startDate.set(Calendar.MINUTE, 0);
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.MILLISECOND, 0);
		this.startDate = startDate;
	}

	public void setEndDate(Calendar endDate) {
		endDate.set(Calendar.MINUTE, 0);
		endDate.set(Calendar.SECOND, 0);
		endDate.set(Calendar.MILLISECOND, 0);
		this.endDate = endDate;
	}
	
	
}
