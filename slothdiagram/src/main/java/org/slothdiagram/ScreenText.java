package org.slothdiagram;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;

public class ScreenText {

    private ScreenPoint position = new WorldPoint(0, 0);
    private final EditText textView;

    public ScreenText(String text, Context context) {
        this.textView = new EditText(context);
        this.textView.addTextChangedListener(new ResizeTextWatcher());
        this.textView.setText(text);
        textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        this.textView.setBackgroundColor(Color.RED);
    }

    public void setPosition(ScreenPoint point) {
        this.position = point;
    }

    public Rect getDimensions() {
        textView.measure(0, 0);
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

    private class ResizeTextWatcher implements TextWatcher {

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            textView.invalidate();

        }

    }

    public TextView getTextView() {
        return textView;
    }

}
