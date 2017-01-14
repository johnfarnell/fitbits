package au.com.medibank.instruction;

import au.com.medibank.entity.Heading;
import au.com.medibank.entity.Pitch;
import au.com.medibank.entity.PositionHeading;
import au.com.medibank.entity.Trainee;
import au.com.medibank.exception.FitbitsException;
import au.com.medibank.factory.TraineeFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 */
public class MoveTest {

    @Test
    public void testMovingNorth() {
        Move target = new Move();

        Pitch pitch = mock(Pitch.class);
        //x = 1, y = 2; Heading.NORTH
        Trainee trainee = TraineeFactory.createTraineeFromLine("1 2 N");

        /*
        Assume pitch position is valid
         */
        when(pitch.isValidPosition(any())).thenReturn(Boolean.TRUE);
        PositionHeading actualPosHeading = target.execute(pitch, trainee);

        assertEquals(1, actualPosHeading.getPosition().getX()); // moved right (east)
        assertEquals(3, actualPosHeading.getPosition().getY()); // y unchanged
        assertEquals(Heading.NORTH, actualPosHeading.getHeading()); // still heading EAST
    }

    @Test
    public void testMovingEast() {
        Move target = new Move();

        Pitch pitch = mock(Pitch.class);
        //x = 3, y = 1; Heading.EAST
        Trainee trainee = TraineeFactory.createTraineeFromLine("3 1 E");

        /*
        Assume pitch position is valid
         */
        when(pitch.isValidPosition(any())).thenReturn(Boolean.TRUE);
        PositionHeading actualPosHeading = target.execute(pitch, trainee);

        assertEquals(4, actualPosHeading.getPosition().getX()); // moved right (east)
        assertEquals(1, actualPosHeading.getPosition().getY()); // y unchanged
        assertEquals(Heading.EAST, actualPosHeading.getHeading()); // still heading EAST
    }

    @Test
    public void testMovingWest() {
        Move target = new Move();

        Pitch pitch = mock(Pitch.class);
        //x = 3, y = 1; Heading.WEST
        Trainee trainee = TraineeFactory.createTraineeFromLine("3 1 W");

        /*
        Assume pitch position is valid
         */
        when(pitch.isValidPosition(any())).thenReturn(Boolean.TRUE);
        PositionHeading actualPosHeading = target.execute(pitch, trainee);

        assertEquals(2, actualPosHeading.getPosition().getX()); // moved right (east)
        assertEquals(1, actualPosHeading.getPosition().getY()); // y unchanged
        assertEquals(Heading.WEST, actualPosHeading.getHeading()); // still heading WEST
    }

    @Test
    public void testMovingSouth() {
        Move target = new Move();

        Pitch pitch = mock(Pitch.class);
        //x = 3, y = 1; Heading.SOUTH
        Trainee trainee = TraineeFactory.createTraineeFromLine("3 1 S");

        /*
        Assume pitch position is valid
         */
        when(pitch.isValidPosition(any())).thenReturn(Boolean.TRUE);
        PositionHeading actualPosHeading = target.execute(pitch, trainee);

        assertEquals(3, actualPosHeading.getPosition().getX()); // moved right (east)
        assertEquals(0, actualPosHeading.getPosition().getY()); // y unchanged
        assertEquals(Heading.SOUTH, actualPosHeading.getHeading()); // still heading SOUTH
    }

    @Test(expected = FitbitsException.class)
    public void testMovingExceptionThrown() {
        Move target = new Move();

        Pitch pitch = mock(Pitch.class);
        //x = 3, y = 1; Heading.SOUTH
        Trainee trainee = TraineeFactory.createTraineeFromLine("3 1 S");

        /*
        Assume pitch position is NOT Valid
         */
        when(pitch.isValidPosition(any())).thenReturn(Boolean.FALSE);

        target.execute(pitch, trainee);
    }
}
