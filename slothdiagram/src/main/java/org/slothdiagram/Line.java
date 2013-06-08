package org.slothdiagram;

import java.util.LinkedList;
import java.util.List;

import org.slothdiagram.points.ScreenPoint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.View;

public class Line implements DrawableElement {

    private final List<ScreenPoint> points = new LinkedList<ScreenPoint>();

    private final Paint linePaint = new Paint();
    private final Path path = new Path();

    public Line() {
        linePaint.setStrokeWidth(5);
        linePaint.setStyle(Style.STROKE);
        linePaint.setAntiAlias(true);
    }

    public void addPoint(ScreenPoint point) {
        points.add(point);
        recalculatePath();
    }

    public List<ScreenPoint> getPoints() {
        return points;
    }

    @Override
    public Point getPosition() {
        return null;
    }

    @Override
    public void setPosition(int left, int top) {
    }

    @Override
    public void setSize(int width, int height) {
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public Rect getDimensions() {
        return null;
    }

    @Override
    public void addConnectionPoint(PointF connectionPoint) {
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
    public List<PointF> getConnectionPoints() {
        return null;
    }

    @Override
    public void setParentView(View parent) {
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(path, linePaint);
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

    @Override
    public void setParentElement(DrawableElement drawableElement) {
    }

    @Override
    public void updateBounds() {
    }
}