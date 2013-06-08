package org.slothdiagram;

import java.util.List;

import org.slothdiagram.points.AbsolutePoint;
import org.slothdiagram.points.ScreenPoint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class ScreenText implements DrawableElement {

    private ScreenPoint position = new AbsolutePoint(0, 0);
    private final EditText textView;
    private View parentView;
    private DrawableElement parentElement;

    public ScreenText(String text, View parent) {
        this.parentView = parent;
        textView = new EditText(parent.getContext());
        textView.setText(text);
        textView.addOnLayoutChangeListener(new OnLayoutChangeListener() {

            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                    int oldRight, int oldBottom) {
                if (parentElement != null)
                    parentElement.updateBounds();
            }
        });
        textView.setBackgroundColor(Color.RED);
    }

    public void setPosition(ScreenPoint point) {
        this.position = point;
    }

    @Override
    public Rect getDimensions() {
        Rect rec = new Rect();
        rec.left = position.getX();
        rec.top = position.getY();
        rec.right = rec.left + textView.getMeasuredWidth();
        rec.bottom = rec.top + textView.getMeasuredHeight();
        return rec;
    }

    public String getText() {
        return textView.getText().toString();
    }

    public void setText(String text) {
        textView.setText(text);
    }

    public TextView getTextView() {
        return textView;
    }

    @Override
    public Point getPosition() {
        return null;
    }

    @Override
    public void setPosition(int left, int top) {
    }

    @Override
    public void setSize(int width, int height) {
    }

    @Override
    public int getWidth() {
        return textView.getMeasuredWidth();
    }

    @Override
    public int getHeight() {
        return textView.getMeasuredHeight();
    }

    @Override
    public void addConnectionPoint(PointF connectionPoint) {
    }

    @Override
    public boolean hasConnectionPoints() {
        return false;
    }

    @Override
    public int getNearestConnectionPoint(Point worldPoint) {
        return 0;
    }

    @Override
    public List<PointF> getConnectionPoints() {
        return null;
    }

    @Override
    public void setParentView(View parent) {
        this.parentView = parent;
    }

    @Override
    public void draw(Canvas canvas) {
    }

    @Override
    public void setParentElement(DrawableElement drawableElement) {
        this.parentElement = drawableElement;
    }

    @Override
    public void updateBounds() {
    }
}
