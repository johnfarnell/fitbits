package au.com.medibank.entity;

/**
 * A class which holds the pitch details
 */
public class Pitch {
    private final Position bottomLeftPosition;
    private final Position topRightPosition;

    public Pitch(Position bottomLeftPosition, Position topRightPosition) {
        this.bottomLeftPosition = bottomLeftPosition;
        this.topRightPosition = topRightPosition;
    }

    public Pitch(Position topRightPosition) {
        // No bottom left co-ordinate has been supplied, so assume 0,0)
        this(new Position(0, 0), topRightPosition);
    }

    /**
     * Creates an instance of FitbitsDAO.
     *
     * @param position the position to validatye
     * @return true if the supplied positon is in a valid location on the Pitch
     */
    public boolean isValidPosition(Position position) {
        /*
        The supplied position is valid, if it lies within the x,y coordinates
         of the bottomLeftPosition and  topRightPosition
         */
        return ((position.getX() <= topRightPosition.getX())
                && (position.getX() >= bottomLeftPosition.getX())
                && (position.getY() <= topRightPosition.getY())
                && (position.getY() >= bottomLeftPosition.getY()));
    }

    public Position getBottomLeftPosition() {
        return bottomLeftPosition;
    }

    public Position getTopRightPosition() {
        return topRightPosition;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Pitch ");
        sb.append(System.getProperty("line.separator"));
        sb.append("Bottom Left Pos X " + getBottomLeftPosition().getX());
        sb.append(System.getProperty("line.separator"));
        sb.append("Bottom Left Pos Y " + getBottomLeftPosition().getY());
        sb.append(System.getProperty("line.separator"));
        sb.append("Top Right Pos X " + getTopRightPosition().getX());
        sb.append(System.getProperty("line.separator"));
        sb.append("Top Right Pos Y " + getTopRightPosition().getY());
        sb.append(System.getProperty("line.separator"));

        return sb.toString();

    }
}
