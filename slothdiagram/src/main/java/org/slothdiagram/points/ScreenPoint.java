package org.slothdiagram.points;

import android.graphics.Point;

/**
 * Represents a point on the screen in pixels.
 */
public interface ScreenPoint {

    /**
     * Returns the x coordinate in absolute screen space.
     * 
     * @return The absolute x coordinate in pixels
     */
    int getX();

    /**
     * Returns the y coordinate in absolute screen space.
     * 
     * @return The absolute y coordinate in pixels
     */
    int getY();

    /**
     * Returns a point in absolute screen space and in pixels.
     * 
     * @return The point in pixels.
     */
    Point getPoint();

}