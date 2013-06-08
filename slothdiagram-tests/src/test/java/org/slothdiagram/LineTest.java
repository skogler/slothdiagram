package org.slothdiagram;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.slothdiagram.points.ConnectionPoint;
import org.slothdiagram.points.AbsolutePoint;

import android.graphics.drawable.Drawable;



@RunWith(RobolectricTestRunner.class)
public class LineTest {
    
    @Test
    public void testSimpleLine() {
        Line line = new Line();
        line.addPoint(new AbsolutePoint(0,0));
        line.addPoint(new ConnectionPoint(getDummyScreenElement(),0));
        line.addPoint(new AbsolutePoint(10,15));
        line.addPoint(new AbsolutePoint(15,10));
        assertFalse(line.getPoints().isEmpty());
    }

    public ScreenElement getDummyScreenElement() {
        ScreenElement screenElement = new ScreenElement(mock(Drawable.class));

        int left = 30, top = 35;
        screenElement.setPosition(left, top);
        int width = 20, height = 24;
        screenElement.setSize(width, height);

        return screenElement;
    }
}
