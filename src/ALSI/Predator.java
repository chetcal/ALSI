package ALSI;

import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Predator extends Animal {
    private Random rand = new Random();
    private double Speed = 0.6D;
    private int Size = 5;
    private int TurnAngle = 30;
    private int Strength = 3;
    private Color bodyColour = Color.rgb(0, 200, 50);
    private Color smellColour = Color.rgb(0, 100, 100, 0.3D);
    private Color sightColour = Color.rgb(150, 255, 255, 0.3D);
    private int breedAge = 1;

    public Predator(int x, int y, int id, int bornDay, int bornYear, World world) {
        super("Predator", 'B', id, bornDay, bornYear, 10, x, y, world);
        this.setName();
        this.setBreedAge(this.breedAge);
        this.setSightRange(60);
        this.setSightCircle(new Circle((double)x, (double)y, (double)this.getSightRange()));
        this.getSightCircle().setFill(this.sightColour);
        this.setSmellRange(40);
        this.setSmellCircle(new Circle((double)x, (double)y, (double)this.getSmellRange()));
        this.getSmellCircle().setFill(this.smellColour);
        this.setSize((float)this.Size);
        this.setMarker(new Circle((double)x, (double)y, (double)this.getSize()));
        this.getMarker().setFill(this.bodyColour);
        this.setSpeed(this.Speed);
        this.setTurnAng(this.TurnAngle);
        this.setStrength(this.Strength);
        this.setMaxInv(4);
    }

    public Predator(int x, int y, char sex, String name, double speed, int strength, int smell, int sight, int size, int MaxInv, int id, int bornDay, int bornYear, World world) {
        super("Predator", 'B', id, bornDay, bornYear, 10, x, y, sex, speed, strength, smell, MaxInv, sight, size, Color.rgb(0, 200, 100), world);
    }

    @Override
    public void createBaby(Animal parent) {
        int predCount = 0;

        for(int i = 0; i < this.getWorldRef().getAnimalList().size(); ++i) {
            if (this.getWorldRef().getAnimalList().get(i).getSpecies() == "Predator") {
                ++predCount;
            }
        }

        if (predCount < 10) {
            super.createBaby(parent);
        }

        this.setBreedTimer(6000);
    }

    @Override
    public void checkBase() {
    }
}
