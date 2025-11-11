
/**
 * Write a description of class Rabbit here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.List;  

public class Rabbit extends Animal {  
   public Rabbit(boolean randomAge, Field field, Location location) {  
     super(randomAge, field, location);  
   }
   
   @Override  
   public void act(List<Animal> newAnimals) {  
     incrementAge();  
     if (isAlive()) {  
       giveBirth(newAnimals);  
       Location newLocation = field.getFreeAdjacentLocations(location);  
       if (newLocation != null) {  
         setLocation(newLocation);  
       } else {  
         setDead();  
       }  
     }  
   } 
   
   public void setDead() {  
     alive = false;  
     if (location != null) {  
       field.clear(location);  
       location = null;  
       field = null;  
     }  
   } 
   
   public void setLocation(Location newLocation) {  
     if (location != null) {  
       field.clear(location);  
       location = newLocation;  
     }  
   } 
   
   @Override  
   protected int getMaxAge() {  
     return 5;  
   }
   
   @Override  
   protected int getBreedingAge() {  
     return 1;  
   } 
   
   @Override  
   protected double getBreedingProbability() {  
     return 0.12;  
   }
   
   @Override  
   protected int getMaxLitterSize() {  
     return 4;  
   }
   
   @Override  
   protected Animal createYoung(Field field, Location location) {  
     return new Rabbit(false,field, location);  
   }  
}  