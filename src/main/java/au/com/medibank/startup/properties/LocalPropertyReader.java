package au.com.medibank.startup.properties;

import au.com.medibank.exception.FitbitsException;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 */
public class LocalPropertyReader implements PropertyReader {

    /**
     * This method will read properties from the default.properties. These properties can be overriden by a file
     * called application.properties if such a file is on the classpath
     *
     * @return map of key value pairs
     */
    @Override
    public Map<String, String> getProperties(String propertyPath, String overridePropertyPath) {
        InputStream propertyStream = null;
        InputStream applicationPropertiesStream = null;
        Map<String, String> propertiesMap = new HashMap<>();
        try {
            try {
                Properties properties = new Properties();
                propertyStream = this.getClass().getResourceAsStream(propertyPath);
                if (propertyStream != null) {
                    properties.load(propertyStream);
                    propertiesMap = new HashMap<String, String>((Map) properties);
                } else {
                    throw new FitbitsException("Property file \"" + propertyPath + "\" was not found on the classpath");
                }
                /*
                * check to see if there is an application.properties file on the classpath,
                 */
                applicationPropertiesStream = LocalPropertyReader.class.getResourceAsStream(overridePropertyPath);
                if (applicationPropertiesStream != null) {
                    //If it exists, the key/values in the application properties will override the default
                    Properties applicationProperties = new Properties();
                    applicationProperties.load(applicationPropertiesStream);
                    propertiesMap.putAll((Map) applicationProperties);
                }
            } finally {
                if (propertyStream != null) {
                    propertyStream.close();
                }
                if (applicationPropertiesStream != null) {
                    applicationPropertiesStream.close();
                }
            }
        } catch (FitbitsException e) {
            throw e;
        } catch (Exception e) {
            throw new FitbitsException(e);
        }


        return propertiesMap;
    }
}
