package org.slothdiagram;

import android.graphics.Point;
import android.graphics.PointF;

public class RelativePoint implements ScreenPoint {

    private ScreenElement parent;
    private PointF position;

    public RelativePoint(ScreenElement parent, PointF position) {
        this.parent = parent;
        this.position = position;
    }

    @Override
    public int getX() {
        return parent.convertToWorldPoint(position).getX();
    }

    @Override
    public int getY() {
        return parent.convertToWorldPoint(position).getY();
    }

    @Override
    public Point getPoint() {
        return parent.convertToWorldPoint(position).getPoint();
    }

}
