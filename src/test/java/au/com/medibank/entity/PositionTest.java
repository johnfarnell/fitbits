package au.com.medibank.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 */
public class PositionTest {


    @Test
    public void testPositionIsCreated() {
        Position position = new Position(4, 6);
        assertEquals(4, position.getX());
        assertEquals(6, position.getY());
    }
}
