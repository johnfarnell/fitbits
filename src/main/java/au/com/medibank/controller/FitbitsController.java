package au.com.medibank.controller;

import au.com.medibank.dao.FitbitsEntitiesDAO;
import au.com.medibank.entity.Trainee;

/**
 * This class is responsible for running the instructions for each Trainee
 */
public class FitbitsController {

    /**
     * Reads all of the {@link Trainee} details from the supplied {@link FitbitsEntitiesDAO }
     *
     * @param @link au.com.medibank.dao.FitbitsDAO  provides sequential access to each of the Trainees
     */
    public void executeInstructions(FitbitsEntitiesDAO dao) {
        /*
        Loop around each trainee and execute their instructions one at a time
         */
        Trainee trainee;
        while ((trainee = dao.getNextTrainee()) != null) {
            trainee.executeInstructions(dao.getPitch());
            System.out.println(trainee);
        }

    }
}
