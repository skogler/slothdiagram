package org.slothdiagram.ui;

import java.util.ArrayList;
import java.util.List;

import org.slothdiagram.DrawActivity;
import org.slothdiagram.DrawableElement;
import org.slothdiagram.PictureElement;
import org.slothdiagram.R;
import org.slothdiagram.SidebarElementAdapter;
import org.slothdiagram.points.PixelPoint;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.GridView;

public class GraphicsViewTest extends ActivityInstrumentationTestCase2<DrawActivity> {

    private GraphicsView graphicsView;
    private DrawActivity drawActivity;
    private GridView sidebar;

    private List<DrawableElement> elements;

    public GraphicsViewTest() {
        super(DrawActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        drawActivity = getActivity();
        graphicsView = (GraphicsView) drawActivity.findViewById(R.id.graphicsView);

        sidebar = (GridView) drawActivity.findViewById(R.id.sidebar);
        elements = createTestScreenElements();
    }

    @UiThreadTest
    public void testDrawScreenElement() {
        PictureElement screenElement = new PictureElement(graphicsView.getRootDrawableElement(), graphicsView,
                new BitmapDrawable(drawActivity.getResources(), BitmapFactory.decodeResource(
                        drawActivity.getResources(), R.raw.test)));

        graphicsView.addElement(screenElement);
    }

    @UiThreadTest
    public void testSideBarElementDraww() {
        SidebarElementAdapter adapter = new SidebarElementAdapter(drawActivity.getApplicationContext(), elements);
        sidebar.setAdapter(adapter);
        assertNotNull(sidebar.getAdapter().getCount());
    }

    private List<DrawableElement> createTestScreenElements() {
        elements = new ArrayList<DrawableElement>();

        PictureElement screenElement1 = buildScreenElement(R.raw.test);
        PictureElement screenElement2 = buildScreenElement(R.raw.mini_test_icon);

        elements.add(screenElement1);
        elements.add(screenElement2);
        elements.add(screenElement1);
        elements.add(screenElement1);
        elements.add(screenElement2);
        elements.add(screenElement1);
        elements.add(screenElement1);
        elements.add(screenElement2);
        elements.add(screenElement1);
        elements.add(screenElement1);
        elements.add(screenElement2);

        return elements;
    }

    private PictureElement buildScreenElement(int imageId) {
        PictureElement screenElement = new PictureElement(graphicsView.getRootDrawableElement(), graphicsView,
                new BitmapDrawable(drawActivity.getResources(), BitmapFactory.decodeResource(
                        drawActivity.getResources(), imageId)));

        int left = 30, top = 35;
        PixelPoint tl = (PixelPoint) screenElement.getTopLeft();
        tl.setOffsetX(left);
        tl.setOffsetY(35);
        int width = 20, height = 24;
        screenElement.setSize(width, height);

        return screenElement;
    }
}
