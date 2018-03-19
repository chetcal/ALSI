package ALSI;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Shelter {
    private int x;
    private int y;
    private int ID;
    private String type;
    private Circle marker;
    protected ArrayList<Animal> sheltered = new ArrayList();

    public Shelter(String type, int x, int y, int ID, Color colour) {
        this.setX(x);
        this.setY(y);
        this.setType(type);
        this.setID(ID);
        this.setMarker(new Circle((double)this.getX(), (double)this.getY(), 25.0D));
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
        return this.ID;
    }

    public void setID(int id) {
        this.ID = id;
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

    public void update() {
        for(int i = 0; i < this.sheltered.size(); ++i) {
            this.sheltered.get(i).setHomeRest(this.sheltered.get(i).getHomeRest() - 1);
            if (this.sheltered.get(i).getHomeRest() < 1) {
                this.sheltered.get(i).exitBase();
                this.sheltered.remove(i);
            }
        }

    }
}
