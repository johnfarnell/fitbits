package au.com.medibank.controller;

import au.com.medibank.dao.FitbitsEntitiesDAO;
import au.com.medibank.entity.Heading;
import au.com.medibank.entity.Pitch;
import au.com.medibank.entity.Position;
import au.com.medibank.entity.Trainee;
import au.com.medibank.instruction.Instruction;
import au.com.medibank.instruction.Move;
import au.com.medibank.instruction.TurnLeft;
import au.com.medibank.instruction.TurnRight;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 */
public class FitbitsControllerIT {

    /**
     * This will be a full test of the FitbitsController functionality. However the entities (trainees etc)
     * will not be created from an input file. The file functionality has been tested in
     * {@link au.com.medibank.dao.FitbitsFileDAOTest} and is loosely coupled from the controller so is not included
     */
    @Test
    public void testFullExecution() {
        /*
        5 5
        1 2 N
        LMLMLMLMM
        3 3 E
        MMRMMRMRRM
        2 1 W
        MRMLMRM
         */

        /*
        Create 3 Trainees with the instructions as shown, assigh and execute their instructions and confirm where they end
         up on the pitch
         */

        Instruction turnLeft = new TurnLeft();
        Instruction turnRight = new TurnRight();
        Instruction move = new Move();

        //Pitch 5 5

        Pitch pitch = new Pitch(new Position(5, 5));
        ;

        List<Trainee> trainees = new ArrayList<>();
        //Trainee 1 2 N
        Trainee trainee1 = new Trainee(new Position(1, 2), Heading.NORTH);
        trainees.add(trainee1);

        //Instructions  LMLMLMLMM
        List<Instruction> instruction1 = new ArrayList<>();
        instruction1.add(turnLeft);
        instruction1.add(move);
        instruction1.add(turnLeft);
        instruction1.add(move);
        instruction1.add(turnLeft);
        instruction1.add(move);
        instruction1.add(turnLeft);
        instruction1.add(move);
        instruction1.add(move);

        //Assign instructions
        trainee1.setInstructions(instruction1);
        //Trainee 3 3 E
        Trainee trainee2 = new Trainee(new Position(3, 3), Heading.EAST);
        trainees.add(trainee2);

        //Instructions  MMRMMRMRRM
        List<Instruction> instruction2 = new ArrayList<>();
        instruction2.add(move);
        instruction2.add(move);
        instruction2.add(turnRight);
        instruction2.add(move);
        instruction2.add(move);
        instruction2.add(turnRight);
        instruction2.add(move);
        instruction2.add(turnRight);
        instruction2.add(turnRight);
        instruction2.add(move);

        //Assign instructions
        trainee2.setInstructions(instruction2);

        //Trainee 3 3 E
        Trainee trainee3 = new Trainee(new Position(0, 0), Heading.EAST);
        trainees.add(trainee3);

        //Instructions  MMRMMRMRRM
        List<Instruction> instruction3 = new ArrayList<>();
        instruction3.add(move);
        instruction3.add(turnLeft);
        instruction3.add(move);
        instruction3.add(move);
        instruction3.add(turnLeft);
        instruction3.add(move);
        instruction3.add(turnRight);
        instruction3.add(move);
        instruction3.add(turnRight);
        instruction3.add(move);
        instruction3.add(move);
        instruction3.add(turnLeft);
        instruction3.add(move);
        instruction3.add(turnRight);
        instruction3.add(turnRight);

        //Assign instructions
        trainee3.setInstructions(instruction3);

        FitbitsEntitiesDAO fitbitsEntitiesDAO = mock(FitbitsEntitiesDAO.class);
        when(fitbitsEntitiesDAO.getPitch()).thenReturn(pitch);
        when(fitbitsEntitiesDAO.getNextTrainee()).thenReturn(trainee1, trainee2, trainee3, null);
        FitbitsController target = new FitbitsController();

        target.executeInstructions(fitbitsEntitiesDAO);

        assertEquals(1, trainee1.getPositionX());
        assertEquals(3, trainee1.getPositionY());
        assertEquals(Heading.NORTH, trainee1.getHeading());

        assertEquals(5, trainee2.getPositionX());
        assertEquals(1, trainee2.getPositionY());
        assertEquals(Heading.EAST, trainee2.getHeading());

        assertEquals(2, trainee3.getPositionX());
        assertEquals(4, trainee3.getPositionY());
        assertEquals(Heading.SOUTH, trainee3.getHeading());

    }
}
