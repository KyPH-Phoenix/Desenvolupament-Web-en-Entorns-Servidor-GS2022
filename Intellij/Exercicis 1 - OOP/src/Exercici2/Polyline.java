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

        for (Point point : this.points) {
            result += "(x" + point.getX();
            result += ",y" + point.getY() + ")";
        }

        result+= "}\nTotal length = " + this.getLength();

        return result;
    }

    public double getLength() {
        double totalLength = 0;

        for (int i = 0; i < this.points.size() - 1; i++) {
                Point point1 = this.points.get(i);
                Point point2 = this.points.get(i + 1);

                double cat1 = Math.abs(point2.getX() - point1.getX());
                double cat2 = Math.abs(point2.getY() - point1.getY());
                double hipo = Math.sqrt((cat1 * cat1) + (cat2 * cat2));

                totalLength += hipo;
        }

        return totalLength;
    }
}
