package au.com.medibank;

import au.com.medibank.controller.FitbitsController;
import au.com.medibank.dao.FitbitsEntitiesDAO;
import au.com.medibank.exception.FitbitsException;
import au.com.medibank.startup.context.ApplicationContext;
import au.com.medibank.startup.context.Configuration;
import au.com.medibank.startup.context.PropertyApplicationContext;

/**
 * This application main class can optionally be passed an argument which is the string name of an instance
 * of {@link au.com.medibank.startup.context.ApplicationContext}.
 * <p>
 * If no such argument is passed, a default of {@link au.com.medibank.startup.context.PropertyApplicationContext}
 * is assumed.
 * <p>
 * the {@link au.com.medibank.controller.FitbitsController} is instantiated and uses the
 * {@link FitbitsEntitiesDAO} to executeInstructions
 */
public class Main {

    public static void main(String[] args) {


        /*
        * Check if the context's class name has been supplied, default first
         */
        String contextClassName = PropertyApplicationContext.class.getName();
        if (args.length > 0) {
            contextClassName = args[0];
        }

        /*
        * Pass the string into the Configuration which will provide the ApplicationContext instance.
         */
        ApplicationContext context = Configuration.getApplicationContext(contextClassName);

        /*
        * Execute instructions, based on information supplied by the FitbitsDAO
         */
        FitbitsController controller = context.getFitbitsController();
        try {
            controller.executeInstructions(context.getFitbitsDAO());
        } catch (FitbitsException e) {
            e.printStackTrace();
        }
    }
}
