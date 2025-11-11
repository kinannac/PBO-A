
/**
 * Write a description of class Simulator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.ArrayList;  
import java.util.List;  
import java.util.Random;  
import java.util.Iterator; 
 
 public class Simulator {  
   private List<Animal> animals;  
   private Field field;  
   private int step;  
   private SimulatorView view; 
   
   public Simulator(int row, int col){  
     if (row <= 0 || col <= 0){  
       System.out.println("The number of rows and columns must be greater than zero.");  
       System.out.println("Using default values.");  
       row = 10;  
       col = 10;  
     }  
     animals = new ArrayList<Animal>();  
     field = new Field(row, col);  
     view = new SimulatorView(row, col);  
     view.setView(Rabbit.class, 'R');  
     view.setView(Fox.class, 'F');  
     reset();  
   } 
   
   public void simulate(int maxSteps){  
     for (int step = 1; step <= maxSteps; step++){  
       simulateOneStep();  
     }  
   }  
   
   public void reset(){  
     step = 0;  
     animals.clear();  
     field.clearAll();  
     populate();  
     view.showField(step, field);  
   }  
   
   public void simulateOneStep(){  
     step++;  
     List<Animal> newAnimals = new ArrayList<Animal>();  
     for (Iterator<Animal> it = animals.iterator(); it.hasNext();){  
       Animal animal = it.next();  
       animal.act(newAnimals);  
     }  
     view.showField(step, field);  
   }  
   
   private void populate(){  
     field.clearAll();  
     double foxPopulation = 0.05;  
     double rabbitPopulation = 0.15;  
     Random rand = new Random();  
     for (int r = 0; r < field.getRow(); r++){  
       for (int c = 0; c < field.getCol(); c++){  
         Location location = new Location(r, c);  
         if (rand.nextDouble() <= foxPopulation){  
           Fox fox = new Fox(false, field, location);  
           animals.add(fox);  
           field.place(fox, location);  
         } else if (rand.nextDouble() <= rabbitPopulation){  
           Rabbit rabbit = new Rabbit(true, field, location);  
           animals.add(rabbit);  
           field.place(rabbit, location);  
         }  
       }  
     }  
   }  
 } 