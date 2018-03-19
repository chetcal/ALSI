package ALSI;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    public static int SIZE_X = 1900;
    public static int SIZE_Y = 960;

    public Main() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primarystage) {
        Group root = new Group();
        Scene scene = new Scene(root, (double)SIZE_X, (double)SIZE_Y, Color.rgb(255, 255, 255));
        Simulation simulation = new Simulation(primarystage, root);
        simulation.togglePause();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.SPACE){
                    simulation.togglePause();
                }else if(event.getCode() == KeyCode.V){
                    simulation.getWorld().getAnimalSmellGroup().setVisible(! simulation.getWorld().getAnimalSmellGroup().isVisible());
                    simulation.getWorld().getAnimalSightGroup().setVisible(! simulation.getWorld().getAnimalSightGroup().isVisible());
                    simulation.getWorld().getAnimalTargetGroup().setVisible(! simulation.getWorld().getAnimalTargetGroup().isVisible());
                }

            }
        });

        KeyFrame frame = new KeyFrame(Duration.millis(16), new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if (simulation.getWorld() != null) {
                    // if the menu is paused run the world
                    if (!simulation.getPause()) {
                        simulation.getWorld().update();
                    }
                }
            }
        });

        ((TimelineBuilder)TimelineBuilder.create().cycleCount(-1)).keyFrames(new KeyFrame[]{frame}).build().play();
        primarystage.setScene(scene);
        primarystage.show();
    }
}
