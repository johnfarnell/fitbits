package au.com.medibank.factory;

import au.com.medibank.entity.Heading;
import au.com.medibank.entity.Position;
import au.com.medibank.entity.Trainee;
import au.com.medibank.exception.FitbitsException;

/**
 */
public class TraineeFactory {

    public static Trainee createTraineeFromLine(String traineeLineRaw) {
        if ((traineeLineRaw == null) || (traineeLineRaw.length() == 0)) {
            throw new FitbitsException("Input Trainee line can not be an empty string or NULL");
        }

        String traineeLine = traineeLineRaw.replaceAll("\\s", "");
        if (traineeLine.length() != 3) {
            throw new FitbitsException("Input Trainee line must contain 3 characters");
        }

        //Derive the positions
        int index = 0;
        int xPos = Integer.valueOf(Character.toString(traineeLine.charAt(index++)));
        int yPos = Integer.valueOf(Character.toString(traineeLine.charAt(index++)));

        //Derive the heading
        char headingAsStr = traineeLine.charAt(index++);

        return new Trainee(Position.createPosition(xPos, yPos), Heading.fromChar(headingAsStr));

    }
}
