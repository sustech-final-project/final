package game.minesweeper.map;

import game.minesweeper.utils.Point2D;

public class Block {
    private final Point2D location;
    protected int state;
    private boolean hasClicked;
    private boolean hasFlag;

    public Block(int x, int y) {
        hasClicked = false;
        location = new Point2D(x, y);
        hasFlag = false;
    }

    public boolean isMine() {
        return state == -1;
    }

    public Point2D getLocation() {
        return location;
    }

    public int getState() {
        return state;
    }

    protected void click() {
        hasClicked = true;
    }

    public boolean hasClicked() {
        return hasClicked;
    }

    public void flag() {
        if (!hasClicked) {
            hasFlag = !hasFlag;
        }
    }

    public boolean hasFlag() {
        return hasFlag;
    }
}
