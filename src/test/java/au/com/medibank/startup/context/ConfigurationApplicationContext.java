package au.com.medibank.startup.context;

import au.com.medibank.dao.FitbitsEntitiesDAO;

/**
 */
public class ConfigurationApplicationContext implements ApplicationContext {
    @Override
    public FitbitsEntitiesDAO getFitbitsDAO() {
        return null;
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        return null;
    }

    @Override
    public String getProperty(String key) {
        return null;
    }

    @Override
    public Object getPropertyObject(String key, String defaultValue) {
        return null;
    }
}
