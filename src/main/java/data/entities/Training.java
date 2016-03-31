package data.entities;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    
    private Calendar startDate;
    
    private Calendar endDate;
   
    @ManyToOne
    private Court court;
    
    @ManyToOne
    private User trainer;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<User> pupils;

    public Training() {
    	
    }
    
    public Training(Calendar startDate, Calendar endDate, Court court, User trainer) {
		super();
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
    
    public boolean removePupil(User pupil) {
    	return pupils.remove(pupil);
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
	public Calendar getStartDate() {
		return startDate;
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
