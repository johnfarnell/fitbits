package au.com.medibank.factory;

import au.com.medibank.entity.Pitch;
import au.com.medibank.exception.FitbitsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 */
public class PitchFactoryTest {

    @Test
    public void testCreatePitchFromLine() {
        Pitch pitch = PitchFactory.createPitch("5 6");
        assertEquals(0, pitch.getBottomLeftPosition().getX());
        assertEquals(0, pitch.getBottomLeftPosition().getY());
        assertEquals(5, pitch.getTopRightPosition().getX());
        assertEquals(6, pitch.getTopRightPosition().getY());
    }

    @Test(expected = FitbitsException.class)
    public void testCreatePitchFailsWhenLineNull() {
        PitchFactory.createPitch(null);

    }

    @Test(expected = FitbitsException.class)
    public void testCreatePitchFailsWhenLineEmpty() {
        PitchFactory.createPitch("");

    }

    @Test(expected = FitbitsException.class)
    public void testCreatePitchFailsWhenMoreThan2Arguments() {
        PitchFactory.createPitch("3 4 6");

    }

    @Test(expected = FitbitsException.class)
    public void testCreatePitchFailsWhenLessThan2Arguments() {
        PitchFactory.createPitch("2");

    }

}
