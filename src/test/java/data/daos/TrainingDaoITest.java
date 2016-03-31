package data.daos;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Training;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TrainingDaoITest {

    @Autowired
    private TrainingDao trainingDao;

    @Test
    public void testFindByStartDateBetween() {
    	
    	Calendar yesterday = Calendar.getInstance();
    	yesterday.add(Calendar.DAY_OF_MONTH, -1);
    	Calendar today = Calendar.getInstance();
    	Calendar twoMonthsFromToday = Calendar.getInstance();
    	twoMonthsFromToday.add(Calendar.MONTH, 2);
    	
    	assertTrue(trainingDao.findByStartDateBetween(yesterday, yesterday).isEmpty());
    	List<Training> trainings = trainingDao.findByStartDateBetween(today, twoMonthsFromToday);
    	for (Training t : trainings) {
    		System.out.println(t);
    	}
    	
    }

   

}
