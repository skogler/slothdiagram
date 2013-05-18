package org.slothdiagram.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.util.AttributeSet;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

public class GraphicsView extends View {

	private List<Picture> pictures = new LinkedList<Picture>();

	public GraphicsView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

    public void addPicture (Picture p) {
        pictures.add(p);
        this.invalidate();
    }

	@Override
	protected void onDraw(Canvas canvas) {
		for (Picture p : pictures) {
            canvas.drawPicture(p);
		}
	}
}
