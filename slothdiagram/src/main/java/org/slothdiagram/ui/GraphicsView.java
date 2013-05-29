package org.slothdiagram.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import org.slothdiagram.ScreenElement;

import java.util.LinkedList;
import java.util.List;

public class GraphicsView extends View {

    private Paint paint;
    private List<ScreenElement> screenElements = new LinkedList<ScreenElement>();

    public GraphicsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStrokeWidth(5);
    }

    public void addScreenElement(ScreenElement p) {
        screenElements.add(p);
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (ScreenElement e : screenElements) {
            Drawable drawable = e.getDrawable();
            drawable.setBounds(100, 100, 400, 400);
            drawable.draw(canvas);
        }
    }
}
