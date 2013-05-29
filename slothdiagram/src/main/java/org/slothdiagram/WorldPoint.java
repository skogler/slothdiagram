package org.slothdiagram;

import android.graphics.Point;

public class WorldPoint implements ScreenPoint {

    private int x;
    private int y;

    public WorldPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public Point getPoint() {
        return new Point(x, y);
    }

}
