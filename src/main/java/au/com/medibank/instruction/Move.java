package au.com.medibank.instruction;

import au.com.medibank.entity.Pitch;
import au.com.medibank.entity.Position;
import au.com.medibank.entity.PositionHeading;
import au.com.medibank.entity.Trainee;
import au.com.medibank.exception.FitbitsException;

/**
 */
public class Move implements Instruction {

    public PositionHeading execute(Pitch pitch, Trainee trainee) {
        int xPos = trainee.getPositionX();
        int yPos = trainee.getPositionY();
        Position newPos = null;
        //Create a new position by moving 1 spot, depending on heading
        switch (trainee.getHeading()) {
            case NORTH:
                newPos = new Position(xPos, ++yPos);
                break;
            case SOUTH:
                newPos = new Position(xPos, --yPos);
                yPos--;
                break;
            case EAST:
                newPos = new Position(++xPos, yPos);
                xPos++;
                break;
            case WEST:
                newPos = new Position(--xPos, yPos);
                xPos--;
                break;
        }

        //now check the position is still on the pitch

        if (pitch.isValidPosition(newPos)) {
            return new PositionHeading(newPos, trainee.getHeading());
        } else {
            throw new FitbitsException("Position is outside of the pitch boundaries", newPos, pitch);
        }


    }

}
