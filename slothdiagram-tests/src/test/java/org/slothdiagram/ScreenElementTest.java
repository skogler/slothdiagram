package org.slothdiagram;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGBuilder;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

@RunWith(RobolectricTestRunner.class)
public class ScreenElementTest {

	private DrawActivity drawActivity;

	@Before
	public void setUp() throws Exception {
		drawActivity = Robolectric.buildActivity(DrawActivity.class).create()
				.get();
	}

	@Test
	public void testLoadFromBitmap() {
		ScreenElement screenElement = new ScreenElement(new BitmapDrawable(
				drawActivity.getResources(), BitmapFactory.decodeResource(
						drawActivity.getResources(), R.raw.test)));
		assertNotNull(screenElement.getDrawable());
	}

	@Test
	public void testLoadFromSVG() {
//		SVG testSvg = new SVGBuilder().readFromResource(drawActivity.getResources(), R.raw.test_svg).build();
//		ScreenElement screenElement = new ScreenElement(testSvg);
//		assertNotNull(screenElement.getDrawable());
	}

	@Test
	public void testAppNameLoadedCorrectly() throws Exception {
		String appName = new DrawActivity().getResources().getString(
				R.string.app_name);
		assertEquals("slothdiagram", appName);
	}

}