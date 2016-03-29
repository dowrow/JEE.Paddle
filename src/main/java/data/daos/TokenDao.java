package data.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import data.entities.Token;
import data.entities.User;

public interface TokenDao extends JpaRepository<Token, Integer>, ExtendedTokenDao {
    List<Token> findByUser(User user);
}
