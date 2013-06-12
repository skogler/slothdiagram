package org.slothdiagram;

import java.util.List;

import org.slothdiagram.points.PixelPoint;
import org.slothdiagram.points.ScreenPoint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class TextElement implements DrawableElement {

    private final EditText textView;
    private final LayoutParams textViewLayoutParams;

    private ViewGroup parentView;
    private DrawableElement parentElement;
    private final Rect boundingBox = new Rect();

    private ScreenPoint topLeft;
    private final PixelPoint rightBottom;

    public TextElement(String text, final ViewGroup parentView, final DrawableElement parentElement) {
        this.parentView = parentView;
        this.parentElement = parentElement;

        topLeft = new PixelPoint(parentElement.getTopLeft(), 0, 0);
        rightBottom = new PixelPoint(topLeft, 0, 0);
        textView = new EditText(parentView.getContext());

        textViewLayoutParams = new RelativeLayout.LayoutParams(MarginLayoutParams.WRAP_CONTENT,
                MarginLayoutParams.WRAP_CONTENT);

        textView.setLayoutParams(textViewLayoutParams);
        textView.setBackgroundColor(Color.RED);
        textView.setText(text);
        parentView.addView(textView);
        textView.requestLayout();

        textView.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                    int oldRight, int oldBottom) {
                int newRight = textView.getMeasuredWidth();
                int newBottom = textView.getMeasuredHeight();

                rightBottom.setOffsetX(newRight);
                rightBottom.setOffsetY(newBottom);

                updateBoundingBox();

                if (parentElement != null)
                    parentElement.updateBoundingBox();

                parentView.invalidate();
            }
        });

    }

    @Override
    public Rect getBoundingBox() {
        return boundingBox;
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
    public void setSize(int width, int height) {
        // This element is automatically sized
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
    public List<ScreenPoint> getConnectionPoints() {
        return null;
    }

    @Override
    public void setParentViewGroup(ViewGroup parent) {
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
    public void updateBoundingBox() {
        boundingBox.left = topLeft.getX();
        boundingBox.top = topLeft.getY();

        boundingBox.right = rightBottom.getX();
        boundingBox.bottom = rightBottom.getY();

        textViewLayoutParams.setMargins(boundingBox.left, boundingBox.top, 0, 0);
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
    public void addChild(DrawableElement child) {
    }

    @Override
    public void setTopLeft(ScreenPoint topLeft) {
        this.topLeft = topLeft;
        this.rightBottom.setParentPoint(topLeft);
    }

    @Override
    public void setRightBottom(ScreenPoint rightBottom) {
        // TODO: find a cleaner solution for this?
        // this is intentionally not implemented
    }

    @Override
    public void addConnectionPoint(ScreenPoint position) {
        // TODO implement
    }

    @Override
    public boolean hasChildren() {
        return false;
    }
}
