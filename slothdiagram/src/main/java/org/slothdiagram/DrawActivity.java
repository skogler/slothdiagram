package org.slothdiagram;

import java.util.ArrayList;
import java.util.List;

import org.slothdiagram.points.AbsolutePoint;
import org.slothdiagram.points.ConnectionPoint;
import org.slothdiagram.points.RelativePoint;
import org.slothdiagram.ui.GraphicsView;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
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
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_draw);
		
		sideBarFactory = SideBarFactory.getInstance();
		elements = SideBarFactory.createSideBarElements();

		graphicsView = (GraphicsView) findViewById(R.id.graphicsView);
		sidebar = (GridView) findViewById(R.id.sidebar);

		
		// TODO: sidebar adapter
		
		setUpTestScene();
	}

	public void setUpTestScene() {
		ScreenElement screenElement = new ScreenElement(new BitmapDrawable(
				getResources(), BitmapFactory.decodeResource(getResources(),
						R.raw.test)));

		// Sidebar test start

		elements.add(screenElement);
		elements.add(screenElement);
		elements.add(screenElement);
		elements.add(screenElement);
		elements.add(screenElement);
		elements.add(screenElement);
		elements.add(screenElement);

		SidebarElementAdapter adapter = new SidebarElementAdapter(this,
				elements);

		sidebar.setAdapter(adapter);

		// Sidebar test end

		screenElement.setPosition(100, 100);
		screenElement.setSize(50, 50);
		screenElement.scale(2.0f);
		screenElement.setPosition(10, 50);
		ScreenText stext = new ScreenText("ll", graphicsView);
		screenElement.addText(stext);
		stext.setPosition(new RelativePoint(screenElement, new PointF(0.5f,
				0.5f)));

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
		line.addPoint(new AbsolutePoint(125, 325));
		line.addPoint(new ConnectionPoint(screenElement2, 0));

		graphicsView.addElement(screenElement);
		graphicsView.addElement(screenElement2);
		graphicsView.addElement(line);
		stext.setText("Hello scale!");
	}
}
