package Exercici3;

public class ResizableCircle extends Circle implements Resizable {
    public ResizableCircle(double radius) {
        super(radius);
    }

    @Override
    public String toString() {
        return String.format("Resizable Circle[%s]", super.toString());
    }

    @Override
    public void resize(int percent) {
        this.radius *= percent / 100.0;
    }
}
