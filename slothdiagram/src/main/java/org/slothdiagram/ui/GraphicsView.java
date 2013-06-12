package org.slothdiagram.ui;

import java.util.LinkedList;
import java.util.List;

import org.slothdiagram.DrawableElement;
import org.slothdiagram.InvisibleElement;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class GraphicsView extends RelativeLayout {

    private final List<DrawableElement> elements = new LinkedList<DrawableElement>();
    private final DrawableElement rootElement;

    public GraphicsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        rootElement = new InvisibleElement();
    }

    public DrawableElement getRootDrawableElement() {
        return rootElement;
    }

    public void addElement(DrawableElement drawableElement) {
        elements.add(drawableElement);
        // drawableElement.setParentViewGroup(this);

        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas); // Draw all android views
        for (DrawableElement e : elements) {
            e.draw(canvas);
        }
    }
}
