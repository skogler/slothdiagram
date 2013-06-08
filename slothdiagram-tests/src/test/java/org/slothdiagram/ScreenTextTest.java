package org.slothdiagram;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;

@RunWith(RobolectricTestRunner.class)
public class ScreenTextTest {

    @Test
    public void testPositioning() {
        ScreenText text = new ScreenText("asdf", new View(new Activity()));
        assertNotNull(text.getDimensions());
    }

    @Test
    public void testExpansionWithinScreenElement(){
        // ScreenElement screenElement = getDummyScreenElement();
        // ScreenText textElement = new ScreenText("asdf", new View(new
        // Activity()));
        // int oldwidth = textElement.getDimensions().width();
        // screenElement.addText(textElement);
        // textElement.setText("newnewnewnewnewnew");
        // assertNotNull(textElement.getText());
        // assertTrue(textElement.getDimensions().width() > oldwidth);
        
    }
    
    public ScreenElement getDummyScreenElement() {
        ScreenElement screenElement = new ScreenElement(mock(Drawable.class));

        int left = 30, top = 35;
        screenElement.setPosition(left, top);
        int width = 20, height = 24;
        screenElement.setSize(width, height);

        return screenElement;
    }

}
