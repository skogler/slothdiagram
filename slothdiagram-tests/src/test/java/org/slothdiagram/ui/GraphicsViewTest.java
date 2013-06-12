package org.slothdiagram.ui;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.slothdiagram.DrawActivity;
import org.slothdiagram.PictureElement;
import org.slothdiagram.R;
import org.slothdiagram.points.PixelPoint;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

@RunWith(RobolectricTestRunner.class)
public class GraphicsViewTest {

    private DrawActivity drawActivity;
    private GraphicsView graphicsView;

    @Before
    public void setUp() throws Exception {
        drawActivity = Robolectric.buildActivity(DrawActivity.class).create().get();
        graphicsView = (GraphicsView) drawActivity.findViewById(R.id.graphicsView);
    }

    @Test
    public void testDraw() {
        PictureElement screenElement = buildPictureElement(0, 0, 0, 0);
        graphicsView.addElement(screenElement);

        Canvas testCanvas = new Canvas();
        graphicsView.onDraw(testCanvas);

        verify(screenElement.getDrawable()).draw(testCanvas);
    }

    @Test
    public void testDrawWithCorrectPosition() {
        int left = 30, top = 35;
        PictureElement screenElement = buildPictureElement(left, top, 0, 0);

        graphicsView.addElement(screenElement);
        Canvas testCanvas = new Canvas();
        graphicsView.onDraw(testCanvas);

        verify(screenElement.getDrawable()).setBounds(new Rect(left, top, left, top));
    }

    @Test
    public void testDrawWithSize() {
        int left = 30, top = 30;
        int width = 20;
        int height = 25;
        PictureElement screenElement = buildPictureElement(left, top, width, height);

        graphicsView.addElement(screenElement);
        Canvas testCanvas = new Canvas();
        graphicsView.onDraw(testCanvas);

        verify(screenElement.getDrawable()).setBounds(new Rect(left, top, left + width, top + height));
    }

    private PictureElement buildPictureElement(int x, int y, int width, int height) {
        PictureElement screenElement = new PictureElement(graphicsView.getRootDrawableElement(), graphicsView,
                mock(Drawable.class));
        PixelPoint tl = (PixelPoint) screenElement.getTopLeft();
        tl.setOffsetX(x);
        tl.setOffsetY(y);
        PixelPoint rb = (PixelPoint) screenElement.getRightBottom();
        rb.setOffsetX(width);
        rb.setOffsetY(height);
        return screenElement;
    }

}
