package data.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Calendar;
import org.junit.Test;

import data.entities.Token;
import data.entities.User;

public class TokenTest {

    @Test
    public void testTokenUser() {
        User user = new User("u", "u@gmail.com", "p", Calendar.getInstance());
        Token token = new Token(user);
        assertTrue(token.getValue().length() > 20);
    }
    
    @Test
    public void testTokenExpired() {
    	 User user = new User("u", "u@gmail.com", "p", Calendar.getInstance());
         Token token = new Token(user);
         assertEquals(token.hasExpired(), false);
         Calendar calendar = Calendar.getInstance();
         calendar.add(Calendar.HOUR_OF_DAY, -2);
         token.setCreationTimestamp(calendar);
         assertEquals(token.hasExpired(), true);
    }

}
