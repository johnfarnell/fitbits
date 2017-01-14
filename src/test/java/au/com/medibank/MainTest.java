package au.com.medibank;

import au.com.medibank.startup.ApplicationContextMainTst;
import org.junit.Test;

/**
 */
public class MainTest {

    @Test
    public void testMainMethod() {
        /*
        Assign the  ApplicationContex to the test class
         */
        String[] args = {ApplicationContextMainTst.class.getName()};
        Main.main(args);
    }
}
