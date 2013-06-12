package org.slothdiagram.points;

import org.slothdiagram.DrawableElement;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * A point which is relative to the width and height of the parent
 * {@link DrawableElement}. x and y are floats between 0.0f and 1.0f which
 * represent the percentage of the parent width or height, respectively.
 * 
 * For calculation, the bounding box of the parent {@link DrawableElement} is
 * used.
 */
public class PercentagePoint implements ScreenPoint {

    private final DrawableElement parent;
    private float percentX;
    private float percentY;

    /**
     * Takes a parent and two floats representing the percentage of the width or
     * height of the parent this point should be calculated with.
     * 
     * @param parent
     *            The parent element.
     * @param percentX
     *            The percentage of the width of the parent element which
     *            determines this point's x coordinate.
     * @param percentY
     *            The percentage of the height of the parent element which
     *            determines this point's y coordinate.
     */
    public PercentagePoint(DrawableElement parent, float percentX, float percentY) {
        this.parent = parent;
        this.percentX = percentX;
        this.percentY = percentY;
    }

    @Override
    public int getX() {
        Rect parentBoundingBox = parent.getBoundingBox();
        return parentBoundingBox.left + (int) ((parentBoundingBox.width()) * percentX);
    }

    @Override
    public int getY() {
        Rect parentBoundingBox = parent.getBoundingBox();
        return parentBoundingBox.top + (int) ((parentBoundingBox.height()) * percentY);
    }

    public float getPercentX() {
        return percentX;
    }

    public float getPercentY() {
        return percentY;
    }

    public void setPercentX(float percentX) {
        this.percentX = percentX;
    }

    public void setPercentY(float percentY) {
        this.percentY = percentY;
    }

    @Override
    public Point getPoint() {
        return new Point(getX(), getY());
    }

}
