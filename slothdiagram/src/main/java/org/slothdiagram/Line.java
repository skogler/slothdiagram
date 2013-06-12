package org.slothdiagram;

import java.util.LinkedList;
import java.util.List;

import org.slothdiagram.points.ScreenPoint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.ViewGroup;

public class Line implements DrawableElement {

    private final List<ScreenPoint> points = new LinkedList<ScreenPoint>();

    private final Paint linePaint = new Paint();
    private final Path path = new Path();

    private Rect boundingBox = new Rect();
    private final Paint boundingBoxPaint = new Paint();

    public Line() {
        linePaint.setStrokeWidth(5);
        linePaint.setStyle(Style.STROKE);
        linePaint.setAntiAlias(true);
        boundingBoxPaint.setColor(Color.argb(100, 50, 100, 255));
    }

    public void addPoint(ScreenPoint point) {
        points.add(point);
    }

    @Override
    public void draw(Canvas canvas) {
        updateBoundingBox();
        recalculatePath();
        canvas.drawPath(path, linePaint);
        canvas.drawRect(boundingBox, boundingBoxPaint);
    }

    private void recalculatePath() {
        path.reset();
        boolean first = true;
        for (ScreenPoint p : getPoints()) {
            if (first) {
                path.moveTo(p.getX(), p.getY());
                first = false;
            } else {
                path.lineTo(p.getX(), p.getY());
            }
        }
    }

    /**
     * Recalculates the bounding box for this line, which is a rectangle
     * containing all the points of the line.
     */
    @Override
    public void updateBoundingBox() {
        int lowestLeft = Integer.MAX_VALUE;
        int highestRight = Integer.MIN_VALUE;
        int lowestTop = Integer.MAX_VALUE;
        int highestBottom = Integer.MIN_VALUE;

        for (ScreenPoint p : getPoints()) {
            int x = p.getX();
            int y = p.getY();
            lowestLeft = Math.min(lowestLeft, x);
            highestRight = Math.max(highestRight, x);
            lowestTop = Math.min(lowestTop, y);
            highestBottom = Math.max(highestBottom, y);
        }
        this.boundingBox = new Rect(lowestLeft, lowestTop, highestRight, highestBottom);
    }

    @Override
    public ScreenPoint getTopLeft() {
        return null;
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
        updateBoundingBox();
        return boundingBox;
    }

    public List<ScreenPoint> getPoints() {
        return points;
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
    public void setParentViewGroup(ViewGroup parent) {
    }

    @Override
    public void setParentElement(DrawableElement drawableElement) {
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
    public void addConnectionPoint(ScreenPoint position) {
    }

    @Override
    public boolean hasChildren() {
        return false;
    }

}