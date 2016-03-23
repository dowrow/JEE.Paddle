package data.entities;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Training {

	private static final int MAX_PUPILS = 4;

    @Id
    @GeneratedValue
    private int id;

    private int dayOfWeek;
    
    private int hourOfDay;
    
    private Calendar startDate;
    
    private Calendar endDate;
   
    @ManyToOne
    private Court court;
    
    @ManyToOne
    private User trainer;

    @OneToMany
    private Set<User> pupils;

    public Training(int dayOfWeek, int hourOfDay, Calendar startDate, Calendar endDate, Court court, User trainer) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.hourOfDay = hourOfDay;
		this.startDate = startDate;
		this.endDate = endDate;
		this.court = court;
		this.trainer = trainer;
		this.pupils = new HashSet<User>();
	}

	public int getId() {
        return id;
    }
    
    public boolean addPupil(User pupil) {
    	if (this.pupils.size() < MAX_PUPILS) {
        	return this.pupils.add(pupil);	
    	}
    	return false;
    }
    
    public void removePupil(User pupil) {
    	pupils.remove(pupil);
    }
    
    public Set<User> getPupils() {
    	return this.pupils;
    }
   
    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return id == ((Training) obj).id;
    }

    @Override
    public String toString() {
        
        return "Training [id=" + id + ", " + 
	        "dayOfWeek=" + dayOfWeek + ", " +
	        "hourOfDay=" + hourOfDay + ", " +
	        "startDate=" + startDate + ", " +
	        "endDate=" + endDate + ", " +
	        "court=" + court + ", " +
	        "trainer=" + trainer + ", " +
	        "pupils=" + pupils +
	        "]";
    }

	public static int getMaxPupils() {
		return MAX_PUPILS;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public int getHourOfDay() {
		return hourOfDay;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public Court getCourt() {
		return court;
	}

	public User getTrainer() {
		return trainer;
	}

}
