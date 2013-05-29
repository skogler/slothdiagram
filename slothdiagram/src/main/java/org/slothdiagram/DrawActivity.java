package org.slothdiagram;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import org.slothdiagram.ui.GraphicsView;

public class DrawActivity extends Activity {

    private GraphicsView graphicsView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_draw);
        graphicsView = (GraphicsView) findViewById(R.id.graphicsView);
        
        setUpTestScene();
    }
    
    public void setUpTestScene() {
        ScreenElement screenElement = new ScreenElement(new BitmapDrawable(
                getResources(), BitmapFactory.decodeResource(getResources(),
                        R.raw.test)));

        screenElement.setPosition(100, 100);
        screenElement.setSize(50, 50);
        screenElement.scale(2.0f);
        screenElement.setPosition(10, 50);
        
        screenElement.addConnectionPoint(new PointF(0.7f, 1.0f));
        
        ScreenElement screenElement2 = new ScreenElement(new BitmapDrawable(
                getResources(), BitmapFactory.decodeResource(getResources(),
                        R.raw.test)));

        screenElement2.setPosition(300, 400);
        screenElement2.setSize(50, 50);
        
        screenElement2.addConnectionPoint(new PointF(0.0f, 0.5f));
        screenElement2.setPosition(500, 200);
        screenElement2.scale(3.0f);
        
        Line line = new Line();
        line.addPoint(new ConnectionPoint(screenElement, 0));
        line.addPoint(new WorldPoint(125, 325));
        line.addPoint(new ConnectionPoint(screenElement2, 0));
        
        
        graphicsView.addScreenElement(screenElement);
        graphicsView.addScreenElement(screenElement2);
        graphicsView.addLine(line);
    }
}
