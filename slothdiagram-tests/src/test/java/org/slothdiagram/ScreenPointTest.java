package org.slothdiagram;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;

@RunWith(RobolectricTestRunner.class)
public class ScreenPointTest {

    @Test
    public void testSimpleConnectionPoint() {
        ScreenPoint point = new ConnectionPoint(getDummyScreenElement(), 0);
        assertEquals(new Point(35, 65), point.getPoint());
        assertEquals(35, point.getX());
        assertEquals(65, point.getY());
    }

    
    public ScreenElement getDummyScreenElement() {
        ScreenElement screenElement = new ScreenElement(mock(Drawable.class));

        int left = 30, top = 35;
        screenElement.setPosition(left, top);
        int width = 20, height = 40;
        screenElement.setSize(width, height);
        
        PointF p = new PointF(0.25f, 0.75f);
        screenElement.addConnectionPoint(p);

        return screenElement;
    }
}
