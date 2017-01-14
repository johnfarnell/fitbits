package au.com.medibank.dao;

import au.com.medibank.entity.Heading;
import au.com.medibank.entity.Pitch;
import au.com.medibank.entity.Trainee;
import au.com.medibank.instruction.Instruction;
import au.com.medibank.instruction.Move;
import au.com.medibank.instruction.TurnLeft;
import au.com.medibank.instruction.TurnRight;
import au.com.medibank.startup.context.ApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

/*
* Testing the FitbitsFileDAO class to ensure it builds and returns the entities passed to it
 */
public class FitbitsFileDAOTest {
    private final static String TEST_FILE_NAME = "FitbitsFileDAOTest.txt";

    @Before
    public void setUp() throws Exception {

        //Write data to the file for testing
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(TEST_FILE_NAME, "UTF-8");
            writer.println("7 5"); //The pitch
            writer.println("1 2 N"); //first trainee
            writer.println("LMR"); //first trainee - instructions
            writer.println("4 3 E"); //second trainee
            writer.println("MRLM"); //second trainee - instructions
        } finally {
            writer.close();
        }
    }

    @Test
    public void testAllEntitiesAreReadFromTheFile() {

        /*
        Move the context, and then the file name provider
         */
        ApplicationContext applicationContextMock = mock(ApplicationContext.class);
        FitbitRetrieveFileName fitbitRetrieveFileNameMock = mock(FitbitRetrieveFileName.class);
        //Mock how the file name is returned.
        when(applicationContextMock.getPropertyObject(anyString(), anyString())).thenReturn(fitbitRetrieveFileNameMock);
        //Ensure we use the file name created in set up.
        when(fitbitRetrieveFileNameMock.getFileName()).thenReturn(TEST_FILE_NAME);

        FitbitsEntitiesFileDAO target = new FitbitsEntitiesFileDAO();

        /*
        set the context to the mock created above
         */
        target.setContext(applicationContextMock);

        /*
        Having set the context, now see if the pitch/trainees etc come back as set up in the file name
         */

        //Pitch check
        Pitch pitch = target.getPitch();

        assert (pitch.getBottomLeftPosition().getX() == 0);
        assert (pitch.getBottomLeftPosition().getY() == 0);
        assert (pitch.getTopRightPosition().getX() == 7);
        assert (pitch.getTopRightPosition().getY() == 5);

        //Trainee 1 check
        Trainee trainee1 = target.getNextTrainee();
        assertNotNull(trainee1);
        assert (trainee1.getPositionX() == 1);
        assert (trainee1.getPositionY() == 2);
        assert (trainee1.getHeading() == Heading.NORTH);

        List<Instruction> instructions1 = trainee1.getInstructions();
        assertNotNull(instructions1);
        assert (instructions1.size() == 3);
        assert (instructions1.get(0) instanceof TurnLeft);
        assert (instructions1.get(1) instanceof Move);
        assert (instructions1.get(2) instanceof TurnRight);

        //Trainee 2 check
        Trainee trainee2 = target.getNextTrainee();
        assertNotNull(trainee2);
        assert (trainee2.getPositionX() == 4);
        assert (trainee2.getPositionY() == 3);
        assert (trainee2.getHeading() == Heading.EAST);

        List<Instruction> instructions2 = trainee2.getInstructions();
        assertNotNull(instructions2);
        assert (instructions2.size() == 4);
        assert (instructions2.get(0) instanceof Move);
        assert (instructions2.get(1) instanceof TurnRight);
        assert (instructions2.get(2) instanceof TurnLeft);
        assert (instructions2.get(3) instanceof Move);

        assertNull(target.getNextTrainee());


    }

    @After
    public void tearDown() throws Exception {
        //get rid of the file
        File file = new File(TEST_FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }
}
