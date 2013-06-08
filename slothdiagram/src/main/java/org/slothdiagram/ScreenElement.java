package org.slothdiagram;

import java.util.ArrayList;
import java.util.List;

import org.slothdiagram.points.AbsolutePoint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.larvalabs.svgandroid.SVG;

public class ScreenElement implements DrawableElement {

    private final Drawable drawable;
    private final Rect dimension = new Rect(0, 0, 0, 0);

    private final List<PointF> connectionPoints = new ArrayList<PointF>();
    private final List<ScreenText> textElements = new ArrayList<ScreenText>();
    private View parentView;
    private DrawableElement parentElement;
    private final Paint connectionPointPaint = new Paint();

    {
        connectionPointPaint.setStrokeWidth(5);
    }

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

    @Override
    public Point getPosition() {
        return new Point(dimension.left, dimension.top);
    }

    @Override
    public void setPosition(int left, int top) {
        dimension.right += left - dimension.left;
        dimension.left = left;
        dimension.bottom += top - dimension.top;
        dimension.top = top;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.slothdiagram.DrawableElement#setSize(int, int)
     */
    @Override
    public void setSize(int width, int height) {
        dimension.right = dimension.left + width;
        dimension.bottom = dimension.top + height;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.slothdiagram.DrawableElement#getWidth()
     */
    @Override
    public int getWidth() {
        return dimension.right - dimension.left;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.slothdiagram.DrawableElement#getHeight()
     */
    @Override
    public int getHeight() {
        return dimension.bottom - dimension.top;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.slothdiagram.DrawableElement#getDimensions()
     */
    @Override
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

    @Override
    public void addConnectionPoint(PointF connectionPoint) {
        connectionPoints.add(connectionPoint);
    }

    @Override
    public boolean hasConnectionPoints() {
        return !connectionPoints.isEmpty();
    }

    public Point connectionPointToWorldPoint(int i) {
        PointF connectionPoint = connectionPoints.get(i);
        int x = dimension.left + (int) ((getWidth()) * connectionPoint.x);
        int y = dimension.top + (int) ((getHeight()) * connectionPoint.y);
        return new Point(x, y);
    }

    @Override
    public int getNearestConnectionPoint(Point worldPoint) {
        float x = (worldPoint.x - dimension.left) / ((float) getWidth());
        float y = (worldPoint.y - dimension.top) / ((float) getHeight());

        double lowestSquaredDistance = Double.MAX_VALUE;
        int nearestPointIndex = 0;

        int i = 0;
        for (PointF connectionPoint : connectionPoints) {
            double squaredDistance = Math.pow(x - connectionPoint.x, 2) + Math.pow(y - connectionPoint.y, 2);

            if (squaredDistance < lowestSquaredDistance) {
                lowestSquaredDistance = squaredDistance;
                nearestPointIndex = i;
            }
            i++;
        }
        return nearestPointIndex;
    }

    @Override
    public List<PointF> getConnectionPoints() {
        return connectionPoints;
    }

    public AbsolutePoint convertToWorldPoint(PointF relativePoint) {
        int x = dimension.left + (int) ((getWidth()) * relativePoint.x);
        int y = dimension.top + (int) ((getHeight()) * relativePoint.y);
        return new AbsolutePoint(x, y);
    }

    public void addText(ScreenText screenText) {
        screenText.setParentElement(this);
        textElements.add(screenText);
        updateBounds();

    }

    public List<ScreenText> getTextElements() {
        return textElements;
    }

    @Override
    public void setParentView(View parent) {
        this.parentView = parent;
    }

    @Override
    public void draw(Canvas canvas) {
        drawable.setBounds(getDimensions());
        drawable.draw(canvas);

        for (int i = 0; i < getConnectionPoints().size(); ++i) {
            Point connectionPoint = connectionPointToWorldPoint(i);
            canvas.drawPoint(connectionPoint.x, connectionPoint.y, connectionPointPaint);
        }
    }

    @Override
    public void setParentElement(DrawableElement drawableElement) {
        this.parentElement = drawableElement;
    }

    @Override
    public void updateBounds() {
        if (parentView != null) {
            parentView.invalidate();
        }
        int biggestRight = 0;
        int biggestBottom = 0;
        do {
            biggestRight = dimension.right;
            biggestBottom = dimension.bottom;
            for (DrawableElement child : textElements) {
                int currentRight = child.getDimensions().right;
                if (biggestRight < currentRight)
                    biggestRight = currentRight;

                int currentBottom = child.getDimensions().bottom;
                if (biggestBottom < currentBottom)
                    biggestBottom = currentBottom;
            }
            dimension.right = biggestRight;
            dimension.bottom = biggestBottom;
        } while (biggestRight > dimension.right || biggestBottom > dimension.bottom);

        if (parentView != null) {
            parentView.invalidate();
        }

    }
}
