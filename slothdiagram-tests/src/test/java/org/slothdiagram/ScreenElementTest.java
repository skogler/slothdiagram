package org.slothdiagram;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.slothdiagram.points.PercentagePoint;
import org.slothdiagram.points.PixelPoint;
import org.slothdiagram.points.ScreenPoint;
import org.slothdiagram.ui.GraphicsView;

import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

@RunWith(RobolectricTestRunner.class)
public class ScreenElementTest {

    private DrawActivity drawActivity;
    private GraphicsView graphicsView;

    @Before
    public void setUp() throws Exception {
        drawActivity = Robolectric.buildActivity(DrawActivity.class).create().get();
        graphicsView = (GraphicsView) drawActivity.findViewById(R.id.graphicsView);
    }

    @Test
    public void testLoadFromBitmap() {
        PictureElement screenElement = new PictureElement(graphicsView.getRootDrawableElement(), graphicsView,
                new BitmapDrawable(drawActivity.getResources(), BitmapFactory.decodeResource(
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
    public void testSetPosition() {
        int left = 30, top = 35;
        DrawableElement screenElement = buildPictureElement(left, top, 0, 0);

        assertEquals(left, screenElement.getTopLeft().getX());
        assertEquals(top, screenElement.getTopLeft().getY());
    }

    @Test
    public void testBoundingBox() {
        int left = 30, top = 35;
        int width = 20, height = 25;
        DrawableElement screenElement = buildPictureElement(left, top, width, height);

        screenElement.updateBoundingBox();

        assertEquals(width, screenElement.getBoundingBox().width());
        assertEquals(height, screenElement.getBoundingBox().height());
        assertEquals(new Rect(left, top, left + width, top + height), screenElement.getBoundingBox());
    }

    @Test
    public void testScaling() {
        int left = 30, top = 35;
        int width = 20, height = 24;
        PictureElement screenElement = buildPictureElement(left, top, width, height);

        float scaleFactor = 2.0f;
        screenElement.scale(scaleFactor);
        screenElement.updateBoundingBox();

        int xChange = (int) ((width * scaleFactor - width) / 2);
        int yChange = (int) ((height * scaleFactor - height) / 2);

        assertEquals(new Rect(left - xChange, top - yChange, left + width + xChange, top + height + yChange),
                screenElement.getBoundingBox());
    }

    @Test
    public void testRepositioning() {
        DrawableElement screenElement = TestUtils.getInstance().getDummyScreenElement();
        int left = 30, top = 35;
        PixelPoint tl = (PixelPoint) screenElement.getTopLeft();
        tl.setOffsetX(left);
        tl.setOffsetY(35);
        int width = 20, height = 24;
        screenElement.setSize(width, height);

        left = 15;
        top = 20;
        tl.setOffsetX(left);
        tl.setOffsetY(top);

        screenElement.updateBoundingBox();
        assertEquals(new Rect(left, top, left + width, top + height), screenElement.getBoundingBox());
    }

    @Test
    public void testConnectionPoints() {
        DrawableElement screenElement = TestUtils.getInstance().getDummyScreenElement();
        addTestConnectionPoints(screenElement);

        screenElement.updateBoundingBox();
        assertTrue(screenElement.hasConnectionPoints());
        assertFalse(screenElement.getConnectionPoints().isEmpty());
    }

    @Test
    public void testGetNearestConnectionPoint() {
        DrawableElement screenElement = buildPictureElement(30, 35, 20, 24);
        addTestConnectionPoints(screenElement);
        screenElement.updateBoundingBox();

        assertEquals(0, screenElement.getNearestConnectionPoint(new Point(32, 40)));
        assertEquals(1, screenElement.getNearestConnectionPoint(new Point(43, 40)));
        assertEquals(2, screenElement.getNearestConnectionPoint(new Point(32, 55)));
        assertEquals(3, screenElement.getNearestConnectionPoint(new Point(43, 50)));

        assertEquals(0, screenElement.getNearestConnectionPoint(new Point(20, 10)));
        assertEquals(0, screenElement.getNearestConnectionPoint(new Point(-1, -1)));
    }

    @Test
    public void testAddTextToScreenElement() {
        PictureElement dummyScreenElement = TestUtils.getInstance().getDummyScreenElement();
        TextElement screenText = buildTextElement("asdf", dummyScreenElement);

        dummyScreenElement.addChild(screenText);
        assertTrue(dummyScreenElement.hasChildren());
    }

    private PictureElement buildPictureElement(int x, int y, int width, int height) {
        PictureElement screenElement = TestUtils.getInstance().getDummyScreenElement();
        PixelPoint tl = (PixelPoint) screenElement.getTopLeft();
        tl.setOffsetX(x);
        tl.setOffsetY(y);
        PixelPoint rb = (PixelPoint) screenElement.getRightBottom();
        rb.setOffsetX(width);
        rb.setOffsetY(height);
        return screenElement;
    }

    private TextElement buildTextElement(String text, DrawableElement parent) {
        return new TextElement(text, graphicsView, parent);
    }

    private void addTestConnectionPoints(DrawableElement element) {
        // @formatter:off
        ScreenPoint leftTopCorner     = new PercentagePoint(element, 0.0f, 0.0f);
        ScreenPoint rightTopCorner    = new PercentagePoint(element, 1.0f, 0.0f);
        ScreenPoint leftBottomCorner  = new PercentagePoint(element, 0.0f, 1.0f);
        ScreenPoint rightBottomCorner = new PercentagePoint(element, 1.0f, 1.0f);
        // @formatter:on
        element.addConnectionPoint(leftTopCorner);
        element.addConnectionPoint(rightTopCorner);
        element.addConnectionPoint(leftBottomCorner);
        element.addConnectionPoint(rightBottomCorner);
    }

}