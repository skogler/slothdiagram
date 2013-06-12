package org.slothdiagram;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.slothdiagram.points.PercentagePoint;
import org.slothdiagram.points.PixelPoint;
import org.slothdiagram.points.ScreenPoint;

import android.graphics.Point;

@RunWith(RobolectricTestRunner.class)
public class ScreenPointTest {

    @Test
    public void testSimpleRelativePoint() {
        DrawableElement de = buildPictureElement(20, 30, 80, 100);
        ScreenPoint point = new PercentagePoint(de, 0.25f, 0.75f);
        de.updateBoundingBox();

        assertEquals(new Point(40, 105), point.getPoint());
        assertEquals(40, point.getX());
        assertEquals(105, point.getY());
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

}
