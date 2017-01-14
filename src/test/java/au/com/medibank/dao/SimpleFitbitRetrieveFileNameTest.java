package au.com.medibank.dao;

import org.junit.Test;

import static au.com.medibank.Constants.DEFAULT_INPUT_FILE_NAME;
import static org.junit.Assert.assertEquals;

/**
 */
public class SimpleFitbitRetrieveFileNameTest {

    @Test
    public void testFileNameIsRetrieved() {
        SimpleFitbitRetrieveFileName target = new SimpleFitbitRetrieveFileName();
        assertEquals(DEFAULT_INPUT_FILE_NAME, target.getFileName());
        target.setFileName("NewFile");
        assertEquals("NewFile", target.getFileName());
    }
}
