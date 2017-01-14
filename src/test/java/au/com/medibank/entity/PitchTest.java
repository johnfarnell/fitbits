package au.com.medibank.entity;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 */
public class PitchTest {

    @Test
    public void testIsValidPositionTrue() {
        Position bottomLeftPosition = new Position(0, 0);
        Position topRightPosition = new Position(3, 5);
        Pitch pitch = new Pitch(bottomLeftPosition, topRightPosition);

        Position pos1 = new Position(0, 0);
        assertTrue(pitch.isValidPosition(pos1));
        Position pos2 = new Position(3, 5);
        assertTrue(pitch.isValidPosition(pos2));
        Position pos3 = new Position(2, 3);
        assertTrue(pitch.isValidPosition(pos3));
    }

    @Test
    public void testIsValidPositionFalse() {
        Position bottomLeftPosition = new Position(0, 0);
        Position topRightPosition = new Position(3, 5);
        Pitch pitch = new Pitch(bottomLeftPosition, topRightPosition);

        Position pos1 = new Position(0, -1);
        assertFalse(pitch.isValidPosition(pos1));
        Position pos2 = new Position(-1, 0);
        assertFalse(pitch.isValidPosition(pos2));
        Position pos3 = new Position(3, 6);
        assertFalse(pitch.isValidPosition(pos3));
        Position pos4 = new Position(4, 5);
        assertFalse(pitch.isValidPosition(pos4));
    }
}
