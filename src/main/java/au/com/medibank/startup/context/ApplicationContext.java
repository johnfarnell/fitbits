package au.com.medibank.startup.context;

import au.com.medibank.controller.FitbitsController;
import au.com.medibank.dao.FitbitsEntitiesDAO;

/**
 * Implementations of this class will return properties and the instance
 * of {@link FitbitsEntitiesDAO} used within the application
 *
 */
public interface ApplicationContext {
    FitbitsEntitiesDAO getFitbitsDAO();

    String getProperty(String key, String defaultValue);

    String getProperty(String key);

    Object getPropertyObject(String key, String defaultValue);

    default FitbitsController getFitbitsController() {
        return new FitbitsController();
    }

}
