package org.slothdiagram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class ScreenText {

    private ScreenPoint position = new WorldPoint(0,0);
    private final EditText textView;

    public ScreenText(String text, Context context) {
        this.textView = new EditText(context);
        this.textView.addTextChangedListener(new ResizeTextWatcher());
        this.textView.setText(text);
    }

    public void setPosition(ScreenPoint point) {
        this.position = point;
    }

    public Rect getDimensions() {
        Rect rec = new Rect();
        rec.left = position.getX();
        rec.top = position.getY();
        rec.right = rec.left + textView.getWidth();
        rec.bottom = rec.top + textView.getHeight();
        return new Rect(textView.getLeft(), textView.getTop(), textView.getRight(), textView.getBottom());
    }

    public void render(Canvas canvas) {
        Point absolutePosition = position.getPoint();

        textView.layout(absolutePosition.x, absolutePosition.y, textView.getMeasuredWidth() + absolutePosition.x,
                textView.getMeasuredHeight() + absolutePosition.y);
        canvas.save();
        canvas.translate(absolutePosition.x, absolutePosition.y);
        textView.draw(canvas);
        canvas.restore();
    }

    public String getText() {
        return textView.getText().toString();
    }

    public void setText(String text) {
        textView.setText(text);
    }

    private class ResizeTextWatcher implements TextWatcher {

        @Override
        public void afterTextChanged(Editable s) {
            textView.measure(0, 0);
            textView.setWidth(textView.getMeasuredWidth());
            textView.setHeight(textView.getMeasuredHeight());

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

    }

    public TextView getTextView() {
        return textView;
    }

}
