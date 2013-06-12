package org.slothdiagram.points;

import android.graphics.Point;

/**
 * A point which represents pixel offsets in relation to another
 * {@link ScreenPoint}.
 */
public class PixelPoint implements ScreenPoint {

    private int offsetX;
    private int offsetY;
    private ScreenPoint parentPoint;

    /**
     * Takes the parent point and offsets to it. If the parent is null, x and y
     * are unmodified.
     * 
     * @param parentPoint
     *            Which point this point is relative to. If null, x and y are
     *            unmodified.
     * @param offsetX
     *            The x coordinate, pixels relative to the parent.
     * @param offsetY
     *            The y coordinate, pixels relative to the parent.
     * */
    public PixelPoint(ScreenPoint parentPoint, int offsetX, int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.parentPoint = parentPoint;
    }

    @Override
    public int getX() {
        return parentPoint.getX() + offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    @Override
    public int getY() {
        return parentPoint.getY() + offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    @Override
    public Point getPoint() {
        return new Point(getX(), getY());
    }

    public void translate(int translateX, int translateY) {
        this.offsetX += translateX;
        this.offsetY += translateY;
    }

    public void setParentPoint(ScreenPoint parentPoint) {
        this.parentPoint = parentPoint;
    }

}
