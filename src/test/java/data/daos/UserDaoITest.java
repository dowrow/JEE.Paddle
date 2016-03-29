package data.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Token;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class UserDaoITest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DaosService daosService;

    @Test
    public void testCreate() {
        assertTrue(userDao.count() >= 8);
    }

    @Test
    public void testFindDistinctByUsernameOrEmail() {
        User u1 = (User) daosService.getMap().get("u1");
        assertEquals(u1, userDao.findByUsernameOrEmail(u1.getUsername()));
        assertEquals(u1, userDao.findByUsernameOrEmail(u1.getEmail()));
        assertNull(userDao.findByUsernameOrEmail("kk"));
    }

    @Test
    public void testFindByTokenValue() {
        User u1 = (User) daosService.getMap().get("u2");
        Token t1 = (Token) daosService.getMap().get("tu2");
        assertEquals(u1, userDao.findByTokenValue(t1.getValue()));
        assertNull(userDao.findByTokenValue("kk"));
    }
}
