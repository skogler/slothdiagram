package org.slothdiagram;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.larvalabs.svgandroid.SVG;

public class ScreenElement {

    private Drawable drawable;
    private Rect dimension = new Rect(0, 0, 0, 0);
    private List<PointF> connectionPoints = new ArrayList<PointF>();

    public ScreenElement(BitmapDrawable bitmapDrawable) {
        drawable = bitmapDrawable;
    }

    public ScreenElement(Drawable drawable) {
        this.drawable = drawable;
    }

    public ScreenElement(SVG svg) {
        drawable = svg.getDrawable();
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public Point getPosition() {
        return new Point(dimension.left, dimension.top);
    }

    public void setPosition(int left, int top) {
        dimension.right += left - dimension.left;
        dimension.left = left;
        dimension.bottom += top - dimension.top;
        dimension.top = top;
    }

    public void setSize(int width, int height) {
        dimension.right = dimension.left + width;
        dimension.bottom = dimension.top + height;
    }

    public int getWidth() {
        return dimension.right - dimension.left;
    }

    public int getHeight() {
        return dimension.bottom - dimension.top;
    }

    public Rect getDimensions() {
        return dimension;
    }

    public void scale(float scaleFactor) {
        int width = getWidth();
        int height = getHeight();
        int widthOffset = (int) (((width * scaleFactor) - width) / 2);
        int heightOffset = (int) (((height * scaleFactor) - height) / 2);

        dimension.left -= widthOffset;
        dimension.right += widthOffset;
        dimension.top -= heightOffset;
        dimension.bottom += heightOffset;
    }

    public void addConnectionPoint(PointF connectionPoint) {
        connectionPoints.add(connectionPoint);
    }

    public boolean hasConnectionPoints() {
        return !connectionPoints.isEmpty();
    }

    public Point connectionPointToWorldPoint(int i) {
        PointF connectionPoint = connectionPoints.get(i);
        int x = dimension.left + (int) ((getWidth()) * connectionPoint.x);
        int y = dimension.top + (int) ((getHeight()) * connectionPoint.y);
        return new Point(x, y);
    }

    public int getNearestConnectionPoint(Point worldPoint) {
        float x = (worldPoint.x - dimension.left) / ((float) getWidth());
        float y = (worldPoint.y - dimension.top) / ((float) getHeight());

        double lowestSquaredDistance = Double.MAX_VALUE;
        int nearestPointIndex = 0;
        
        int i = 0;
        for (PointF connectionPoint : connectionPoints) {
            double squaredDistance = Math.pow(x - connectionPoint.x, 2)
                    + Math.pow(y - connectionPoint.y, 2);

            if (squaredDistance < lowestSquaredDistance) {
                lowestSquaredDistance = squaredDistance;
                nearestPointIndex = i;
            }
            i++;
        }
        return nearestPointIndex;
    }
}
