package org.slothdiagram;

import android.app.Activity;
import android.graphics.Picture;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGBuilder;
import org.slothdiagram.ui.GraphicsView;

import java.util.HashMap;
import java.util.Map;

public class DrawActivity extends Activity {

    private Map<Integer, Picture> loadedImages = new HashMap<Integer, Picture>();
    private GraphicsView graphicsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_draw);
        graphicsView = (GraphicsView) findViewById(R.id.graphicsView);
        //
        loadImage(R.raw.test_image);
        int xPosition= 10;
        int yPosition = 10;
        drawImage(R.raw.test_image, xPosition, yPosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.draw, menu);
        return true;
    }

    public void loadImage(int image) {
        Picture p = new SVGBuilder().readFromResource(getResources(), image).build().getPicture();
        if(p != null) {
            loadedImages.put(image, p);
        }
    }

    public boolean isImageLoaded(int test_image) {
        return loadedImages.containsKey(test_image);
    }

    public void drawImage(int test_image, int xPosition, int yPosition) {
        System.out.println(loadedImages.get(test_image).getHeight());
        System.out.println(loadedImages.get(test_image).getWidth());
        graphicsView.addPicture(loadedImages.get(test_image));
    }
}
