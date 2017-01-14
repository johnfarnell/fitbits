package au.com.medibank.factory;

import au.com.medibank.entity.Heading;
import au.com.medibank.entity.Trainee;
import au.com.medibank.exception.FitbitsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 */
public class TraineeFactoryTest {

    @Test
    public void testCreateTraineeFromLine() {
        Trainee trainee = TraineeFactory.createTraineeFromLine("5 6 N");
        assertEquals(Heading.NORTH, trainee.getHeading());
        assertEquals(5, trainee.getPositionX());
        assertEquals(6, trainee.getPositionY());
    }

    @Test(expected = FitbitsException.class)
    public void testCreateTraineeFailsWhenLineNull() {
        TraineeFactory.createTraineeFromLine(null);

    }

    @Test(expected = FitbitsException.class)
    public void testCreateTraineeFailsWhenLineEmpty() {
        TraineeFactory.createTraineeFromLine("");

    }

    @Test(expected = FitbitsException.class)
    public void testCreateTraineeFailsWhenMoreThan3Arguments() {
        TraineeFactory.createTraineeFromLine("3 4 6 7");

    }

    @Test(expected = FitbitsException.class)
    public void testCreateTraineeFailsWhenLessThan3Arguments() {
        TraineeFactory.createTraineeFromLine("2 6");

    }

}
