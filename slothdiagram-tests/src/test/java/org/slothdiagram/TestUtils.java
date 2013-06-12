package org.slothdiagram;

import static org.mockito.Mockito.mock;

import org.slothdiagram.points.PixelPoint;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

public class TestUtils {

    private TestUtils() {
    }

    private static class SingletonHolder {
        public static final TestUtils INSTANCE = new TestUtils();

    }

    public static TestUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public PictureElement getDummyScreenElement() {
        InvisibleElement root = new InvisibleElement();
        PictureElement screenElement = new PictureElement(root, mock(ViewGroup.class), mock(Drawable.class));

        int left = 30, top = 35;
        PixelPoint tl = (PixelPoint) screenElement.getTopLeft();
        tl.setOffsetX(left);
        tl.setOffsetY(35);
        int width = 20, height = 24;
        screenElement.setSize(width, height);

        return screenElement;
    }

}
