package au.com.medibank.entity;

import au.com.medibank.exception.FitbitsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeadingTest {

    @Test
    public void testTurning() {
        //Check left turn
        Heading target = Heading.NORTH;
        target = target.turnLeft();
        assertEquals(target, Heading.WEST);
        target = target.turnLeft();
        assertEquals(target, Heading.SOUTH);
        target = target.turnLeft();
        assertEquals(target, Heading.EAST);
        target = target.turnLeft();
        assertEquals(target, Heading.NORTH);

        //Check right turn
        target = Heading.NORTH;
        target = target.turnRight();
        assertEquals(target, Heading.EAST);
        target = target.turnRight();
        assertEquals(target, Heading.SOUTH);
        target = target.turnRight();
        assertEquals(target, Heading.WEST);
        target = target.turnRight();
        assertEquals(target, Heading.NORTH);
    }

    @Test
    public void testCharacterConversion() {
        /*
        Check that the 4 characters are correctly assigned to a Heading class
         */
        Heading target = Heading.fromChar('N');
        assertEquals(Heading.NORTH, target);

        target = Heading.fromChar('S');
        assertEquals(Heading.SOUTH, target);
        target = Heading.fromChar('E');
        assertEquals(Heading.EAST, target);
        target = Heading.fromChar('W');
        assertEquals(Heading.WEST, target);
    }

    @Test(expected = FitbitsException.class)
    public void testCharacterConversionException() {
        /*
        Check that a dodgy character throws an exception
         */
        Heading.fromChar('Z');
    }

}
