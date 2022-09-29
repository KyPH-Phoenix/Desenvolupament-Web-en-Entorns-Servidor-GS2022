package Exercici3;

public class Test {
    public static void main(String[] args) {
        ResizableCircle resizableCircle = new ResizableCircle(78);
        System.out.println(resizableCircle);
        System.out.println(resizableCircle.getArea());

        resizableCircle.resize(10);
        System.out.println(resizableCircle);
        System.out.println(resizableCircle.getArea());

        resizableCircle.resize(500);
        System.out.println(resizableCircle);
        System.out.println(resizableCircle.getArea());
    }
}
