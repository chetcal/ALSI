package ALSI;

import javafx.scene.shape.Circle;

public class Target {
    private int x;
    private int y;
    private Circle circle;

    public Target(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.setCircle(new Circle((double)this.getX(), (double)this.getY(), 4.0D));
    }

    public Target(Circle circle) {
        this.setCircle(circle);
        this.setX((int)this.getCircle().getCenterX());
        this.setY((int)this.getCircle().getCenterY());
    }

    public int getX() {
        return this.x;
    }

    public void setX(int xIn) {
        this.x = xIn;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int yIn) {
        this.y = yIn;
    }

    public Circle getCircle() {
        return this.circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }
}
