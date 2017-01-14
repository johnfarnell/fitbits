package au.com.medibank.startup.context;

import au.com.medibank.dao.FitbitsEntitiesDAO;
import au.com.medibank.exception.FitbitsException;

/**
 * This class is called at the start of the process to set up and supply
 * an instance of {@link FitbitsEntitiesDAO} back to the controller
 * <p>
 * The static class is supplied a string (see fitbitContextName) which should be the full classname
 * of an instance of {@link ApplicationContext}. This object is supplied to
 * the instance of FitbitsDAO (see {@link FitbitsEntitiesDAO#setContext(ApplicationContext)}
 *
 */
public class Configuration {
    private static ApplicationContext applicationContext;

    /**
     * Creates a singleton instance of {@link ApplicationContext}.
     *
     * @param applicationContextName the classname of the context, assumed to be on the classpath
     * @return {@link ApplicationContext }
     * @throws {@link au.com.medibank.exception.FitbitsException}  if any error occurs
     */
    public synchronized static ApplicationContext getApplicationContext(String applicationContextName) {
        // Only createTraineeFromLine once
        if (applicationContext == null) {
            if ((applicationContextName == null) || (applicationContextName.length() == 0)) {
                throw new FitbitsException("The classname of an implementation of  \"" + ApplicationContext.class.getSimpleName() + "\" must be supplied");
            }
            //Use reflection to createTraineeFromLine the fitbits context
            try {
                applicationContext = (ApplicationContext) Class.forName(applicationContextName).newInstance();
            } catch (IllegalAccessException e) {
                throw new FitbitsException(e);
            } catch (Exception e) {
                throw new FitbitsException("The supplied class name " + applicationContextName + " is not an implementation of " + ApplicationContext.class.getSimpleName());
            }
        }


        return applicationContext;
    }

    public synchronized static void reset() {
        applicationContext = null;
    }


}
