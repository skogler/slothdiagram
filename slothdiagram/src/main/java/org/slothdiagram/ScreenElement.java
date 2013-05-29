package org.slothdiagram;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.larvalabs.svgandroid.SVG;

public class ScreenElement {

    private Drawable drawable;
    private Rect dimension = new Rect(0, 0, 0, 0);

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
        int width = (dimension.right - dimension.left);
        int height = (dimension.bottom- dimension.top);
        int widthOffset  = (int) (((width * scaleFactor ) - width) / 2);
        int heightOffset = (int) (((height * scaleFactor )- height)/ 2);
        
        dimension.left -= widthOffset;
        dimension.right += widthOffset;
        dimension.top -= heightOffset;
        dimension.bottom += heightOffset;
    }
}
