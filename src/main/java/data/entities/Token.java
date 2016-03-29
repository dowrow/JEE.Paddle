package data.entities;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Token {

	private static final int EXPIRATION_HOURS = 1;
	
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String value;
    
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar creationTimestamp;
    
    @ManyToOne
    @JoinColumn
    private User user;

	public Token() {
    }

    public Token(User user) {
        assert user != null;
        this.user = user;
        this.value = new Encrypt().encryptInBase64UrlSafe("" + user.getId() + user.getUsername() + Long.toString(new Date().getTime())
                + user.getPassword());
        this.creationTimestamp = Calendar.getInstance();
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public User getUser() {
        return user;
    }
    
    public Calendar getCreationTimestamp() {
    	return creationTimestamp;
    }

    public void setCreationTimestamp(Calendar creationTimestamp) {
    	this.creationTimestamp = creationTimestamp;
    }
    
	public boolean hasExpired() {
		Calendar now = Calendar.getInstance();
		Calendar expirationTime = this.creationTimestamp;
		expirationTime.add(Calendar.HOUR_OF_DAY, EXPIRATION_HOURS);
		return (expirationTime.compareTo(now) <= 0 );	
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
        return id == ((Token) obj).id;
    }

    @Override
    public String toString() {
        return "Token [id=" + id + 
        		", value=" + value + 
        		", userId=" + user.getId() + 
        		", creationTimestamp=" + creationTimestamp.getTime() +
        		"]";
    }

}
