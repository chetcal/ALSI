package ALSI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Animal {
    private Random randAng;
    private float size;
    private int id;
    private int x;
    private int y;
    private int smellrange;
    private int sightrange;
    private int prevAngle;
    private int turnAngle;
    private int PDistance;
    private int HomeWait;
    private int homeID;
    private int breedTimer;
    private int homeTimer;
    private int FollowMainTimer;
    private int targetID;
    private int ageRecord;
    private int ageY;
    private int ageD;
    private int bornDay;
    private int bornYear;
    private int breedAge;
    private int strength;
    private int Inventory = 0;
    private int MaxInventory;
    private int health;
    private boolean localTargetBool;
    private boolean mainTargetBool;
    private boolean targetFoodBool;
    private boolean targetHomeBool;
    private boolean breedingBool;
    private boolean BeingHunted;
    private boolean TargAnimalBool;
    private boolean AtBase;
    private boolean Valid;
    private double speed;
    private double dx;
    private double dy;
    private Target HunterTarg;
    private Target LocalTarg;
    private Target HomeTarg;
    private Target MainTarg;
    private World worldref;
    private Circle marker;
    private Circle smellCircle;
    private Circle sightCircle;
    private Text text;
    private Rectangle targLocate;
    private Rectangle homeLocate;
    private String species;
    private String name;
    private char mark;
    private char sex;

    public Animal(String speciesIn, char markIn, int IDIn, int dayBorn, int yearBorn, int health, int xIn, int yIn, World world) {
        setSpecies(speciesIn);
        setMark(markIn);
        setID(IDIn);
        setX(xIn);
        setY(yIn);
        setBeingHunted(false);
        setHealth(health);
        setTurnAng(30);
        setWorldRef(world);
        randAng = new Random();
        setPrevAngle(randAng.nextInt(360));
        randomSex();
        Rectangle t = new Rectangle(0.0D, 0.0D, 5.0D, 5.0D);
        t.setFill(Color.rgb(0, 255, 0));
        setTargLocation(t);
        Rectangle h = new Rectangle(0.0D, 0.0D, 5.0D, 5.0D);
        h.setFill(Color.rgb(0, 0, 255));
        setHomeLocation(h);
        setAtBase(false);
        setHomeRest(0);
        setYearBorn(yearBorn);
        setDayBorn(dayBorn);
        setPDistance(40);
    }

    public Animal(String speciesIn, char markIn, int IDIn, int dayBorn, int yearBorn, int healthIn, int xIn, int yIn, char sex, double speed, int strength, int smell, int MaxInv, int sight, int size, Color colour, World world) {
        setSpecies(speciesIn);
        setMark(markIn);
        setDayBorn(dayBorn);
        setYearBorn(yearBorn);
        setID(IDIn);
        setX(xIn);
        setY(yIn);
        setMaxInv(MaxInv);
        setHealth(healthIn);
        setWorldRef(world);
        setSex(sex);
        setName();
        setSpeed(speed);
        setStrength(strength);
        setSmellRange(smell);
        setSightRange(sight);
        setSize((float)size);
        setSmellCircle(new Circle((double)x, (double)y, (double)getSmellRange()));
        getSmellCircle().setFill(colour);
        getSmellCircle().setOpacity(0.4D);
        setPDistance(40);
        setSightCircle(new Circle((double)x, (double)y, (double)getSightRange()));
        getSightCircle().setFill(Color.rgb(0, 150, 150));
        getSightCircle().setOpacity(0.4D);
        setMarker(new Circle((double)x, (double)y, (double)getSize()));
        getMarker().setFill(colour);
        setTurnAng(30);
        Rectangle t = new Rectangle(0.0D, 0.0D, 5.0D, 5.0D);
        t.setFill(Color.rgb(0, 255, 0));
        setTargLocation(t);
        Rectangle h = new Rectangle(0.0D, 0.0D, 5.0D, 5.0D);
        h.setFill(Color.rgb(0, 0, 255));
        setHomeLocation(h);
        setAtBase(false);
        setHomeRest(0);
        setPrevAngle(0);
    }

    public void createBaby(Animal animal) {
        int x = (int)(getMarker().getCenterX() + getMarker().getTranslateX());
        int y = (int)(getMarker().getCenterY() + getMarker().getTranslateY());
        char gender;
        if ((new Random()).nextInt(2) == 1) {
            gender = 'M';
        } else {
            gender = 'F';
        }

        getWorldRef().addAnimal(getSpecies(), x, y, gender, name, getSpeed(), getStrength(), getSmellRange(), getSightRange(), (int)getSize(), getMaxInventory());
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        if (getSex() == 'M') {
            name = getSpecies() + "_M";
        } else {
            name = getSpecies() + "_F";
        }

    }

    public char getMark() {
        return mark;
    }

    public void setMark(char mark) {
        this.mark = mark;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getHomeID() {
        return homeID;
    }

    public void setHomeID(int homeID) {
        this.homeID = homeID;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getSmellRange() {
        return smellrange;
    }

    public void setSmellRange(int smellrange) {
        this.smellrange = smellrange;
    }

    public int getSightRange() {
        return sightrange;
    }

    public void setSightRange(int sightrange) {
        this.sightrange = sightrange;
    }

    public boolean isMainTargetBool() {
        return mainTargetBool;
    }

    public void setMainTargetBool(boolean hasTarget) {
        this.mainTargetBool = hasTarget;
    }

    public boolean isBeingHunted() {
        return BeingHunted;
    }

    public void setBeingHunted(Boolean hunted) {
        this.BeingHunted = hunted;
    }

    public Circle getMarker() {
        return this.marker;
    }

    public void setMarker(Circle marker) {
        this.marker = marker;
    }

    public int getInventory() {
        return this.Inventory;
    }

    public void setInventory(int inventory) {
        this.Inventory = inventory;
    }

    public void checkHunger() {
        if (this.Inventory > 0) {
            this.eatFood();
        }

    }

    public void eatFood() {
        --this.Inventory;
    }

    public int getAgeDay() {
        return this.ageD;
    }

    public void setAgeDay(int DayAge) {
        this.ageD = DayAge;
    }

    public int getAgeYear() {
        return this.ageY;
    }

    public void setAgeYear(int ageYear) {
        this.ageY = ageYear;
    }

    public void solveAge(int year, int day) {
        int ageDay = day - this.getDayBorn();
        int ageYear = year - this.getYearBorn();
        if (ageDay < 0) {
            ageDay += 365;
            --ageYear;
        }

        setAgeYear(ageYear);
        setAgeDay(ageDay);
    }

    public int getAgeRecord() {
        return ageRecord;
    }

    public void setAgeRecord(int ageRec) {
        this.ageRecord = ageRec;
    }

    public void ageAnimal() {
        if (getAgeRecord() < getAgeYear()) {
            setAgeRecord(getAgeYear());
            if (getAgeYear() >= getBreedAge()) {
                setBreed(true);
            }
        }

    }

    public Rectangle getTargLocation() {
        return targLocate;
    }

    public void setTargLocation(Rectangle targ) {
        this.targLocate = targ;
    }

    public Rectangle getHomeLocation() {
        return homeLocate;
    }

    public void setHomeLocation(Rectangle home) {
        this.homeLocate = home;
    }

    public void setBase(Target home, int ID) {
        setHomeTarg(home);
        setHomeID(ID);
    }

    public int getHomeRest() {
        return HomeWait;
    }

    public void setHomeRest(int wait) {
        this.HomeWait = wait;
    }

    public World getWorldRef() {
        return worldref;
    }

    public void setWorldRef(World world) {
        this.worldref = world;
    }

    public int getX() {
        return x;
    }

    public void setX(int xIn) {
        this.x = xIn;
    }

    public int getY() {
        return y;
    }

    public void setY(int yIn) {
        this.y = yIn;
    }

    public int getMaxInventory() {
        return MaxInventory;
    }

    public void setMaxInv(int MaxInv) {
        this.MaxInventory = MaxInv;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public void removeLocalTarget() {
        setLocalTargetBool(false);
        setTargetingAnimal(false);
        setTargetingFood(false);
        targetID = -1;
        setDx(0);
        setDy(0);
    }

    public boolean getAtBase() {
        return this.AtBase;
    }

    public void setAtBase(boolean AtHome) {
        this.AtBase = AtHome;
    }

    public double getDx() {
        return this.dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return this.dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public int getPDistance() {
        return this.PDistance;
    }

    public void setPDistance(int pDist) {
        this.PDistance = pDist;
    }

    public int getTurnAng() {
        return this.turnAngle;
    }

    public int getPrevAngle() {
        return this.prevAngle;
    }

    public int getTargetID() {
        return this.targetID;
    }

    public void setTargetID(int ID) {
        this.targetID = ID;
    }

    public void setPrevAngle(int prevAngle) {
        this.prevAngle = prevAngle;
    }

    public void setTurnAng(int turnAng) {
        this.turnAngle = turnAng;
    }

    public boolean getTargetingAnimal() {
        return this.TargAnimalBool;
    }

    public void setTargetingAnimal(boolean targeting) {
        this.TargAnimalBool = targeting;
    }

    public boolean getLocalTargetBool() {
        return this.localTargetBool;
    }

    public void setLocalTargetBool(boolean targetBool) {
        this.localTargetBool = targetBool;
    }

    public boolean getTargetingFood() {
        return this.targetFoodBool;
    }

    public void setTargetingFood(boolean targetBool) {
        this.targetFoodBool = targetBool;
    }

    public boolean getTargetHomeBool() {
        return this.targetHomeBool;
    }

    public void setTargetHomeBool(boolean HomeBool) {
        this.targetHomeBool = HomeBool;
    }

    public Circle getSmellCircle() {
        return this.smellCircle;
    }

    public void setSmellCircle(Circle smellCircle) {
        this.smellCircle = smellCircle;
    }

    public Circle getSightCircle() {
        return this.sightCircle;
    }

    public void setSightCircle(Circle sightCircle) {
        this.sightCircle = sightCircle;
    }

    public int getDayBorn() {
        return this.bornDay;
    }

    public void setDayBorn(int dayBorn) {
        this.bornDay = dayBorn;
    }

    public int getYearBorn() {
        return this.bornYear;
    }

    public void setYearBorn(int YearBorn) {
        this.bornYear = YearBorn;
    }

    public boolean getBreed() {
        return this.breedingBool;
    }

    public void setBreed(boolean shouldBreed) {
        this.breedingBool = shouldBreed;
    }

    public int getBreedAge() {
        return this.breedAge;
    }

    public void setBreedAge(int breedAge) {
        this.breedAge = breedAge;
    }

    public int getBreedTimer() {
        return this.breedTimer;
    }

    public void setBreedTimer(int breedTimer) {
        this.breedTimer = breedTimer;
    }

    public int getHomeTimer() {
        return this.homeTimer;
    }

    public void setHomeTimer(int timer) {
        this.homeTimer = timer;
    }

    public void randomSex() {
        Random RandInt = new Random();
        switch(RandInt.nextInt(2)) {
            case 1:
                this.setSex('M');
                break;
            case 2:
                this.setSex('F');
        }

    }

    public void TimerFollowMain() {
        this.setFollowMainTimer(this.getFollowMainTimer() - 1);
        if (this.getFollowMainTimer() < 0) {
            this.setFollowMainTimer(0);
        }

    }

    public int getFollowMainTimer() {
        return this.FollowMainTimer;
    }

    public void setFollowMainTimer(int followMainTimer) {
        this.FollowMainTimer = followMainTimer;
    }

    public void setVisibility(boolean visible) {
        int index = 0;

        for(int i = 0; i < this.getWorldRef().getAnimalList().size(); ++i) {
            if (this.getID() == (this.getWorldRef().getAnimalList().get(i)).getID()) {
                index = i;
                break;
            }
        }

        this.getWorldRef().getAnimalGroup().getChildren().get(index).setVisible(visible);
        this.getWorldRef().getAnimalHomeLocationGroup().getChildren().get(index).setVisible(visible);
        this.getWorldRef().getAnimalSmellGroup().getChildren().get(index).setVisible(visible);
       this.getWorldRef().getAnimalSightGroup().getChildren().get(index).setVisible(visible);
        this.getWorldRef().getAnimalTargetGroup().getChildren().get(index).setVisible(visible);
    }

    public void breedtimerCD() {
        if (this.getBreedTimer() <= 0) {
            this.setBreedTimer(0);
        } else {
            this.setBreedTimer(this.getBreedTimer() - 1);
        }

    }

    public Target getMainTarg() {
        return this.MainTarg;
    }

    public void setMainTarg(Target target) {
        this.MainTarg = target;
        this.setMainTargetBool(true);
    }

    public Target getLocalTarg() {
        return this.LocalTarg;
    }

    public void setLocalTarg(Target target) {
        this.LocalTarg = target;
        this.setLocalTargetBool(true);
    }

    public void setLocalHuntTarget(Circle circle) {
        this.setLocalTarg(new Target(circle));
    }

    public Target getHunterTarg() {
        return this.HunterTarg;
    }

    public void setHunterTarg(Target target) {
        this.HunterTarg = target;
    }

    public Target getHomeTarg() {
        return this.HomeTarg;
    }

    public void setHomeTarg(Target target) {
        this.HomeTarg = target;
    }

    public boolean isValid(int x, int y) {
        return x < Main.SIZE_X && x >= 0 && y < Main.SIZE_Y && y >= 25;
    }

    public void setValid(boolean valid) {
        this.Valid = valid;
    }

    public boolean foodThere() {
        for(Food food : getWorldRef().getFoodList()){
            if (food.getID() == targetID){
                // Return true if the target food id was found
                return true;
            }
        }
        return false;
    }

    public void storeFood() {
        this.setTargetingFood(false);

        for(int i = 0; i < this.getWorldRef().getFoodList().size(); ++i) {
            if (this.MaxInventory > (this.getWorldRef().getFoodList().get(i).getSize())){
                this.getWorldRef().getFoodGroup().getChildren().remove(i);
                this.getWorldRef().getFoodList().remove(i);
            }
        }

    }

    public boolean animalThere() {
        for(Animal animal : getWorldRef().getAnimalList()){
            if (animal.getID() == targetID){
                if (Collision.overlapsEfficient(animal.getMarker(), getSmellCircle())) {
                    // return true if the animal was found within the list
                    return true;
                }
            }
        }
        return false;
    }

    public void targetHome() {
        this.setTargetHomeBool(true);
        this.setFollowMainTimer(1000);
        this.setMainTarg(this.getHomeTarg());
    }

    public void randomLocalTarg() {
        Random rand = new Random();
        int tempAngle = this.turnAngle;
        int mightBeStuckCount = 0;
        int path = this.getSmellRange();

        int tX;
        int tY;
        int anAngle;
        do {
            anAngle = rand.nextInt(tempAngle * 2) - this.getTurnAng() + this.getPrevAngle();
            double angleRad = Math.toRadians((double)anAngle);
            tX = (int)(this.getMarker().getCenterX() + this.getMarker().getTranslateX() + (double)path * Math.cos(angleRad));
            tY = (int)(this.getMarker().getCenterY() + this.getMarker().getTranslateY() + (double)path * Math.sin(angleRad));
            ++mightBeStuckCount;
            if (mightBeStuckCount > 10) {
                path = this.getSightRange();
                tempAngle = 360;
                if (mightBeStuckCount > 20) {
                    this.setHealth(-10);
                    return;
                }
            }
        } while(!this.isValid(tX, tY));

        this.setLocalTarg(new Target(tX, tY));
        this.setPrevAngle(anAngle);
    }

    public double getAngTo(double targx, double targy) {
        double aX = this.getMarker().getCenterX() + this.getMarker().getTranslateX();
        double aY = this.getMarker().getCenterY() + this.getMarker().getTranslateY();
        return Math.atan2(targy - aY, targx - aX);
    }

    public boolean checkMove(int x, int y) {
        return x - this.getPDistance() > 0 || y - this.getPDistance() > 0;
    }

    public void move() {
        if (this.checkMove((int)(this.getMarker().getTranslateX() + this.getMarker().getCenterX() + this.getDx()), (int)(this.getMarker().getTranslateY() + this.getMarker().getCenterY() + this.getDy()))) {
            this.getMarker().setTranslateX(this.getMarker().getTranslateX() + this.getDx());
            this.getMarker().setTranslateY(this.getMarker().getTranslateY() + this.getDy());
            this.getSmellCircle().setTranslateX(this.getSmellCircle().getTranslateX() + this.getDx());
            this.getSmellCircle().setTranslateY(this.getSmellCircle().getTranslateY() + this.getDy());
            this.getSightCircle().setTranslateX(this.getSightCircle().getTranslateX() + this.getDx());
            this.getSightCircle().setTranslateY(this.getSightCircle().getTranslateY() + this.getDy());
            this.getTargLocation().setTranslateX(this.getLocalTarg().getCircle().getCenterX() + this.getLocalTarg().getCircle().getTranslateX());
            this.getTargLocation().setTranslateY(this.getLocalTarg().getCircle().getCenterY() + this.getLocalTarg().getCircle().getTranslateY());
        } else {
            this.randomLocalTarg();
        }

    }

    public void direct() {
        double TargX = this.getLocalTarg().getCircle().getCenterX() + this.getLocalTarg().getCircle().getTranslateX();
        double TargY = this.getLocalTarg().getCircle().getCenterY() + this.getLocalTarg().getCircle().getTranslateY();
        double angle = this.getAngTo(TargX, TargY);
        this.setDx(Math.cos(angle) * this.getSpeed());
        this.setDy(Math.sin(angle) * this.getSpeed());
    }

    public void checkHittingMain() {
        if (Collision.overlapsEfficient(this.getMarker(), this.getMainTarg().getCircle()) && Collision.overlapsAccurate(this.getMarker(), this.getMainTarg().getCircle())) {
            if (this.getTargetHomeBool()) {
                this.enterBase();
            }

            this.setMainTargetBool(false);
            this.removeLocalTarget();
            this.randomLocalTarg();
        }

    }

    public void checkHittingLocal() {
        if (this.getTargetingFood() && !this.foodThere()) {
            this.removeLocalTarget();
        }

        if (this.getTargetingAnimal() && !this.animalThere()) {
            this.removeLocalTarget();
        }

        if (Collision.overlapsEfficient(this.getMarker(), this.getLocalTarg().getCircle()) && Collision.overlapsAccurate(this.getMarker(), this.getLocalTarg().getCircle())) {
            if (this.getTargetingAnimal()) {
                this.fight();
            } else if (this.getTargetingFood()) {
                this.storeFood();
            }

            this.removeLocalTarget();
        }

    }

    public void fight() {
        if (getHealth() > 0) {
            // loop for all Animals
            for (Animal animal : getWorldRef().getAnimalList()) {
                if (animal.getID() == getTargetID()){
                    if (animal.getStrength() <= getStrength()) {
                        animal.setHealth(-10);
                        return;
                    }
                }
            }
        }
    }

    public boolean isInHuntList(char mark) {
       int X;
        if (mark == 'A') {
            X = 0;
        } else {
            if (mark != 'B') {
                return false;
            }

            X = 1;
        }

        int Y;
        if (this.getMark() == 'A') {
            Y = 0;
        } else {
            if (this.getMark() != 'B') {
                return false;
            }

            Y = 1;
        }

        return getWorldRef().getHuntList().get(X).get(Y);
    }

    public boolean isInEatList(char mark) {
        int X;
        if (mark == 'A') {
            X = 0;
        } else if (mark == 'B') {
            X = 1;
        }else{return false;}


        int Y;
        if (getMark() == 'A') {
            Y = 0;
        } else if (getMark() == 'B') {
            Y = 1;
        }else{ return false;}



        return getWorldRef().getEatList().get(X).get(Y);
    }

    public void checkFood(){
        for(Animal animal : getWorldRef().getAnimalList()) {
            if (isInHuntList(animal.getMark())) {
                if (getID() != animal.getID()) {
                    if (Collision.overlapsEfficient(this.getSmellCircle(), animal.getMarker())) {
                        if (Collision.overlapsAccurate(this.getSmellCircle(), animal.getMarker())) {
                            setTargetingAnimal(true);
                            setTargetID(animal.getID());
                            setLocalHuntTarget(animal.getMarker());
                            break;
                        }
                    }
                }
            }
        }
        // Check for food within the surrounding area
        for(Food food : getWorldRef().getFoodList()){
            boolean test = isInEatList(food.getMark());
            if (test) {
                if (Collision.overlapsEfficient(this.getSmellCircle(), food.getMarker())) {
                    if (Collision.overlapsAccurate(this.getSmellCircle(), food.getMarker())) {
                        // save the information
                        setTargetingFood(true);
                        setTargetingAnimal(false);
                        setTargetID(food.getID());
                        setLocalHuntTarget(food.getMarker());
                        return;
                    }
                }
            }
        }
    }

    public void createLocaltoMain() {
        double x = this.getMainTarg().getCircle().getCenterX() + this.getMainTarg().getCircle().getTranslateX();
        double y = this.getMainTarg().getCircle().getCenterY() + this.getMainTarg().getCircle().getTranslateY();
        double angle = this.getAngTo(x, y);
        int tX = (int)(this.getMarker().getCenterX() + this.getMarker().getTranslateX() + (double)this.getPDistance() * Math.cos(angle));
        int tY = (int)(this.getMarker().getCenterY() + this.getMarker().getTranslateY() + (double)this.getPDistance() * Math.sin(angle));
        this.setLocalTarg(new Target(tX, tY));
    }

    public void target() {
        this.breedtimerCD();
        this.TimerFollowMain();
        if (this.isMainTargetBool()) {
            this.checkHittingMain();
            if (this.getLocalTargetBool()) {
                this.checkHittingLocal();
            } else {
                this.createLocaltoMain();
            }
        } else {
            if (!this.getLocalTargetBool()) {
                this.randomLocalTarg();
            }

            if (this.getLocalTargetBool()) {
                this.checkHittingLocal();
            }

            if (this.HomeTarg == null) {
                this.checkBase();
            }

            if (this.HomeTarg != null && this.getBreedTimer() < 1 && this.getAgeYear() >= 1) {
                this.targetHome();
            }

            if (!this.getTargetingFood() && !this.getTargetingAnimal()) {
                this.checkFood();
            }
        }

    }

    public void exitBase() {
        this.setHomeRest(0);
        this.setAtBase(false);
        this.setVisibility(true);
    }

    public void enterBase() {
        Random rand = new Random();
        setHomeRest(400);
        setAtBase(true);
        // make self invisible while within the shelter
        setVisibility(false);
        setTargetHomeBool(false);


        // loop through all shelter
        for (int i = 0; i < getWorldRef().getShelterList().size(); i++) {
            if (getWorldRef().getShelterList().get(i).getID() == getHomeID()) {
                // Check if the opposite sex is in the shelter and is also the correct age
                if (getBreed()) {
                    setHomeRest(600);
                    for (Animal animal : getWorldRef().getAnimalList()) {
                        if (animal.getAtBase()) {
                            if (animal.getSex() != getSex()) {
                                if (animal.getBreed()) {
                                    if (animal.getHomeID() == getHomeID()) {
                                        animal.setBreed(false);
                                        setBreed(false);
                                        createBaby(animal);
                                        setBreedTimer(5000);
                                        animal.setBreedTimer(5000);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    setBreedTimer(5000);
                }
            }
        }
    }

    public void addSelfToLists() {
        getWorldRef().getAnimalList().add(this);
        getWorldRef().getAnimalGroup().getChildren().add(getMarker());
        getWorldRef().getAnimalSmellGroup().getChildren().add(getSmellCircle());
        getWorldRef().getAnimalSightGroup().getChildren().add(getSightCircle());
        getWorldRef().getAnimalTargetGroup().getChildren().add(getTargLocation());
        getWorldRef().getAnimalHomeLocationGroup().getChildren().add(getHomeLocation());
    }

    public void update() {
        if (getAtBase()) {
            setHomeRest(getHomeRest() - 1);
            if (getHomeRest() <= 0) {
                exitBase();
            }
        } else {
            checkHunger();
            target();
            direct();
            move();
        }

        ageAnimal();
    }

    public void checkBase() {
    }
}
