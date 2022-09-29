package Exercici4;

public class Test {
    public static void main(String[] args) {
        MovablePoint movablePoint = new MovablePoint(0,0,3,0);
        System.out.println(movablePoint);
        movablePoint.moveRight();
        movablePoint.moveUp();
        System.out.println(movablePoint);
        System.out.println();

        MovableCircle movableCircle = new MovableCircle(5, 0, 0, 8,2);
        System.out.println(movableCircle);

        movableCircle.moveDown();
        movableCircle.moveLeft();

        System.out.println(movableCircle);
    }
}
