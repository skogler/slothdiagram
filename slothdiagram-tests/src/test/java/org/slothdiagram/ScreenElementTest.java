package org.slothdiagram;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

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
        ScreenElement screenElement = new ScreenElement(mock(Drawable.class));
        int left = 30, top = 35;
        screenElement.setPosition(left, top);
        
        assertEquals(left, screenElement.getPosition().x);
        assertEquals(top,  screenElement.getPosition().y);
    }
    
    @Test
    public void testDimensions() {
        ScreenElement screenElement = new ScreenElement(mock(Drawable.class));
        int left = 30, top = 35;
        screenElement.setPosition(left, top);
        int width = 20, height = 25;
        screenElement.setSize(width, height);
        
        assertEquals(width,  screenElement.getWidth());
        assertEquals(height, screenElement.getHeight());
        assertEquals(new Rect(left, top, left+width, top+height), screenElement.getDimensions());
    }
    
    @Test
    public void testScaling() {
        ScreenElement screenElement = new ScreenElement(mock(Drawable.class));
        int left = 30, top = 35;
        screenElement.setPosition(left, top);
        int width = 20, height = 24;
        screenElement.setSize(width, height);
        float scaleFactor = 2.0f;
        
        screenElement.scale(scaleFactor);
        
        assertEquals(new Rect(20, 23, left+width+10, top+height+12), screenElement.getDimensions());
    }

    @Test
    public void testRepositioning() {
        ScreenElement screenElement = new ScreenElement(mock(Drawable.class));
        int left = 30, top = 35;
        screenElement.setPosition(left, top);
        int width = 20, height = 24;
        screenElement.setSize(width, height);
        
        left = 15;
        top = 20;
        screenElement.setPosition(15, 20);
        
        assertEquals(new Rect(left, top, left+width, top+height), screenElement.getDimensions());
    }
}