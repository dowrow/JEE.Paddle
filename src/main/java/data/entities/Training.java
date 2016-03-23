package data.entities;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Training {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private Reserve reserve;

    @OneToMany
    private Set<User> players;
    
    final int MAX_PLAYERS = 4;
    
    final int HOURS_PER_TRAINING = 1;

    public Training(Reserve reserve) {
        this.reserve = reserve;
    }

    public int getId() {
        return id;
    }
    
    public void addPlayer(User player) {
    	if (this.players.size() < MAX_PLAYERS) {
        	this.players.add(player);	
    	}
    }
    
    public void removePlayer(User user) {
    	players.remove(user);
    }
    
    public Set<User> getPlayers() {
    	return this.players;
    }

    public Calendar getStartDate () {
    	return this.reserve.getDate();
    }
    
    public Calendar getEndDate () {
    	Calendar end = getStartDate();
    	end.add(Calendar.HOUR_OF_DAY, HOURS_PER_TRAINING);
    	return end;
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
        
        return "Training [id=" + id + ", reserve=" + reserve.getId() + 
        		", players=" + players + ", start date=" + getStartDate() + 
        		", end date=" + getEndDate() + "]";
    }

}
