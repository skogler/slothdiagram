package org.slothdiagram;

import java.util.LinkedList;
import java.util.List;

public class Line {
    
    private List<ScreenPoint> points = new LinkedList<ScreenPoint>();
    
    public void addPoint(ScreenPoint point) {
        points.add(point);
    }

    public List<ScreenPoint> getPoints() {
        return points;
    }

}
