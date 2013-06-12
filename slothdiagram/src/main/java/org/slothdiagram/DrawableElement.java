package org.slothdiagram;

import java.util.List;

import org.slothdiagram.points.ScreenPoint;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.ViewGroup;

/**
 * Represents any element which can be shown in the diagram. Elements are
 * generally abstracted by a rectangular bounding box, which most operations are
 * based on.
 */
public interface DrawableElement {

    /**
     * The {@link ScreenPoint} representing the top-left coordinate of this
     * element.
     * 
     * @return The top-left point.
     */
    ScreenPoint getTopLeft();

    /**
     * The {@link ScreenPoint} representing the bottom-right coordinate of this
     * element.
     * 
     * @return The bottom-right point.
     */
    ScreenPoint getRightBottom();

    /**
     * Sets the size hint of this element. This is optional and may or may not
     * be considered by the element.
     * 
     * @param width
     * @param height
     */
    void setSize(int width, int height);

    /**
     * A rectangle in screen coordinates representing the bounds of this
     * element. The units are always pixels in the outermost coordinate space.
     * 
     * @return The bounding box rectangle.
     */
    Rect getBoundingBox();

    /**
     * Optional hook which may be called by children so that a parent can react
     * to children's size changes.
     */
    void updateBoundingBox();

    /**
     * Adds a connection point to the element. This is a point where other
     * elements such as lines can be attached. The position of this point should
     * be relative to this element.
     * 
     * @param position
     *            The position of the connection point relative to this element.
     */
    void addConnectionPoint(ScreenPoint position);

    boolean hasConnectionPoints();

    int getNearestConnectionPoint(Point worldPoint);

    List<ScreenPoint> getConnectionPoints();

    /**
     * Sets the parent {@link ViewGroup} of this element. This may not be the
     * same as the parent {@link DrawableElement}. This can be used to hook up
     * any child views the element may need to render.
     * 
     * @param parentViewGroup
     *            The parent view group.
     */
    void setParentViewGroup(ViewGroup parentView);

    /**
     * Sets the parent {@link DrawableElement} of this element. Responsible for
     * updating any relative points as needed.
     * 
     * @param drawableElement
     *            The new parent element.
     */
    void setParentElement(DrawableElement drawableElement);

    /**
     * Draws the element to a canvas.
     * 
     * @param canvas
     *            The canvas to draw to.
     */
    void draw(Canvas canvas);

    /**
     * Adds a new child to this element. Calls all the appropriate methods on
     * the child.
     * 
     * @param child
     *            The child element to be added.
     */
    void addChild(DrawableElement child);

    /**
     * Sets the top left coordinate of this element to a new {@link ScreenPoint}
     * . . Be careful, some references might be lost if the position is set like
     * this. .
     * 
     * @param topLeft
     *            The top left coordinate.
     */
    void setTopLeft(ScreenPoint topLeft);

    /**
     * Sets the right bottom coordinate of this element to a new
     * {@link ScreenPoint}. . Be careful, some references might be lost if the
     * position is set like this.
     * 
     * @param rightBottom
     *            The right bottom coordinate.
     */
    void setRightBottom(ScreenPoint rightBottom);

    /**
     * Returns true if the element contains any child elements.
     * 
     * @return Whether this element contains children.
     */
    boolean hasChildren();

}