package au.com.medibank.instruction;

import au.com.medibank.entity.Pitch;
import au.com.medibank.entity.PositionHeading;
import au.com.medibank.entity.Trainee;

/**
 */
public interface Instruction {
    PositionHeading execute(Pitch pitch, Trainee trainee);
}
