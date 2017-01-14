package au.com.medibank.startup.context;

import au.com.medibank.dao.FitbitsEntitiesDAO;
import au.com.medibank.dao.FitbitsEntitiesDAOTst;
import au.com.medibank.startup.properties.PropertyReader;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static au.com.medibank.Constants.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 */
public class PropertyApplicationContextTest {

    @Test
    public void testGetProperties() {
        PropertyReader properyReader = mock(PropertyReader.class);
        PropertyApplicationContext target = new PropertyApplicationContext();
        target.setPropertyReader(properyReader);

        Map<String, String> expectedMap = new HashMap<String, String>();
        expectedMap.put("key1", "value1");

        expectedMap.put("key2", "value2");

        when(properyReader.getProperties(DEFAULT_PROPERTIES_CLASSPATH, APPLICATION_PROPERTIES_CLASSPATH)).thenReturn(expectedMap);

        String defaultValue = "defaultValue";
        String actualValue = target.getProperty("key1", defaultValue);
        assertEquals("value1", actualValue);
        actualValue = target.getProperty("key1");
        assertEquals("value1", actualValue);

        actualValue = target.getProperty("key2", defaultValue);
        assertEquals("value2", actualValue);

        actualValue = target.getProperty("key3", defaultValue);
        assertEquals(defaultValue, actualValue);

        actualValue = target.getProperty("key3");
        assertNull(actualValue);
    }

    @Test
    public void testGetFitbitsDAO() {
        PropertyReader properyReader = mock(PropertyReader.class);
        PropertyApplicationContext target = new PropertyApplicationContext();
        target.setPropertyReader(properyReader);

        Map<String, String> expectedMap = new HashMap<String, String>();
        expectedMap.put("key1", "dummy");
        expectedMap.put(FITBITS_ENTITIES_DAO, FitbitsEntitiesDAOTst.class.getName());
        expectedMap.put("key3", "dummy");

        when(properyReader.getProperties(DEFAULT_PROPERTIES_CLASSPATH, APPLICATION_PROPERTIES_CLASSPATH)).thenReturn(expectedMap);

        FitbitsEntitiesDAO dao = target.getFitbitsDAO();
        assertNotNull(dao);
        assertTrue(dao instanceof FitbitsEntitiesDAOTst);
    }
}