package au.com.medibank.startup.context;

import au.com.medibank.dao.FitbitsEntitiesDAO;
import au.com.medibank.dao.FitbitsEntitiesFileDAO;
import au.com.medibank.exception.FitbitsException;
import au.com.medibank.startup.properties.LocalPropertyReader;
import au.com.medibank.startup.properties.PropertyReader;

import java.util.Map;

import static au.com.medibank.Constants.*;

/**
 */
public class PropertyApplicationContext implements ApplicationContext {
    private Map<String, String> propertiesMap;

    private PropertyReader propertyReader = new LocalPropertyReader();

    private synchronized Map<String, String> getProperties() {
        if (propertiesMap == null) {
            propertiesMap = propertyReader.getProperties(DEFAULT_PROPERTIES_CLASSPATH, APPLICATION_PROPERTIES_CLASSPATH);
        }

        return propertiesMap;
    }

    //PropertyApplicationContext.class.getName()
    @Override
    public FitbitsEntitiesDAO getFitbitsDAO() {
        Object obj = getPropertyObject(FITBITS_ENTITIES_DAO, FitbitsEntitiesFileDAO.class.getName());
        if (obj instanceof FitbitsEntitiesDAO) {
            FitbitsEntitiesDAO dao = (FitbitsEntitiesDAO) obj;
            //Give access to this context to the DAO, to allow properties to be supplied
            dao.setContext(this);
            return dao;
        } else {
            throw new FitbitsException("Unable to createTraineeFromLine an instance of " + FitbitsEntitiesDAO.class);
        }
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        String result = defaultValue;
        Map<String, String> propertiesMap = getProperties();
        if (propertiesMap.containsKey(key)) {
            result = propertiesMap.get(key);
        }
        return result;
    }

    @Override
    public String getProperty(String key) {
        return getProperty(key, null);
    }

    @Override
    public Object getPropertyObject(String key, String defaultValue) {
        String className = getProperty(key, defaultValue);
        try {
            return Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            throw new FitbitsException(e);
        } catch (IllegalAccessException e) {
            throw new FitbitsException(e);
        } catch (ClassNotFoundException e) {
            throw new FitbitsException(e);
        }
    }

    public void setPropertyReader(PropertyReader propertyReader) {
        this.propertyReader = propertyReader;
    }


}
