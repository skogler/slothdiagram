package org.slothdiagram.ui;

import org.slothdiagram.DrawActivity;
import org.slothdiagram.R;
import org.slothdiagram.ScreenElement;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

public class GraphicsViewTest extends ActivityInstrumentationTestCase2<DrawActivity> {

    private GraphicsView graphicsView;
    private DrawActivity drawActivity;

    public GraphicsViewTest() {
        super(DrawActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        drawActivity = getActivity();
        graphicsView = (GraphicsView) drawActivity.findViewById(R.id.graphicsView);
    }
    
    @UiThreadTest
    public void testDrawScreenElement() {
    	Bitmap before = Bitmap.createBitmap(graphicsView.getWidth(), graphicsView.getHeight(), Bitmap.Config.ARGB_8888);
    	Bitmap beforeValidate = Bitmap.createBitmap(graphicsView.getWidth(), graphicsView.getHeight(), Bitmap.Config.ARGB_8888);
    	Bitmap after = Bitmap.createBitmap(graphicsView.getWidth(), graphicsView.getHeight(), Bitmap.Config.ARGB_8888);

    	graphicsView.draw(new Canvas(before));
    	graphicsView.draw(new Canvas(beforeValidate));
    	
    	assertTrue(before.sameAs(beforeValidate));
    	
        ScreenElement screenElement = new ScreenElement(new BitmapDrawable(drawActivity.getResources(),
                BitmapFactory.decodeResource(drawActivity.getResources(), R.raw.test)));

        graphicsView.addScreenElement(screenElement);
        
        graphicsView.draw(new Canvas(after));
        
        //FIXME: this returns true
        //assertFalse(before.sameAs(after));
        
    }
}
