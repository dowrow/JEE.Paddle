package data.daos;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    UserDaoITest.class,
    TokenDaoITest.class,
    AuthorizationDaoITest.class,
    ReserveDaoITest.class
})
public class AllDaosITests {

}
