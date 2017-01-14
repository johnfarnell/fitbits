package au.com.medibank.dao;

import au.com.medibank.entity.Pitch;
import au.com.medibank.entity.Trainee;
import au.com.medibank.startup.context.ApplicationContext;

/**
 */
public class FitbitsEntitiesDAOTst implements FitbitsEntitiesDAO {
    @Override
    public Trainee getNextTrainee() {
        return null;
    }

    @Override
    public void setContext(ApplicationContext applicationContext) {

    }

    @Override
    public Pitch getPitch() {
        return null;
    }
}
