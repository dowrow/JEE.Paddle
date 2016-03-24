package data.entities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    EncryptTest.class,
    TokenTest.class,
    TrainingTest.class
})
public class AllEntityTests {

}
