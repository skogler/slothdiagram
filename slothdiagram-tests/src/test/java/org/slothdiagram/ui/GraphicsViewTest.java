package org.slothdiagram.ui;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.slothdiagram.DrawActivity;
import org.slothdiagram.R;
import org.slothdiagram.ScreenElement;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

@RunWith(RobolectricTestRunner.class)
public class GraphicsViewTest {

    private DrawActivity drawActivity;
    private GraphicsView graphicsView;

    @Before
    public void setUp() throws Exception {
        drawActivity = Robolectric.buildActivity(DrawActivity.class).create()
                .get();
        graphicsView = (GraphicsView) drawActivity
                .findViewById(R.id.graphicsView);
    }

    @Test
    public void testDraw() {
        ScreenElement screenElement = new ScreenElement(mock(Drawable.class));

        graphicsView.addScreenElement(screenElement);

        Canvas testCanvas = new Canvas();
        graphicsView.onDraw(testCanvas);
        verify(screenElement.getDrawable()).draw(testCanvas);
    }
    
    @Test
    public void testDrawWithCorrectPosition() {
        ScreenElement screenElement = new ScreenElement(mock(Drawable.class));
        int left = 30, top = 35;
        screenElement.setPosition(left, top);
        
        graphicsView.addScreenElement(screenElement);

        Canvas testCanvas = new Canvas();
        graphicsView.onDraw(testCanvas);
        verify(screenElement.getDrawable()).setBounds(new Rect(left, top, 0, 0));
    }
    
    @Test
    public void testDrawWithSize() {
        ScreenElement screenElement = new ScreenElement(mock(Drawable.class));
        int left = 30, top = 30;
        int width = 20;
        int height = 25;
        screenElement.setPosition(left, top);
        screenElement.setSize(width, height);
        
        graphicsView.addScreenElement(screenElement);

        Canvas testCanvas = new Canvas();
        graphicsView.onDraw(testCanvas);
        verify(screenElement.getDrawable()).setBounds(new Rect(left, top, left+width, top+height));
    }
    
}
