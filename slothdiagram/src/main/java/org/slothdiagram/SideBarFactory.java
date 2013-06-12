package org.slothdiagram;

import java.util.ArrayList;
import java.util.List;

public class SideBarFactory {
	
	private static SideBarFactory currentInstance;
	private static List<DrawableElement> sidebarElements;
	
	private SideBarFactory() {
		sidebarElements = new ArrayList<DrawableElement>();
	}
	
	public static SideBarFactory getInstance(){
		if(currentInstance == null)
			currentInstance = new SideBarFactory();
		return currentInstance;
	}
	
	public static List<DrawableElement> createSideBarElements()
	{
		return sidebarElements;
	}

//	public static void addSidebarElement(DrawableElement sbElement){
//		sidebarElements.add(sbElement);
//	}
}
