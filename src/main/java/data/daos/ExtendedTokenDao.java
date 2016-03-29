package data.daos;

public interface ExtendedTokenDao {
	void deleteExpiredTokens();
}