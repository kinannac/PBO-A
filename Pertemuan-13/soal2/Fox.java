
/**
 * Write a description of class Fox here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.List;

public class Fox extends Animal {
    private int foodLevel;
    private static final int RABBIT_FOOD_VALUE = 9;
    
    public Fox(boolean randomAge, Field field, Location location) {
        super(randomAge, field, location);
        foodLevel = RABBIT_FOOD_VALUE;
    }
    
    @Override
    public void act(List<Animal> newAnimals) {
        incrementAge();
        incrementHunger();
        if (isAlive()) {
            giveBirth(newAnimals);
            Location newLocation = findFood();
            if (newLocation == null) {
                newLocation = field.getFreeAdjacentLocations(location);
            }
            if (newLocation != null) {
                setLocation(newLocation);
            } else {
                setDead();
            }
        }
    }
    
    private void incrementHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            setDead();
        }
    }
    
    private Location findFood() {
        List<Location> adjacent = field.adjacentLocations(location);
        for (Location loc : adjacent) {
            Object animal = field.getObjectAt(loc);
            if (animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if (rabbit.isAlive()) {
                    rabbit.setDead();
                    foodLevel = RABBIT_FOOD_VALUE;
                    return loc;
                }
            }
        }
        return null;
    }
    
    @Override
    public void setLocation(Location newLocation) {
        if (location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    @Override
    protected int getMaxAge() {
        return 150;
    }
    
    @Override
    protected int getBreedingAge() {
        return 15;
    }
    
    @Override
    protected double getBreedingProbability() {
        return 0.08;
    }
    
    @Override
    protected int getMaxLitterSize() {
        return 5;
    }
    
    @Override
    protected Animal createYoung(Field field, Location location) {
        return new Fox(false, field, location);
    }
}