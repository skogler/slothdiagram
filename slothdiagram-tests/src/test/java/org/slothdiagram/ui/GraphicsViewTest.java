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
}
