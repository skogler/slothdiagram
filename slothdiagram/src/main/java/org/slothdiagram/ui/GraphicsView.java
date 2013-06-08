package org.slothdiagram.ui;

import java.util.LinkedList;
import java.util.List;

import org.slothdiagram.DrawableElement;
import org.slothdiagram.ScreenElement;
import org.slothdiagram.ScreenText;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class GraphicsView extends RelativeLayout {

    private final List<DrawableElement> elements = new LinkedList<DrawableElement>();

    public GraphicsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void addElement(DrawableElement drawableElement) {
        elements.add(drawableElement);
        drawableElement.setParentView(this);

        if (drawableElement instanceof ScreenElement) {
            ScreenElement screenElement = (ScreenElement) drawableElement;
            for (ScreenText screenText : screenElement.getTextElements()) {
                this.addView(screenText.getTextView());
            }
        }

        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (DrawableElement e : elements) {
            e.draw(canvas);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (DrawableElement drawableElement : elements) {
            if (drawableElement instanceof ScreenElement) {
                ScreenElement screenElement = (ScreenElement) drawableElement;
                for (ScreenText screenText : screenElement.getTextElements()) {
                    Rect dimensions = screenText.getDimensions();
                    screenText.getTextView().layout(dimensions.left, dimensions.top, dimensions.right,
                            dimensions.bottom);
                }
            }
        }
    }

}
