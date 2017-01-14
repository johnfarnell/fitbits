package au.com.medibank.dao;

import au.com.medibank.entity.Pitch;
import au.com.medibank.entity.Trainee;
import au.com.medibank.startup.context.ContextAware;

/**
 * Implementations of this class will return 1 or more  {@link Trainee} and {@link Pitch}
 *
 */
public interface FitbitsEntitiesDAO extends ContextAware {
    Trainee getNextTrainee();

    Pitch getPitch();
}
