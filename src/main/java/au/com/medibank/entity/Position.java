package au.com.medibank.entity;

/**
 */
public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Position createPosition(int x, int y) {
        return new Position(x, y);
    }

    @Override
    public String toString() {
        return "x co-ordinate " + x + "; y co-ordinate " + y;

    }
}
