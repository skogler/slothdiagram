package org.slothdiagram.ui;

import java.util.LinkedList;
import java.util.List;

import org.slothdiagram.Line;
import org.slothdiagram.ScreenElement;
import org.slothdiagram.ScreenPoint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class GraphicsView extends View {

    private Paint connectionPointPaint;
    private Paint linePaint;
    
    private List<ScreenElement> screenElements = new LinkedList<ScreenElement>();
    private List<Line> lines = new LinkedList<Line>();

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
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (ScreenElement e : screenElements) {
            Drawable drawable = e.getDrawable();
            drawable.setBounds(e.getDimensions());
            drawable.draw(canvas);

            for (int i = 0; i < e.getConnectionPoints().size(); ++i) {
                Point connectionPoint = e.connectionPointToWorldPoint(i);
                canvas.drawPoint(connectionPoint.x, connectionPoint.y, connectionPointPaint);
            }
        }
        
        for (Line l: lines) {
            Path path = new Path();
            boolean first = true;
            for (ScreenPoint p: l.getPoints()) {
                if (first)
                {
                    path.moveTo(p.getX(), p.getY());
                    first = false;
                }
                else
                {
                    path.lineTo(p.getX(), p.getY());
                }
                canvas.drawPath(path, linePaint);
            }
        }
    }

    public void addLine(Line line) {
        lines.add(line);
    }
}
