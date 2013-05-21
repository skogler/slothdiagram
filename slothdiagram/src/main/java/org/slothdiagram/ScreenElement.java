package org.slothdiagram;

import com.larvalabs.svgandroid.SVG;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ScreenElement {

	private Drawable drawable;

	public ScreenElement(BitmapDrawable bitmapDrawable) {
		drawable = bitmapDrawable;
	}

	public ScreenElement(SVG svg) {
		drawable = svg.getDrawable();
	}

	public Drawable getDrawable() {
		return drawable;
	}

}
