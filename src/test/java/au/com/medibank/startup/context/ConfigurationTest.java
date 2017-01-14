package au.com.medibank.startup.context;

import au.com.medibank.exception.FitbitsException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 */
public class ConfigurationTest {
    @Before
    public void setUp() {
        Configuration.reset();
    }

    @Test(expected = FitbitsException.class)
    public void testClassNameIsEmpty() {
        Configuration.getApplicationContext(null);
    }

    @Test(expected = FitbitsException.class)
    public void testClassNameIsNull() {
        Configuration.getApplicationContext("");
    }

    @Test(expected = FitbitsException.class)
    public void testClassNameIsInterface() {
        Configuration.getApplicationContext(ApplicationContext.class.getName());
    }

    @Test
    public void testClassNameIsApplicationContextImpl() {
        ApplicationContext result = Configuration.getApplicationContext(ConfigurationApplicationContext.class.getName());
        assertTrue(result instanceof ConfigurationApplicationContext);
        //check same object is read next time
        assertSame(result, Configuration.getApplicationContext(ConfigurationApplicationContext.class.getName()));
    }
}
