package au.com.medibank.startup;

import au.com.medibank.controller.FitbitsController;
import au.com.medibank.dao.FitbitsEntitiesDAO;
import au.com.medibank.startup.context.ApplicationContext;

import static org.mockito.Mockito.mock;

/**
 */
public class ApplicationContextMainTst implements ApplicationContext {
    private FitbitsEntitiesDAO fitbitsEntitiesDAO;
    FitbitsController controller;

    public ApplicationContextMainTst() {
        controller = mock(FitbitsController.class);
        fitbitsEntitiesDAO = mock(FitbitsEntitiesDAO.class);

    }

    @Override
    public FitbitsController getFitbitsController() {
        return controller;
    }

    @Override
    public FitbitsEntitiesDAO getFitbitsDAO() {
        return fitbitsEntitiesDAO;
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
