package org.slothdiagram;

import java.util.ArrayList;
import java.util.List;

import org.slothdiagram.points.PixelPoint;
import org.slothdiagram.points.ScreenPoint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

import com.larvalabs.svgandroid.SVG;

/**
 * An element which uses a drawable to paint a picture on its bounding area. Can
 * contain other elements and resize to their largest size.
 */
public class PictureElement implements DrawableElement, Scalable {

    private final Drawable drawable;
    private final Rect boundingBox = new Rect(0, 0, 0, 0);

    private final List<ScreenPoint> connectionPoints = new ArrayList<ScreenPoint>();
    private ViewGroup parentViewGroup;
    private DrawableElement parentElement;
    private final Paint connectionPointPaint = new Paint();

    private ScreenPoint topLeft;
    private ScreenPoint rightBottom;

    private final List<DrawableElement> children = new ArrayList<DrawableElement>();

    public PictureElement(DrawableElement parentElement, ViewGroup parentViewGroup, Drawable drawable) {
        this.drawable = drawable;
        this.parentElement = parentElement;
        this.parentViewGroup = parentViewGroup;
        this.connectionPointPaint.setStrokeWidth(5);
        this.topLeft = new PixelPoint(parentElement.getTopLeft(), 0, 0);
        this.rightBottom = new PixelPoint(topLeft, 0, 0);
    }

    public PictureElement(DrawableElement parentElement, ViewGroup parentViewGroup, SVG svg) {
        this(parentElement, parentViewGroup, svg.getDrawable());
    }

    public PictureElement(DrawableElement parentElement, ViewGroup parentViewGroup, BitmapDrawable bitmapDrawable) {
        this(parentElement, parentViewGroup, (Drawable) bitmapDrawable);
    }

    public Drawable getDrawable() {
        return drawable;
    }

    /**
     * {@link PictureElement}s with children are automatically sized and this
     * has no effect!
     */
    @Override
    public void setSize(int width, int height) {
        if (!hasChildren()) {
            if (rightBottom instanceof PixelPoint) {
                PixelPoint rb = (PixelPoint) rightBottom;
                rb.setOffsetX(width);
                rb.setOffsetY(height);
            }
        }
    }

    @Override
    public void scale(float scaleFactor) {
        if (rightBottom instanceof PixelPoint) {
            PixelPoint rb = (PixelPoint) rightBottom;

            // If top left is a pixel point, scale centered
            // Else, just move bottom right
            if (topLeft instanceof PixelPoint) {
                int widthOffset = (int) (((rb.getOffsetX() * scaleFactor) - rb.getOffsetX()) / 2);
                int heightOffset = (int) (((rb.getOffsetY() * scaleFactor) - rb.getOffsetY()) / 2);

                PixelPoint tl = (PixelPoint) topLeft;
                tl.translate(-widthOffset, -heightOffset);
            }
            rb.setOffsetX((int) (scaleFactor * rb.getOffsetX()));
            rb.setOffsetY((int) (scaleFactor * rb.getOffsetY()));
        }
        updateBoundingBox();
        for (DrawableElement child : children) {
            child.updateBoundingBox();
        }
    }

    @Override
    public void addConnectionPoint(ScreenPoint position) {
        connectionPoints.add(position);
    }

    @Override
    public boolean hasConnectionPoints() {
        return !connectionPoints.isEmpty();
    }

    @Override
    public int getNearestConnectionPoint(Point worldPoint) {
        int lowestSquaredDistance = Integer.MAX_VALUE;
        int nearestPointIndex = 0;

        int i = 0;
        for (ScreenPoint connectionPoint : connectionPoints) {
            int squaredDistance = (int) (Math.pow(worldPoint.x - connectionPoint.getX(), 2) + Math.pow(worldPoint.y
                    - connectionPoint.getY(), 2));

            if (squaredDistance < lowestSquaredDistance) {
                lowestSquaredDistance = squaredDistance;
                nearestPointIndex = i;
            }
            i++;
        }
        return nearestPointIndex;
    }

    @Override
    public List<ScreenPoint> getConnectionPoints() {
        return connectionPoints;
    }

    @Override
    public void setParentViewGroup(ViewGroup parent) {
        this.parentViewGroup = parent;
    }

    @Override
    public void draw(Canvas canvas) {
        updateBoundingBox();
        drawable.setBounds(boundingBox);
        drawable.draw(canvas);

        for (ScreenPoint point : connectionPoints) {
            canvas.drawPoint(point.getX(), point.getY(), connectionPointPaint);
        }

        for (DrawableElement child : children) {
            child.draw(canvas);
        }
    }

    @Override
    public void setParentElement(DrawableElement drawableElement) {
        this.parentElement = drawableElement;
    }

    @Override
    public void updateBoundingBox() {

        if (hasChildren()) {
            assert (rightBottom instanceof PixelPoint);
            PixelPoint rightBottomPixelPoint = (PixelPoint) rightBottom;

            int biggestX = 0;
            int biggestY = 0;
            for (DrawableElement child : children) {
                int childX = child.getRightBottom().getX();
                if (biggestX < childX) {
                    biggestX = childX;
                }
                int childY = child.getRightBottom().getY();
                if (biggestY < childY) {
                    biggestY = childY;
                }
            }

            rightBottomPixelPoint.setOffsetX(biggestX);
            rightBottomPixelPoint.setOffsetY(biggestY);
        }
        boundingBox.left = topLeft.getX();
        boundingBox.top = topLeft.getY();
        boundingBox.right = rightBottom.getX();
        boundingBox.bottom = rightBottom.getY();
    }

    @Override
    public ScreenPoint getTopLeft() {
        return topLeft;
    }

    @Override
    public ScreenPoint getRightBottom() {
        return rightBottom;
    }

    @Override
    public Rect getBoundingBox() {
        return boundingBox;
    }

    @Override
    public void addChild(DrawableElement child) {
        child.setParentElement(this);
        children.add(child);
    }

    @Override
    public void setTopLeft(ScreenPoint topLeft) {
        this.topLeft = topLeft;
    }

    @Override
    public void setRightBottom(ScreenPoint rightBottom) {
        this.rightBottom = rightBottom;
    }

    @Override
    public void scaleByPixels(int pixels) {
        assert (rightBottom instanceof PixelPoint);
        PixelPoint rightBottomPixelPoint = (PixelPoint) rightBottom;
        rightBottomPixelPoint.translate(pixels, pixels);
    }

    @Override
    public boolean hasChildren() {
        return !children.isEmpty();
    }
}
