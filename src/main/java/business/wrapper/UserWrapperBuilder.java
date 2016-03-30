package business.wrapper;

import java.util.Calendar;
import java.util.GregorianCalendar;

import data.entities.Role;

public class UserWrapperBuilder {

    private UserWrapper userWrapper;

    public UserWrapperBuilder(int suffix) {
        userWrapper = new UserWrapper("u" + suffix, "u" + suffix + "@gmail.com", "u" + suffix, new GregorianCalendar(1979, 07, 22));
    }

    public UserWrapperBuilder() {
        this(0);
    }

    public UserWrapperBuilder username(String username) {
        userWrapper.setUsername(username);
        return this;
    }

    public UserWrapperBuilder email(String email) {
        userWrapper.setEmail(email);
        return this;
    }

    public UserWrapperBuilder password(String password) {
        userWrapper.setPassword(password);
        return this;
    }

    public UserWrapperBuilder birthDate(Calendar birthDate) {
        userWrapper.setBirthDate(birthDate);
        return this;
    }
    
    public UserWrapperBuilder role(Role role) {
        userWrapper.setRole(role);
        return this;
    }

    public UserWrapper build() {
        return userWrapper;
    }

}
