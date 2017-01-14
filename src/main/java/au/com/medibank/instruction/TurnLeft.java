package au.com.medibank.instruction;

import au.com.medibank.entity.Heading;
import au.com.medibank.entity.Pitch;
import au.com.medibank.entity.PositionHeading;
import au.com.medibank.entity.Trainee;
import au.com.medibank.exception.FitbitsException;

/**
 */
public class TurnLeft implements Instruction {

    public PositionHeading execute(Pitch pitch, Trainee trainee) throws FitbitsException {
        Heading newHeading = trainee.getHeading().turnLeft();

        return new PositionHeading(trainee.getPosition(), newHeading);
    }
}