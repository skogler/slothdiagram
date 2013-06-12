package org.slothdiagram.points;

import android.graphics.Point;

public class AbsolutePoint implements ScreenPoint {

    private final int x;
    private final int y;

    public AbsolutePoint(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Point getPoint() {
        return null;
    }

}
