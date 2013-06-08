package org.slothdiagram;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.View;

public interface DrawableElement {

    public abstract Point getPosition();

    public abstract void setPosition(int left, int top);

    public abstract void setSize(int width, int height);

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract Rect getDimensions();

    public abstract void addConnectionPoint(PointF connectionPoint);

    public abstract boolean hasConnectionPoints();

    public abstract int getNearestConnectionPoint(Point worldPoint);

    public abstract List<PointF> getConnectionPoints();

    public abstract void setParentView(View parent);

    public abstract void setParentElement(DrawableElement drawableElement);

    public abstract void updateBounds();

    public abstract void draw(Canvas canvas);

}