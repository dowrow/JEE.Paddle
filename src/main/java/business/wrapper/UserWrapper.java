package business.wrapper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import data.entities.Role;
import data.entities.User;

public class UserWrapper {

    private String username;

    private String email;

    private String password;

    private Calendar birthDate;

    private Role role;
    
    private int id;
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserWrapper() {
    }

    public UserWrapper(String username, String email, String password, Calendar birthDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.role = Role.PLAYER;
        this.id = 0;
    }
    
    public UserWrapper(String username, String email, String password, Calendar birthDate, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.role = role;
        this.id = 0;
    }

    public UserWrapper(User pupil) {
		this.username = pupil.getUsername();
		this.email = pupil.getEmail();
		this.password = pupil.getPassword();
		this.birthDate = pupil.getBirthDate();
		this.role = Role.PLAYER;
		this.id = pupil.getId();
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        String time = new SimpleDateFormat("dd-MMM-yyyy ").format(birthDate.getTime());
        return "UserWrapper [username=" + username + ", email=" + email + ", password=" + password + ", birthDate=" + time + "]";
    }

	public Role getRole() {
		return role;
	}

	public void setRole(Role role2) {
		this.role = role2;
	}

}
