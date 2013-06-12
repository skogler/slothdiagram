package org.slothdiagram;

import java.util.List;

import org.slothdiagram.points.PercentagePoint;
import org.slothdiagram.points.PixelPoint;
import org.slothdiagram.ui.GraphicsView;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;

public class DrawActivity extends Activity {

    private GraphicsView graphicsView;
    private GridView sidebar;
    private SideBarFactory sideBarFactory;
    private List<DrawableElement> elements;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_draw);

        sideBarFactory = SideBarFactory.getInstance();
        elements = SideBarFactory.createSideBarElements();

        graphicsView = (GraphicsView) findViewById(R.id.graphicsView);
        sidebar = (GridView) findViewById(R.id.sidebar);

        setUpTestScene();
    }

    public void setUpTestScene() {
        final DrawableElement root = graphicsView.getRootDrawableElement();
        PictureElement pic1 = new PictureElement(root, graphicsView, new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.raw.test)));

        // Sidebar test start

        elements.add(pic1);
        elements.add(pic1);
        elements.add(pic1);
        elements.add(pic1);
        elements.add(pic1);
        elements.add(pic1);
        elements.add(pic1);

        SidebarElementAdapter adapter = new SidebarElementAdapter(this, elements);

        sidebar.setAdapter(adapter);

        // Sidebar test end

        pic1.setSize(50, 50);
        pic1.scale(2.0f);
        PixelPoint point = (PixelPoint) pic1.getTopLeft();
        point.setOffsetX(10);
        point.setOffsetY(50);
        pic1.addConnectionPoint(new PercentagePoint(pic1, 0.7f, 1.1f));

        TextElement stext = new TextElement("ll", graphicsView, pic1);
        pic1.addChild(stext);
        stext.setTopLeft(new PixelPoint(pic1.getTopLeft(), 50, 10));

        PictureElement pic2 = new PictureElement(root, graphicsView, new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.raw.test)));

        point = (PixelPoint) pic2.getTopLeft();
        point.setOffsetX(10);
        point.setOffsetY(50);

        point = (PixelPoint) pic2.getRightBottom();
        point.setOffsetX(100);
        point.setOffsetY(100);

        pic2.addConnectionPoint(new PercentagePoint(pic2, 0.0f, 0.5f));
        point = (PixelPoint) pic2.getTopLeft();
        point.setOffsetX(500);
        point.setOffsetY(200);
        pic2.scale(3.0f);

        Line line = new Line();
        line.addPoint(pic1.getConnectionPoints().get(0));
        line.addPoint(new PixelPoint(root.getTopLeft(), 125, 325));
        line.addPoint(pic2.getConnectionPoints().get(0));

        graphicsView.addElement(pic1);
        graphicsView.addElement(pic2);
        graphicsView.addElement(line);
        stext.setText("Hello scale!");
    }
}
