package org.slothdiagram;

import android.graphics.Point;

public class ConnectionPoint implements ScreenPoint {

    private ScreenElement parent;
    private int index;
    
    public ConnectionPoint(ScreenElement parent, int index) {
        this.parent = parent;
        this.index = index;
    }
    @Override
    public int getX() {
        return parent.connectionPointToWorldPoint(index).x;
    }
    @Override
    public int getY() {
        return parent.connectionPointToWorldPoint(index).y;
    }
    @Override
    public Point getPoint() {
        return parent.connectionPointToWorldPoint(index);
    }
    
}
