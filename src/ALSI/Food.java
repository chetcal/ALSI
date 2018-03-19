package ALSI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Food {
    private int x;
    private int y;
    private int id;
    private int size;
    private String type;
    private Circle marker;
    private char mark;

    public Food(String type, int xIn, int yIn, int id, int size, Color colour) {
        this.setX(xIn);
        this.setY(yIn);
        this.setID(id);
        this.setSize(size);
        this.setType(type);
        this.setMark();
        this.setMarker(new Circle((double)this.getX(), (double)this.getY(), (double)size));
        this.getMarker().setFill(colour);
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

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Circle getMarker() {
        return this.marker;
    }

    public void setMarker(Circle marker) {
        this.marker = marker;
    }

    public char getMark() {
        return this.mark;
    }

    public void setMark() {
        if (this.type == "Prey") {
            this.mark = 'A';
        } else if (this.type == "Predator") {
            this.mark = 'B';
        }

    }
}
