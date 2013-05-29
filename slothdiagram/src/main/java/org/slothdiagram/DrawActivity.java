package org.slothdiagram;

import android.app.Activity;
import android.graphics.BitmapFactory;
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

        ScreenElement screenElement = new ScreenElement(new BitmapDrawable(
                getResources(), BitmapFactory.decodeResource(getResources(),
                        R.raw.test)));

        renderScreenElement(screenElement);
    }

    public void renderScreenElement(ScreenElement screenElement) {
        graphicsView.addScreenElement(screenElement);
    }
}
