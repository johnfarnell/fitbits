package au.com.medibank.instruction;

import au.com.medibank.entity.Heading;
import au.com.medibank.entity.Pitch;
import au.com.medibank.entity.PositionHeading;
import au.com.medibank.entity.Trainee;
import au.com.medibank.factory.TraineeFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 */
public class TurnLeftTest {

    @Test
    public void testTurnFromNorth() {
        TurnLeft target = new TurnLeft();

        Pitch pitch = mock(Pitch.class);
        //x = 1, y = 2; Heading.NORTH
        Trainee trainee = TraineeFactory.createTraineeFromLine("1 2 N");
        PositionHeading actualPosHeading = target.execute(pitch, trainee);
        //A turn left from north will be west
        assertEquals(Heading.WEST, actualPosHeading.getHeading());

        //Position should be unchanged
        assertEquals(1, actualPosHeading.getPosition().getX());
        assertEquals(2, actualPosHeading.getPosition().getY());
    }

    @Test
    public void testTurnFromSouth() {
        TurnLeft target = new TurnLeft();

        Pitch pitch = mock(Pitch.class);
        //x = 1, y = 2; Heading.SOUTH
        Trainee trainee = TraineeFactory.createTraineeFromLine("1 2 S");
        PositionHeading actualPosHeading = target.execute(pitch, trainee);
        //A turn left from south will be east
        assertEquals(Heading.EAST, actualPosHeading.getHeading());

        //Position should be unchanged
        assertEquals(1, actualPosHeading.getPosition().getX());
        assertEquals(2, actualPosHeading.getPosition().getY());
    }

    @Test
    public void testTurnFromEast() {
        TurnLeft target = new TurnLeft();

        Pitch pitch = mock(Pitch.class);
        //x = 1, y = 2; Heading.EAST
        Trainee trainee = TraineeFactory.createTraineeFromLine("1 2 E");
        PositionHeading actualPosHeading = target.execute(pitch, trainee);
        //A turn left from east will be north
        assertEquals(Heading.NORTH, actualPosHeading.getHeading());

        //Position should be unchanged
        assertEquals(1, actualPosHeading.getPosition().getX());
        assertEquals(2, actualPosHeading.getPosition().getY());
    }

    @Test
    public void testTurnFromWest() {
        TurnLeft target = new TurnLeft();

        Pitch pitch = mock(Pitch.class);
        //x = 1, y = 2; Heading.WEST
        Trainee trainee = TraineeFactory.createTraineeFromLine("1 2 W");
        PositionHeading actualPosHeading = target.execute(pitch, trainee);
        //A turn left from south will be south
        assertEquals(Heading.SOUTH, actualPosHeading.getHeading());

        //Position should be unchanged
        assertEquals(1, actualPosHeading.getPosition().getX());
        assertEquals(2, actualPosHeading.getPosition().getY());
    }
}
