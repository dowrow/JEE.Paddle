package data.daos;

import org.springframework.beans.factory.annotation.Autowired;

import data.entities.Token;

public class TokenDaoImpl implements ExtendedTokenDao {
	
	@Autowired
	private TokenDao tokenDao;

	@Override
	public void deleteExpiredTokens() {
		for (Token token: tokenDao.findAll()) {
			System.out.println("Eliminando " + token);
			if (token.hasExpired()) {
				tokenDao.delete(token);
				
			}
		}
	}
	
}
