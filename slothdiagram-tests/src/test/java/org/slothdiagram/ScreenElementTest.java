package org.slothdiagram;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.graphics.BitmapFactory;
import android.graphics.Point;
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
        // SVG testSvg = new
        // SVGBuilder().readFromResource(drawActivity.getResources(),
        // R.raw.test_svg).build();
        // ScreenElement screenElement = new ScreenElement(testSvg);
        // assertNotNull(screenElement.getDrawable());
    }

    @Test
    public void testAppNameLoadedCorrectly() throws Exception {
        String appName = new DrawActivity().getResources().getString(
                R.string.app_name);
        assertEquals("slothdiagram", appName);
    }
    
    
    @Test
    public void testSetPosition() {
        ScreenElement screenElement = new ScreenElement(new BitmapDrawable(
                drawActivity.getResources(), BitmapFactory.decodeResource(
                        drawActivity.getResources(), R.raw.test)));
        
        screenElement.setPosition(new Point(35, 35));
        
        assertEquals(screenElement.getPosition().x, 35);
        assertEquals(screenElement.getPosition().y, 35);
        
        Point p = new Point(30,30);
        screenElement.setPosition(p.x, p.y);
        
        assertEquals(screenElement.getPosition(), p);
    }

}