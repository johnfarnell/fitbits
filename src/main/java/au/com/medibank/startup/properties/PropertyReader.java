package au.com.medibank.startup.properties;

import java.util.Map;

/**
 */
public interface PropertyReader {

    Map<String, String> getProperties(String propertyPath, String overridePropertyPath);
}
