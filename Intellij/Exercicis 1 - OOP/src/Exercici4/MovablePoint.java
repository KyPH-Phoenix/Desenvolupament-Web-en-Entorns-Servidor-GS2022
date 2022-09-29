package Exercici4;

public class MovablePoint implements Movable {
    int x;
    int y;
    int xSpeed;
    int ySpeed;

    public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = Math.max(xSpeed, 1);
        this.ySpeed = Math.max(ySpeed, 1);
    }

    @Override
    public void moveUp() {
        this.y += this.ySpeed;
    }

    @Override
    public void moveDown() {
        this.y -= this.ySpeed;
    }

    @Override
    public void moveLeft() {
        this.x += this.xSpeed;
    }

    @Override
    public void moveRight() {
        this.x -= this.xSpeed;
    }

    @Override
    public String toString() {
        String result;

        result = String.format("Point located at x%d, y%d.\nxSpeed %d, ySpeed %d.", x, y, xSpeed, ySpeed);

        return result;
    }
}
