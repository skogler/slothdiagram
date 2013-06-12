package org.slothdiagram;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.slothdiagram.points.AbsolutePoint;
import org.slothdiagram.points.PercentagePoint;
import org.slothdiagram.points.PixelPoint;



@RunWith(RobolectricTestRunner.class)
public class LineTest {
    
    @Test
    public void testSimpleLine() {
        AbsolutePoint center = new AbsolutePoint(0, 0);

        Line line = new Line();
        line.addPoint(new PixelPoint(center, 0, 0));
        line.addPoint(new PercentagePoint(TestUtils.getInstance().getDummyScreenElement(), 0.5f, 0.7f));
        line.addPoint(new PixelPoint(center, 0, 15));
        line.addPoint(new PixelPoint(center, 15, 10));
        assertFalse(line.getPoints().isEmpty());
    }


}
