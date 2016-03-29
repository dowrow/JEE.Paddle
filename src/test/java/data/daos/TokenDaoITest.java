package data.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Token;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TokenDaoITest {

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private DaosService daosService;

    @Test
    public void testFindByUser() {
        Token token = (Token) daosService.getMap().get("tu1");
        List<Token> tokens = tokenDao.findByUser(token.getUser());
        for(Token t : tokens) {
        	if (!t.hasExpired()) {
        		assertEquals(t, token);		
        	}
        }
    }

    @Test
    public void testDeleteExpiredTokens() {
        assertEquals(tokenDao.count(), 8);
        tokenDao.deleteExpiredTokens();
        assertEquals(tokenDao.count(), 4);
    }
}
