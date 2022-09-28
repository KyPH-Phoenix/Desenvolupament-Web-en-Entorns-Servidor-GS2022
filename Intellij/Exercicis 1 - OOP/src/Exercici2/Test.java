package Exercici2;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Polyline polyline1 = new Polyline();

        polyline1.appendPoint(5, 4);
        polyline1.appendPoint(new Point(1, 4));
        polyline1.appendPoint(3, 8);
        polyline1.appendPoint(-4, 9);

        System.out.println(polyline1);

        List<Point> pointList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int x = (int)(Math.random() * 30) - 15;
            int y = (int)(Math.random() * 30) - 15;
            pointList.add(new Point(x, y));
        }

        Polyline polyline2 = new Polyline(pointList);
        System.out.println(polyline2);

        System.out.println(new Polyline());
    }
}
