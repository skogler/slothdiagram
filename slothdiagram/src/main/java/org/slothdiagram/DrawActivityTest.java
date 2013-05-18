package org.slothdiagram;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import com.larvalabs.svgandroid.SVG;
import org.slothdiagram.DrawActivity;
import org.slothdiagram.R;

public class DrawActivityTest extends ActivityInstrumentationTestCase2<DrawActivity> {

    private DrawActivity drawActivity;

    public DrawActivityTest(){
        super(DrawActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        drawActivity = getActivity();
    }


    public void testLoadSvg(){
        drawActivity.loadImage(R.raw.test_image);
        assertTrue(drawActivity.isImageLoaded(R.raw.test_image));
    }

    public void testDrawSvg () {
        drawActivity.loadImage(R.raw.test_image);
        int xPosition= 10;
        int yPosition = 10;
        drawActivity.drawImage(R.raw.test_image, xPosition, yPosition);
    }



}
