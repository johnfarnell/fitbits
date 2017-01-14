package au.com.medibank.factory;

import au.com.medibank.entity.Pitch;
import au.com.medibank.entity.Position;
import au.com.medibank.exception.FitbitsException;

public class PitchFactory {
    /**
     * @param pitchLineRaw A string containing the top right hand co-ordinates of the pitch (e.g. 5 5)
     * @return an instance of  {@link Pitch}
     */
    public static Pitch createPitch(String pitchLineRaw) {

        // Raise an error if the line has no data
        if ((pitchLineRaw == null) || (pitchLineRaw.length() == 0)) {
            throw new FitbitsException("Input Pitch line can not be an empty string or NULL");
        }

        /*
        * Remove any spaces
         */
        String pitchLine = pitchLineRaw.replaceAll("\\s", "");
        if (pitchLine.length() != 2) {
            throw new FitbitsException("Input pitch line must contain 3 characters");
        }

        /*
        * Get the co-ordinates of the top right position of the pitch
         */
        int index = 0;
        int xPos = Integer.valueOf(Character.toString(pitchLine.charAt(index++)));
        int yPos = Integer.valueOf(Character.toString(pitchLine.charAt(index++)));

        /*
        * Create an instance of the Pitch
         */

        return new Pitch(Position.createPosition(xPos, yPos));


    }
}
