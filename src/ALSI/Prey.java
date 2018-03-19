package ALSI;

import java.util.Iterator;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Prey extends Animal {
    private Random rand = new Random();
    private double Speed = 0.5D;
    private int Size = 2;
    private int TurnAngle = 30;
    private int Strength = 3;
    private int runCooldown = 0;
    private Color bodyColour = Color.rgb(0, 0, 0);
    private Color smellColour = Color.rgb(100, 100, 100, 0.3D);
    private Color sightColour = Color.rgb(200, 200, 200, 0.3D);
    private int breedAge = 1;

    public Prey(int x, int y, int id, int bornDay, int bornYear, World world) {
        super("Prey", 'A', id, bornDay, bornYear, 10, x, y, world);
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

    public Prey(int x, int y, char sex, String name, double speed, int strength, int smell, int sight, int size, int MaxInv, int id, int bornDay, int bornYear, World world) {
        super("Prey", 'A', id, bornDay, bornYear, 10, x, y, sex, speed, strength, smell, MaxInv, sight, size, Color.rgb(0, 0, 0), world);
    }

    @Override
    public void createBaby(Animal parent) {
        for(int i = 0; i < 1; ++i) {
            super.createBaby(parent);
        }

    }
    @Override
    public void target() {
        super.target();
        int failCount = 0;
        double angle;
        int tX;
        int tY;
        for (Animal animal : getWorldRef().getAnimalList()) {
            if (getID() != animal.getID() && runCooldown < 1) {
                if (getSpecies() != animal.getSpecies()) {
                    if (Collision.overlapsEfficient(getSightCircle(), animal.getMarker())) {
                        if (Collision.overlapsAccurate(getSightCircle(), animal.getMarker())) {
                            this.setSpeed(getSpeed() * 1.5);
                            double x = animal.getMarker().getCenterX() + getLocalTarg().getCircle().getTranslateX();
                            double y = animal.getMarker().getCenterY() + getLocalTarg().getCircle().getTranslateY();
                            angle = getAngTo(x, y) + 180;
                            do {
                                tX = (int) (getMarker().getCenterX() + getMarker().getTranslateX() + getSightRange() * Math.cos(angle));
                                tY = (int) (getMarker().getCenterY() + getMarker().getTranslateY() + getSightRange() * Math.sin(angle));
                                ++failCount;
                                if (failCount > 2) {
                                    angle += (this.rand.nextInt(90) - 45);
                                    runCooldown = 500;
                                    if (failCount > 3) {
                                        return;
                                    }
                                }
                            }while(!isValid(tX, tY));
                            setPrevAngle((int)angle);
                            setLocalTarg(new Target(tX, tY));
                        }
                    }
                }
            }else setSpeed(0.5);
        }
    }

    @Override
    public void checkBase(){
        for(int i = 0; i < 1; ++i) {
            if (this.getWorldRef().getShelterList().get(i).getType().equals("PreyBase") && Collision.overlapsEfficient(this.getSmellCircle(), this.getWorldRef().getShelterList().get(i).getMarker()) && Collision.overlapsEfficient(this.getSmellCircle(), this.getWorldRef().getShelterList().get(i).getMarker())) {
                this.setBase(new Target(this.getWorldRef().getShelterList().get(i).getX(), this.getWorldRef().getShelterList().get(i).getY()), this.getWorldRef().getShelterList().get(i).getID());
                break;
            }
        }

    }
}
