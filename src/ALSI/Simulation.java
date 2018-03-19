package ALSI;

import javafx.scene.Group;
import javafx.stage.Stage;

public class Simulation {
    private Group root;
    private World world;
    private boolean pause = false;

    public Simulation(Stage primarystage, Group root) {
        this.setRoot(root);
        this.createWorld();
    }

    public Group getRoot() {
        return this.root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }

    public boolean getPause() {
        return this.pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void togglePause() {
        this.pause = !this.pause;
    }

    public World getWorld() {
        return this.world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void createWorld() {
        this.getRoot().getChildren().clear();
        this.setWorld(new World(this.getRoot()));
    }
}
