package au.com.medibank.entity;

/**
 */
public class PositionHeading {
    private Heading heading;

    public Position getPosition() {
        return position;
    }

    private Position position;

    public Heading getHeading() {
        return heading;
    }

    public PositionHeading(Position position, Heading heading) {
        this.heading = heading;
        this.position = position;
    }
}
