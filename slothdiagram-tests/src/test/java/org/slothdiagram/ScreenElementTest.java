package org.slothdiagram;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.slothdiagram.points.RelativePoint;

import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

@RunWith(RobolectricTestRunner.class)
public class ScreenElementTest {

    private DrawActivity drawActivity;

    @Before
    public void setUp() throws Exception {
        drawActivity = Robolectric.buildActivity(DrawActivity.class).create().get();
    }

    @Test
    public void testLoadFromBitmap() {
        ScreenElement screenElement = new ScreenElement(new BitmapDrawable(drawActivity.getResources(),
                BitmapFactory.decodeResource(drawActivity.getResources(), R.raw.test)));
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
        String appName = new DrawActivity().getResources().getString(R.string.app_name);
        assertEquals("slothdiagram", appName);
    }

    @Test
    public void testSetPosition() {
        DrawableElement screenElement = new ScreenElement(mock(Drawable.class));
        int left = 30, top = 35;
        screenElement.setPosition(left, top);

        assertEquals(left, screenElement.getPosition().x);
        assertEquals(top, screenElement.getPosition().y);
    }

    @Test
    public void testDimensions() {
        DrawableElement screenElement = new ScreenElement(mock(Drawable.class));
        int left = 30, top = 35;
        screenElement.setPosition(left, top);
        int width = 20, height = 25;
        screenElement.setSize(width, height);

        assertEquals(width, screenElement.getWidth());
        assertEquals(height, screenElement.getHeight());
        assertEquals(new Rect(left, top, left + width, top + height), screenElement.getDimensions());
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

        assertEquals(new Rect(20, 23, left + width + 10, top + height + 12), screenElement.getDimensions());
    }

    @Test
    public void testRepositioning() {
        DrawableElement screenElement = new ScreenElement(mock(Drawable.class));
        int left = 30, top = 35;
        screenElement.setPosition(left, top);
        int width = 20, height = 24;
        screenElement.setSize(width, height);

        left = 15;
        top = 20;
        screenElement.setPosition(15, 20);

        assertEquals(new Rect(left, top, left + width, top + height), screenElement.getDimensions());
    }

    @Test
    public void testConnectionPoints() {
        DrawableElement screenElement = getDummyScreenElement();
        // @formatter:off
        PointF leftTopCorner     = new PointF(0.0f, 0.0f);
        PointF rightTopCorner    = new PointF(1.0f, 0.0f);
        PointF leftBottomCorner  = new PointF(0.0f, 1.0f);
        PointF rightBottomCorner = new PointF(1.0f, 1.0f);
        // @formatter:on
        screenElement.addConnectionPoint(leftTopCorner);
        screenElement.addConnectionPoint(rightTopCorner);
        screenElement.addConnectionPoint(leftBottomCorner);
        screenElement.addConnectionPoint(rightBottomCorner);

        assertTrue(screenElement.hasConnectionPoints());
        assertFalse(screenElement.getConnectionPoints().isEmpty());
    }

    @Test
    public void testConnectionPointsToWorldCoordinates() {
        ScreenElement screenElement = new ScreenElement(mock(Drawable.class));

        int left = 30, top = 35;
        screenElement.setPosition(left, top);
        int width = 20, height = 24;
        screenElement.setSize(width, height);
        // @formatter:off
        PointF leftTopCorner     = new PointF(0.0f, 0.0f);
        PointF rightTopCorner    = new PointF(1.0f, 0.0f);
        PointF leftBottomCorner  = new PointF(0.0f, 1.0f);
        PointF rightBottomCorner = new PointF(1.0f, 1.0f);
        // @formatter:on
        screenElement.addConnectionPoint(leftTopCorner);
        screenElement.addConnectionPoint(rightTopCorner);
        screenElement.addConnectionPoint(leftBottomCorner);
        screenElement.addConnectionPoint(rightBottomCorner);

        Point p1 = screenElement.connectionPointToWorldPoint(0);
        Point p2 = screenElement.connectionPointToWorldPoint(1);
        Point p3 = screenElement.connectionPointToWorldPoint(2);
        Point p4 = screenElement.connectionPointToWorldPoint(3);

        assertEquals(new Point(left, top), p1);
        assertEquals(new Point(left + width, top), p2);
        assertEquals(new Point(left, top + height), p3);
        assertEquals(new Point(left + width, top + height), p4);
    }

    @Test
    public void testGetNearestConnectionPoint() {
        DrawableElement screenElement = new ScreenElement(mock(Drawable.class));

        int left = 30, top = 35;
        screenElement.setPosition(left, top);
        int width = 20, height = 24;
        screenElement.setSize(width, height);
        // @formatter:off
        PointF leftTopCorner     = new PointF(0.0f, 0.0f);
        PointF rightTopCorner    = new PointF(1.0f, 0.0f);
        PointF leftBottomCorner  = new PointF(0.0f, 1.0f);
        PointF rightBottomCorner = new PointF(1.0f, 1.0f);
        // @formatter:on
        screenElement.addConnectionPoint(leftTopCorner);
        screenElement.addConnectionPoint(rightTopCorner);
        screenElement.addConnectionPoint(leftBottomCorner);
        screenElement.addConnectionPoint(rightBottomCorner);

        assertEquals(0, screenElement.getNearestConnectionPoint(new Point(32, 40)));
        assertEquals(1, screenElement.getNearestConnectionPoint(new Point(43, 40)));
        assertEquals(2, screenElement.getNearestConnectionPoint(new Point(32, 55)));
        assertEquals(3, screenElement.getNearestConnectionPoint(new Point(43, 50)));

        assertEquals(0, screenElement.getNearestConnectionPoint(new Point(20, 10)));
        assertEquals(0, screenElement.getNearestConnectionPoint(new Point(-1, -1)));
    }
    
    @Test
    public void testAddTextToScreenElement() {
        ScreenText screenText = new ScreenText("asdf", new View(drawActivity));
        ScreenElement dummyScreenElement = getDummyScreenElement();
        screenText.setPosition(new RelativePoint(dummyScreenElement, new PointF(0.0f, 0.0f)));
        
        dummyScreenElement.addText(screenText);
        assertFalse(dummyScreenElement.getTextElements().isEmpty());
        assertTrue(dummyScreenElement.getTextElements().contains(screenText));
    }
    

    private ScreenElement getDummyScreenElement() {
        ScreenElement screenElement = new ScreenElement(mock(Drawable.class));

        int left = 30, top = 35;
        screenElement.setPosition(left, top);
        int width = 20, height = 24;
        screenElement.setSize(width, height);

        return screenElement;
    }
}