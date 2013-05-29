package org.slothdiagram;

import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.larvalabs.svgandroid.SVG;

public class ScreenElement {

    private Drawable drawable;
    private Point position;

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
        return position;
    }

    public void setPosition(int x, int y) {
        position = new Point(x, y);
    }

    public void setPosition(Point position) {
        this.position = position;

    }

}
