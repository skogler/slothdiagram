
package org.slothdiagram;

import java.util.List;

import org.slothdiagram.points.AbsolutePoint;
import org.slothdiagram.points.ScreenPoint;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.ViewGroup;

public class InvisibleElement implements DrawableElement {

    private final ScreenPoint topLeft;

    public InvisibleElement() {
        topLeft = new AbsolutePoint(0, 0);
    }

    @Override
    public ScreenPoint getTopLeft() {
        return topLeft;
    }

    @Override
    public ScreenPoint getRightBottom() {
        return null;
    }

    @Override
    public void setSize(int width, int height) {
    }

    @Override
    public Rect getBoundingBox() {
        return null;
    }

    @Override
    public void updateBoundingBox() {
    }

    @Override
    public void addConnectionPoint(ScreenPoint position) {
    }

    @Override
    public boolean hasConnectionPoints() {
        return false;
    }

    @Override
    public int getNearestConnectionPoint(Point worldPoint) {
        return 0;
    }

    @Override
    public List<ScreenPoint> getConnectionPoints() {
        return null;
    }

    @Override
    public void setParentViewGroup(ViewGroup parentView) {
    }

    @Override
    public void setParentElement(DrawableElement drawableElement) {
    }

    @Override
    public void draw(Canvas canvas) {
    }

    @Override
    public void addChild(DrawableElement child) {
    }

    @Override
    public void setTopLeft(ScreenPoint topLeft) {
    }

    @Override
    public void setRightBottom(ScreenPoint rightBottom) {
    }

    @Override
    public boolean hasChildren() {
        return false;
    }

}
