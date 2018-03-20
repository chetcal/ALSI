package ALSI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class World {
    private Random rand = new Random();
    private int year;
    private int day;
    private int dayLength;
    private int dayLengthCounter;
    private int trackAnimalID = 0;
    public static int trackFoodID = 0;
    private int shelterID = 0;
    private ArrayList<Animal> animalList = new ArrayList<>();
    private ArrayList<Food> foodList = new ArrayList<>();
    private ArrayList<Shelter> shelterList = new ArrayList<>();
    private ArrayList<ArrayList<Boolean>> eatList = new ArrayList<>();
    private ArrayList<ArrayList<Boolean>> huntList = new ArrayList<>();
    private Group animalGroup = new Group();
    private Group animalSmellGroup = new Group();
    private Group animalSightGroup = new Group();
    private Group animalTargetGroup = new Group();
    private Group animalHomeLocationGroup = new Group();
    private Group shelterGroup = new Group();
    private Group foodGroup = new Group();

    public World(Group root) {
        this.trackAnimalID = 0;
        trackFoodID = 0;
        setDay(0);
        setYear(0);
        setDayLength(10);
        setDayLengthCounter(0);

        huntList.add(new ArrayList<>());
        huntList.get(0).add(false);
        huntList.get(0).add(true);
        huntList.add(new ArrayList<>());
        huntList.get(1).add(false);
        huntList.get(1).add(false);


        eatList.add(new ArrayList<>());
        eatList.get(0).add(false);
        eatList.get(0).add(true);

        eatList.add(new ArrayList<>());
        eatList.get(1).add(false);
        eatList.get(1).add(false);

        root.getChildren().add(getShelterGroup());
        root.getChildren().add(getFoodGroup());
        root.getChildren().add(getAnimalSmellGroup());
        root.getChildren().add(getAnimalSightGroup());
        root.getChildren().add(getAnimalGroup());
        root.getChildren().add(getAnimalTargetGroup());
        root.getChildren().add(getAnimalHomeLocationGroup());

        addShelter("PreyBase");
        int i;
        for(i = 0; i < 40; ++i) {
            addRandomAnimal("Prey");
        }
        for(i = 0; i < 6; ++i) {
            addRandomAnimal("Predator");
        }

    }

    public void addRandomAnimal(String type) {
        int x;
        int y;
        if (type.equals("Prey")) {
            Prey a;
            do {
                x = rand.nextInt(Main.SIZE_X);
                y = rand.nextInt(Main.SIZE_Y);
                a = new Prey(x, y, trackAnimalID,getDay(), getYear(), this);
            } while(overlaps(a.getMarker()));

            a.addSelfToLists();
        } else if (type.equals("Predator")) {
            Predator a;
            do {
                x = rand.nextInt(Main.SIZE_X);
                y = rand.nextInt(Main.SIZE_Y);
                a = new Predator(x, y, trackAnimalID, getDay(), getYear(), this);
            } while(this.overlaps(a.getMarker()));

            a.addSelfToLists();
        }

        ++this.trackAnimalID;
    }

    public void addAnimal(String type, int x, int y, char gender, String name, double speed, int strength, int smell, int sight, int size, int maxInv) {
        Animal a = null;
        if (type.equals("Prey")) {
            a = new Prey(x, y, trackAnimalID, getDay(), getYear(), this);
        } else if (type.equals("Predator")) {
            a = new Predator(x, y, gender, name, speed, strength, smell, sight, size, maxInv,trackAnimalID, getDay(), getYear(), this);
        }

        ++this.trackAnimalID;
        a.addSelfToLists();
    }

    public ArrayList<ArrayList<Boolean>> getEatList() {
        return this.eatList;
    }

    public void setEatList(ArrayList<ArrayList<Boolean>> eatList) {
        this.eatList = eatList;
    }

    public ArrayList<ArrayList<Boolean>> getHuntList() {
        return this.huntList;
    }

    public void setHuntList(ArrayList<ArrayList<Boolean>> huntList) {
        this.huntList = huntList;
    }

    public void addFood(String type, int x, int y, int size) {
        Food f;
        if (type.equals("Prey")) {
            f = new Food(type, x, y, trackFoodID, size, Color.rgb(50, 50, 50));
        } else if (type.equals("Predator")) {
            f = new Food(type, x, y, trackFoodID, size, Color.rgb(0, 150, 40));
        } else {
            f = new Food(type, x, y, trackFoodID, size, Color.rgb(0, 0, 0));
        }

        this.foodList.add(f);
        this.getFoodGroup().getChildren().add(f.getMarker());
        ++trackFoodID;
    }

    public void addShelter(String type) {
        Shelter s;
        do {
            int x = Main.SIZE_X / 2;
            int y = Main.SIZE_Y / 2;
            if (type.equals("PreyBase")) {
                s = new Shelter(type, x, y, shelterID, Color.rgb(220, 200, 160));
            } else {
                if (!type.equals("PredatorBase")) {
                    System.out.println("Error creating shelter");
                    return;
                }

                s = new Shelter(type, 10, 10, shelterID, Color.rgb(100, 110, 120));
            }
        } while(this.overlaps(s.getMarker()));

        ++this.shelterID;
        this.shelterList.add(s);
        this.getShelterGroup().getChildren().add(s.getMarker());
    }

    public boolean overlaps(Circle c1) {
        for(Animal animal : animalList){
            if (Collision.overlapsEfficient(c1, animal.getMarker())){
                if (Collision.overlapsAccurate(c1, animal.getMarker())) {
                    // Return  true if a collision was recorded
                    return true;
                }
            }
        }
        for(Food food : foodList){
            if (Collision.overlapsEfficient(c1, food.getMarker())){
                if (Collision.overlapsAccurate(c1, food.getMarker())) {
                    // Return  true if a collision was recorded
                    return true;
                }
            }
        }
        return false;
    }

    public void killAnimal(int i) {
        this.addFood(this.getAnimalList().get(i).getSpecies(), (int)(animalList.get(i).getMarker().getCenterX() + (animalList.get(i)).getMarker().getTranslateX()), (int)((animalList.get(i)).getMarker().getCenterY() + (animalList.get(i)).getMarker().getTranslateY()), (int)(animalList.get(i)).getMarker().getRadius());
        this.getAnimalGroup().getChildren().remove(i);
        this.getAnimalSmellGroup().getChildren().remove(i);
        this.getAnimalSightGroup().getChildren().remove(i);
        this.getAnimalTargetGroup().getChildren().remove(i);
        this.animalList.remove(i);
    }

    public void update() {
        updateClock();
        ageAnimals();
        int i;
        for(i = 0; i < animalList.size(); ++i) {
            this.animalList.get(i).update();

            for(int j = 0; j < animalList.size(); ++j) {
                if (animalList.get(j).getHealth() <= 0) {
                    killAnimal(j);
                    --j;
                    --i;
                }
            }
        }

        for(i = 0; i < this.shelterList.size(); ++i) {
          shelterList.get(i).update();
        }

    }

    public void updateClock() {
        this.setDayLengthCounter(this.getDayLengthCounter() + 1);
        if (this.getDayLengthCounter() >= this.getDayLength()) {
            this.setDayLengthCounter(0);
            this.setDay(this.getDay() + 1);
            if (this.getDay() > 365) {
                this.setDay(0);
                this.setYear(this.getYear() + 1);
            }
        }

    }

    public void ageAnimals() {
        Iterator<Animal> var1 = this.getAnimalList().iterator();

        while(var1.hasNext()) {
            Animal animal = var1.next();
            animal.solveAge(this.getYear(), this.getDay());
        }

    }

    public ArrayList<Animal> getAnimalList() {
        return this.animalList;
    }

    public ArrayList<Food> getFoodList() {
        return this.foodList;
    }

    public ArrayList<Shelter> getShelterList() {
        return this.shelterList;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDayLength() {
        return this.dayLength;
    }

    public void setDayLength(int dayLength) {
        this.dayLength = dayLength;
    }

    public int getDayLengthCounter() {
        return this.dayLengthCounter;
    }

    public void setDayLengthCounter(int dayLengthCounter) {
        this.dayLengthCounter = dayLengthCounter;
    }

    public String getDateString() {
        return "Y" + this.getYear() + " D" + this.getDay();
    }

    public Group getAnimalGroup() {
        return this.animalGroup;
    }

    public Group getAnimalSmellGroup() {
        return this.animalSmellGroup;
    }

    public Group getAnimalSightGroup() {
        return this.animalSightGroup;
    }

    public Group getAnimalTargetGroup() {
        return this.animalTargetGroup;
    }

    public Group getAnimalHomeLocationGroup() {
        return this.animalHomeLocationGroup;
    }

    public Group getShelterGroup() {
        return this.shelterGroup;
    }

    public Group getFoodGroup() {
        return this.foodGroup;
    }
}
