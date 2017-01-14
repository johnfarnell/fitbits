package au.com.medibank.entity;

import au.com.medibank.exception.FitbitsException;

/**
 * This enum holds the 4 possible headings (or directions) that a {@link Trainee} could have
 */
public enum Heading {
    NORTH, EAST, SOUTH, WEST;

    /**
     * Turn right from current headiong
     *
     * @return the Heading after the turn has been made
     */
    public Heading turnRight() {
        /*
         Find the next ordinal  - use modulo (%) to return to keep within the range of Headings
          */
        int newIndex = (ordinal() + 1) % values().length;
        return values()[newIndex];
    }

    /**
     * Turn left from current heading
     *
     * @return the Heading after the turn has been made
     */
    public Heading turnLeft() {
        /*
        Adding a value of 1 less than the number of headings, is the same
        as subtracting 1 (left turn) without the complication of arriving at -1
         */
        int numberOfAvailableHeadingsMinus1 = values().length - 1;
        /*
         Find the next ordinal  - use modulo (%) to return to keep within the range of Headings
          */
        int newIndex = (ordinal() + numberOfAvailableHeadingsMinus1) % values().length;
        return values()[newIndex];
    }

    /**
     * @param directionAsChar the character to be matched to a {@link Heading}
     * @return the {@link Heading} that matches the supplied character
     */
    public static Heading fromChar(char directionAsChar) {
        Heading result = null;
        switch (directionAsChar) {
            case 'N':
                result = NORTH;
                break;
            case 'E':
                result = EAST;
                break;
            case 'S':
                result = SOUTH;
                break;
            case 'W':
                result = WEST;
                break;
            default:
                throw new FitbitsException("Invalid character " + directionAsChar + " supplied as a direction");
        }

        return result;

    }
}
