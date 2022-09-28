package Exercici2;

import java.util.ArrayList;
import java.util.List;

public class Polyline {
    private List<Point> points = new ArrayList<>();

    public Polyline() {
    }

    public Polyline(List<Point> points) {
        this.points = points;
    }

    public void appendPoint(int x, int y) {
        points.add(new Point(x, y));
    }

    public void appendPoint(Point point) {
        points.add(point);
    }

    @Override
    public String toString() {
        if (this.points.size() == 0) return "Empty polyline";

        String result = "{";

        for (Point point:this.points) {
            result += "(x" + point.getX();
            result += ",y" + point.getY() + ")";
        }

        result+= "}\n";

        return result;
    }

    public double getLength() {
        return this.points.size();
    }
}
