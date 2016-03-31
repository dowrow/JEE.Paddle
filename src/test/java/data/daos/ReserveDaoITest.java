package data.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class ReserveDaoITest {

    @Autowired
    private ReserveDao reserveDao;
    
    @Autowired
    private CourtDao courtDao;

    @Test
    public void testFindByDateBetween() {
        Calendar date1 = Calendar.getInstance();
        date1.add(Calendar.DAY_OF_YEAR, 1);
        date1.set(Calendar.HOUR_OF_DAY, 0);
        Calendar date2 = (Calendar) date1.clone();
        date2.add(Calendar.HOUR_OF_DAY, 23);
        date2.add(Calendar.MINUTE, 59);
        assertEquals(4, reserveDao.findByDateBetween(date1, date2).size());
    }

    @Test
    public void testFindByCourtAndDate() {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_YEAR, 1);
        date.set(Calendar.HOUR_OF_DAY, 10);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        assertNotNull(reserveDao.findByCourtAndDate(courtDao.findOne(1), date));
        assertNull(reserveDao.findByCourtAndDate(courtDao.findOne(2), date));
    }

}
