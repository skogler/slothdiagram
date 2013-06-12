package org.slothdiagram.ui;

import org.slothdiagram.DrawActivity;
import org.slothdiagram.PictureElement;
import org.slothdiagram.R;
import org.slothdiagram.TextElement;
import org.slothdiagram.points.PercentagePoint;
import org.slothdiagram.points.PixelPoint;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.jayway.android.robotium.solo.Solo;

public class ScreenElementTest extends ActivityInstrumentationTestCase2<DrawActivity> {

    private GraphicsView graphicsView;
    private DrawActivity drawActivity;
    private Solo solo;
    PictureElement screenElement;
    TextElement textElement;

    public ScreenElementTest() {
        super(DrawActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        drawActivity = getActivity();
        graphicsView = (GraphicsView) drawActivity.findViewById(R.id.graphicsView);
        drawActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                screenElement = getDummyScreenElement();
                textElement = new TextElement("asdf", graphicsView, graphicsView.getRootDrawableElement());
                textElement.setTopLeft(new PercentagePoint(screenElement, 0.5f, 0.5f));
                screenElement.addChild(textElement);
                graphicsView.addElement(screenElement);
            }
        });
        getInstrumentation().waitForIdleSync();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testTextElementResize() throws InterruptedException {
        int oldX = textElement.getRightBottom().getX();
        solo.enterText((EditText) textElement.getTextView(), "newnewasdfasdadfadsfasdfnewnewll");

        assertNotNull(textElement.getText());

        solo.waitForView(textElement.getTextView());
        assertTrue(textElement.getRightBottom().getX() > oldX);
    }

    private PictureElement getDummyScreenElement() {
        PictureElement screenElement = new PictureElement(graphicsView.getRootDrawableElement(), graphicsView,
                new BitmapDrawable(drawActivity.getResources(), BitmapFactory.decodeResource(
                        drawActivity.getResources(), R.raw.test)));

        int left = 30, top = 35;
        PixelPoint tl = (PixelPoint) screenElement.getTopLeft();
        tl.setOffsetX(left);
        tl.setOffsetY(35);
        int width = 20, height = 24;
        screenElement.setSize(width, height);

        return screenElement;
    }

    @Override
    protected void tearDown() throws Exception {
        drawActivity.finish();
        super.tearDown();
    }

}
