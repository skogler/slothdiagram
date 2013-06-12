package org.slothdiagram;

import static org.junit.Assert.assertNotSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import android.app.Activity;
import android.widget.RelativeLayout;

@RunWith(RobolectricTestRunner.class)
public class ScreenTextTest {

    @Test
    public void testInitialSize() {
        TextElement text = new TextElement("asdf", new RelativeLayout(new Activity()), TestUtils.getInstance()
                .getDummyScreenElement());
        text.updateBoundingBox();
        assertNotSame(0, text.getBoundingBox().right);
        assertNotSame(0, text.getBoundingBox().left);
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
}
