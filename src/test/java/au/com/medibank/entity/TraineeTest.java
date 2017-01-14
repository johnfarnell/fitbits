package au.com.medibank.entity;

import au.com.medibank.instruction.Instruction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class TraineeTest {
    @Mock
    Position position;
    Heading heading;
    Trainee trainee;

    @Before
    public void setUp() {
        //Create the mock object of stock service
        position = mock(Position.class);
        heading = Heading.NORTH;
        //Create a Trainee object which is to be tested
        trainee = new Trainee(position, heading);

    }

    /**
     * Sets up a list of instructions and calls execute on the trainee
     */
    @Test
    public void testAllTheInstructionsAreAppliedForTrainee() {

        //Mock a Pitch
        Pitch pitch = mock(Pitch.class);
        //Mock a List of instructions
        List<Instruction> instructions = new ArrayList<Instruction>();

        Instruction instruction1 = mock(Instruction.class);
        PositionHeading posHeading1 = mock(PositionHeading.class);
        //Mock the return from this instruction to be posHeading1
        when(instruction1.execute(pitch, trainee)).thenReturn(posHeading1);

        //Mock the return from this posHeading1.getHeading() to be expectedHeading1
        Heading expectedHeading1 = Heading.NORTH;
        when(posHeading1.getHeading()).thenReturn(expectedHeading1);

        //Mock the return from this posHeading1.getPosition() to be expectedPos1
        Position expectedPos1 = new Position(2, 3);
        when(posHeading1.getPosition()).thenReturn(expectedPos1);

        instructions.add(instruction1);

        Instruction instruction2 = mock(Instruction.class);
        PositionHeading posHeading2 = mock(PositionHeading.class);
        //Mock the return from this instruction to be posHeading2
        when(instruction2.execute(pitch, trainee)).thenReturn(posHeading2);

        //Mock the return from this posHeading2.getHeading() to be expectedHeading2
        Heading expectedHeading2 = Heading.EAST;
        when(posHeading2.getHeading()).thenReturn(expectedHeading2);

        //Mock the return from this posHeading2.getPosition() to be expectedPos2
        Position expectedPos2 = new Position(5, 6);
        when(posHeading2.getPosition()).thenReturn(expectedPos2);

        instructions.add(instruction2);

        //Assign the instructions to the trainee, and execute
        trainee.setInstructions(instructions);
        trainee.executeInstructions(pitch);

        //Verify that all instructions were "executed" exactly once

        instructions.forEach(instruction -> verify(instruction, times(1)).execute(pitch, trainee));

        //Check the heading and position were called
        verify(posHeading1, atLeast(1)).getHeading();
        verify(posHeading1, atLeast(1)).getPosition();
        verify(posHeading2, atLeast(1)).getHeading();
        verify(posHeading2, atLeast(1)).getPosition();
        //Expect to end up at expectedPos2 and expectedHeading2
        assertEquals(expectedPos2, trainee.getPosition());
        assertEquals(expectedHeading2, trainee.getHeading());

    }

}
