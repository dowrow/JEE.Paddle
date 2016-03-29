package data.entities;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class TrainingTest {
	
	   @Test
	    public void testAddPupils() {
	        User trainer = new User("u1", "u1@gmail.com", "p1", Calendar.getInstance());
	        User pupil1 = new User("u2", "u2@gmail.com", "p2", Calendar.getInstance());
	        Court court = new Court();
	        Calendar startDate = Calendar.getInstance();
	        Calendar endDate = Calendar.getInstance();
	        endDate.add(Calendar.WEEK_OF_MONTH, 4);
	        Training training = new Training(startDate, endDate, court, trainer);
	        assertNotNull(training.getId());
	        assertTrue(training.getPupils().isEmpty());
	        assertTrue(training.addPupil(pupil1));
	        assertEquals(training.getPupils().size(), 1);
	        assertTrue(training.removePupil(pupil1));
	        assertTrue(training.getPupils().isEmpty());
	    }
}
