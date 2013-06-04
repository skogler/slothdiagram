package org.slothdiagram.ui;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slothdiagram.Line;
import org.slothdiagram.ScreenElement;
import org.slothdiagram.ScreenPoint;
import org.slothdiagram.ScreenText;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class GraphicsView extends ViewGroup {

    private final Paint connectionPointPaint;
    private final Paint linePaint;

    private final List<ScreenElement> screenElements = new LinkedList<ScreenElement>();
    private final List<Line> lines = new LinkedList<Line>();

    public GraphicsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        connectionPointPaint = new Paint();
        connectionPointPaint.setStrokeWidth(5);

        linePaint = new Paint();
        linePaint.setStrokeWidth(5);
        linePaint.setStyle(Style.STROKE);
        linePaint.setAntiAlias(true);
    }

    public void addScreenElement(ScreenElement p) {
        screenElements.add(p);

        ArrayList<View> toAdd = new ArrayList<View>();
        for (ScreenText screenText : p.getTextElements()) {
            toAdd.add(screenText.getTextView());
            this.addView(screenText.getTextView());
        }
        // this.addTouchables(toAdd);


        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (ScreenElement e : screenElements) {
            Drawable drawable = e.getDrawable();
            drawable.setBounds(e.getDimensions());
            drawable.draw(canvas);

            for (int i = 0; i < e.getConnectionPoints().size(); ++i) {
                Point connectionPoint = e.connectionPointToWorldPoint(i);
                canvas.drawPoint(connectionPoint.x, connectionPoint.y, connectionPointPaint);
            }
            // for (ScreenText st : e.getTextElements()) {
            // st.render(canvas);
            // }
        }

        for (Line l : lines) {
            Path path = new Path();
            boolean first = true;
            for (ScreenPoint p : l.getPoints()) {
                if (first) {
                    path.moveTo(p.getX(), p.getY());
                    first = false;
                } else {
                    path.lineTo(p.getX(), p.getY());
                }
                canvas.drawPath(path, linePaint);
            }
        }
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }


}
