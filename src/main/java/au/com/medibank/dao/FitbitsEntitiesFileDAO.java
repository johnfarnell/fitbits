package au.com.medibank.dao;

import au.com.medibank.entity.Pitch;
import au.com.medibank.entity.Trainee;
import au.com.medibank.exception.FitbitsException;
import au.com.medibank.factory.InstructionFactory;
import au.com.medibank.factory.PitchFactory;
import au.com.medibank.factory.TraineeFactory;
import au.com.medibank.startup.context.ApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static au.com.medibank.Constants.FITBITS_RETRIEVE_FILE_NAME;

/**
 * The {@link FitbitsEntitiesFileDAO} class is an implementation of {@link FitbitsEntitiesDAO which retrieves data from a file
 * on the local server and returns an instance of a {@link Pitch}}  and, via a call to getNextTrainee, the next
 * available {@link Trainee}.
 */
public class FitbitsEntitiesFileDAO implements FitbitsEntitiesDAO {
    private FitbitRetrieveFileName fileNameProvider;
    private Pitch pitch;
    private int currentIndex = 0;
    private List<Trainee> trainees;
    private ApplicationContext applicationContext;

    /**
     * Takes an instance of {@link au.com.medibank.startup.context.ApplicationContext} which provides access to the file name containing all Trainee,
     * Pitch and Instructions
     *
     * @param applicationContext which provides details of how to get the name of the fiole
     */
    public void setContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;

        /*
        Now get the File Name provider class which will provide the file name containing trainee, instructions etc.
        If none supplied use the au.com.medibank.dao.ConsoleFitbitsFileDAOFileName
         */
        fileNameProvider = (FitbitRetrieveFileName) applicationContext.getPropertyObject(FITBITS_RETRIEVE_FILE_NAME,
                ConsoleFitbitRetrieveFileName.class.getName());

        /*
        * The following try .. catch will read through the file and build the single Pitch information
        * and the Trainee
         */
        trainees = new ArrayList<Trainee>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileNameProvider.getFileName()))) {
            /*
            * The first line is the Pitch details
             */
            pitch = PitchFactory.createPitch(br.readLine());

            /*
            *   The rest of the file is devoted to the 1 or more Trainees, their specific Position and Heading and
            *   \their instructions
             */
            String traineeLine;
            int index = 1;
            while ((traineeLine = br.readLine()) != null) {
                //Create a trainee
                Trainee trainee = TraineeFactory.createTraineeFromLine(traineeLine);

                trainee.setDescription("Line " + index++);
                if (!pitch.isValidPosition(trainee.getPosition())) {
                    throw new FitbitsException("Trainee not in a valid position", trainee, pitch);
                }
                //Give a description that will assist in tracking failures
                //Get the 1 or more instructions of the trainee
                String instructions = br.readLine();

                trainee.setInstructions(InstructionFactory.createInstructions(instructions));
                trainees.add(trainee);
            }
        } catch (IOException e) {
            throw new FitbitsException(e);
        }
    }

    /**
     * Returns the next Trainee
     *
     * @return the next Trainee in the list
     */
    @Override
    public synchronized Trainee getNextTrainee() {

        int currentIndexLocal = currentIndex++;
        return (currentIndexLocal < trainees.size()) ? trainees.get(currentIndexLocal) : null;
    }

    /**
     * Returns the Pitch
     *
     * @return the next Trainee in the list
     */
    @Override
    public Pitch getPitch() {
        return pitch;
    }
}
