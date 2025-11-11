
/**
 * Write a description of class Animal here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.List;  
import java.util.Random;  

 public abstract class Animal {  
   protected int age;  
   protected boolean alive;  
   protected Location location;  
   protected Field field;  
   protected static final Random rand = new Random();  
   public Animal(boolean randomAge,Field field, Location location) {  
     if (randomAge) {  
       this.age = rand.nextInt(getMaxAge());  
     } else {  
       this.age = 0;  
     }  
     this.field = field;  
     this.alive = true;  
     setLocation(location);  
   }  
   public int getAge() {  
     return age;  
   }  
   public boolean isAlive() {  
     return alive;  
   }  
   public Location getLocation() {  
     return location;  
   }  
   public void setLocation(Location newLocation) {  
     if (location != null) {  
       field.clear(location);  
     }  
     location = newLocation;  
     field.place(this, newLocation);  
   }  
   public void setDead() {  
     alive = false;  
     if (location != null) {  
       field.clear(location);  
       location = null;  
     }  
     field = null;  
   }  
   public boolean canBreed() {  
     return age >= getBreedingAge();  
   }  
   protected void incrementAge() {  
     age++;  
     if (age > getMaxAge()) {  
       setDead();  
     }  
   }  
   protected void giveBirth(List<Animal> newAnimals) {  
     if (location == null) {  
       return;  
     }  
     List<Location> free = field.getAllFreeAdjacentLocations(location);  
     int totalOffspring = breed();  
     for (int b = 0; b < totalOffspring && free.size() > 0; b++) {  
       Location loc = free.remove(0);  
       Animal young = createYoung(field, loc);  
       newAnimals.add(young);  
     }  
   }  
   protected int breed() {  
     int totalOffspring = 0;  
     if (canBreed() && rand.nextDouble() <= getBreedingProbability()) {  
       totalOffspring = rand.nextInt(getMaxLitterSize()) + 1;  
     }  
     return totalOffspring;  
   }  
   public String printLocation(Location location) {  
     return String.format("(" + (location.getRow()+1) + "," + (location.getCol()+1) + ")");  
   }  
   public abstract void act(List<Animal> newAnimals);  
   protected abstract int getMaxAge();  
   protected abstract int getBreedingAge();  
   protected abstract double getBreedingProbability();  
   protected abstract int getMaxLitterSize();  
   protected abstract Animal createYoung(Field field, Location location);  
 }