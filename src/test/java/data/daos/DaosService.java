package data.daos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.entities.Authorization;
import data.entities.Court;
import data.entities.Reserve;
import data.entities.Role;
import data.entities.Token;
import data.entities.Training;
import data.entities.User;
import data.services.DataService;

@Service
public class DaosService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private AuthorizationDao authorizationDao;

    @Autowired
    private CourtDao courtDao;

    @Autowired
    private ReserveDao reserveDao;
    
    @Autowired
    private TrainingDao trainingDao;

    @Autowired
    private DataService genericService;

    private Map<String, Object> map;

    @PostConstruct
    public void populate() {
        map = new HashMap<>();
        User[] users = this.createPlayers(0, 4);
        for (User user : users) {
            map.put(user.getUsername(), user);
        }
        for (Token token : this.createTokens(users)) {
            map.put("t" + token.getUser().getUsername(), token);
        }
        
        users = this.createPlayers(4, 4);
        for (User user : users) {
            map.put(user.getUsername(), user);
        }

        for (Token token : this.createExpiredTokens(users)) {
        	 map.put("t" + token.getUser().getUsername(), token);
        }
        
        User[] trainers = this.createTrainers(0, 16);
        for (User trainer : trainers) {
            map.put(trainer.getUsername(), trainer);
        }
        User[] pupils = this.createPlayers(8, 16);

        this.createCourts(1, 8);
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_YEAR, 1);
        date.set(Calendar.HOUR_OF_DAY, 9);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        
        for (int i = 0; i < 4; i++) {
            date.add(Calendar.HOUR_OF_DAY, 1);
            reserveDao.save(new Reserve(courtDao.findOne(i+1), users[i], date));
        }
        
        for (int i = 0; i < 4; i++) {

        	Calendar tomorrow = Calendar.getInstance();
        	tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        	Calendar twoMonthsFromTomorrow = Calendar.getInstance();
        	twoMonthsFromTomorrow.add(Calendar.DAY_OF_MONTH, 1);
        	twoMonthsFromTomorrow.add(Calendar.MONTH, 2);	
        	Training training = new Training(tomorrow, twoMonthsFromTomorrow, courtDao.findOne(i+1), trainers[i]);
        	for (int j = i * 4; j <  i * 4 + 4; j++) {
            	training.addPupil(pupils[j]);	
        	}
            trainingDao.save(training);
        }
        
    }

    public User[] createPlayers(int initial, int size) {
        User[] users = new User[size];
        for (int i = 0; i < size; i++) {
            users[i] = new User("u" + (i + initial), "u" + (i + initial) + "@gmail.com", "p", Calendar.getInstance());
            userDao.save(users[i]);
            authorizationDao.save(new Authorization(users[i], Role.PLAYER));
        }
        return users;
    }
    
    public User[] createTrainers(int initial, int size) {
    	 User[] users = new User[size];
         for (int i = 0; i < size; i++) {
             users[i] = new User("t" + (i + initial), "t" + (i + initial) + "@gmail.com", "p", Calendar.getInstance());
             userDao.save(users[i]);
             authorizationDao.save(new Authorization(users[i], Role.TRAINER));
         }
         return users;
    }

    public List<Token> createTokens(User[] users) {
        List<Token> tokenList = new ArrayList<>();
        Token token;
        for (User user : users) {
            token = new Token(user);
            tokenDao.save(token);
            tokenList.add(token);
        }
        return tokenList;
    }
    
    
    public List<Token> createExpiredTokens(User[] users) {
        List<Token> tokenList = new ArrayList<>();
        Token token;
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);
        for (User user : users) {
            token = new Token(user);
            token.setCreationTimestamp(yesterday);
            tokenDao.save(token);
            tokenList.add(token);
        }
        return tokenList;
    }

    public void createCourts(int initial, int size) {
        for (int id = 0; id < size; id++) {
            courtDao.save(new Court(id + initial));
        }
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void deleteAll() {
        genericService.deleteAllExceptAdmin();
    }
}
