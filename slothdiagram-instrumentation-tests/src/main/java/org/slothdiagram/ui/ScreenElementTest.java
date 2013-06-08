package org.slothdiagram.ui;

import org.slothdiagram.DrawActivity;
import org.slothdiagram.R;
import org.slothdiagram.ScreenElement;
import org.slothdiagram.ScreenText;
import org.slothdiagram.points.RelativePoint;

import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.jayway.android.robotium.solo.Solo;

public class ScreenElementTest extends ActivityInstrumentationTestCase2<DrawActivity> {

    private GraphicsView graphicsView;
    private DrawActivity drawActivity;
    private Solo solo;
    ScreenElement screenElement;
    ScreenText textElement;

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
                textElement = new ScreenText("asdf", graphicsView);
                textElement.setPosition(new RelativePoint(screenElement, new PointF(0.5f, 0.5f)));
                screenElement.addText(textElement);
                graphicsView.addElement(screenElement);
            }
        });
        getInstrumentation().waitForIdleSync();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testTextElementResize() throws InterruptedException {
        int oldwidth = textElement.getDimensions().width();
        solo.enterText((EditText) textElement.getTextView(), "newnewasdfasdadfadsfasdfnewnewll");
       
        
        assertNotNull(textElement.getText());
        
        solo.waitForView(textElement.getTextView());
        assertTrue(textElement.getDimensions().width() > oldwidth);
    }

    private ScreenElement getDummyScreenElement() {
        ScreenElement screenElement = new ScreenElement(new BitmapDrawable(drawActivity.getResources(),
                BitmapFactory.decodeResource(drawActivity.getResources(), R.raw.test)));

        int left = 30, top = 35;
        screenElement.setPosition(left, top);
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
