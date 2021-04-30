package game.minesweeper.utils;

public class Point2D {
    public int x;
    public int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int toOneDimension() {
        return 9 + x + y * 9;
    }
}
