package au.com.medibank.startup.properties;

import au.com.medibank.exception.FitbitsException;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 */
public class LocalPropertyReaderTest {

    @Test
    public void testApplicationOverridesDefault() {
        LocalPropertyReader reader = new LocalPropertyReader();
        /*
        * Confirm the application properties override the default
         */
        Map<String, String> result =
                reader.getProperties("/defaultTest.properties", "/applicationTest.properties");
        assertEquals("override", result.get("override.property"));
        assertEquals("default", result.get("default.only.property"));
    }

    @Test
    public void testDefaultIfThereIsNoApplication() {
        LocalPropertyReader reader = new LocalPropertyReader();
        /*
        * Confirm the  the default properties remain if there is no application
         */
        Map<String, String> result =
                reader.getProperties("/defaultTest.properties", "/applicationTestXYZ.properties");
        assertEquals("default", result.get("override.property"));
        assertEquals("default", result.get("default.only.property"));
    }

    @Test(expected = FitbitsException.class)
    public void testExceptionIsThrownIfNoDefault() {
        LocalPropertyReader reader = new LocalPropertyReader();
        /*
        * should throw an exception
         */
        reader.getProperties("/defaultTestXXX.properties", "/applicationTest.properties");
    }
}
