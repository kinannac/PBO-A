
/**
 * Write a description of class SimulatorView here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.HashMap;  
import java.util.Map;  

 public class SimulatorView {  
   private int row;  
   private int col;  
   private Map<Class<?>, Character> fieldView;  
   
   public SimulatorView(int row, int col) {  
     this.row = row;  
     this.col = col;  
     fieldView = new HashMap<Class<?>, Character>();  
   }  
   
   public void setView(Class<?> animalClass, Character viewChar) {  
     fieldView.put(animalClass, viewChar);  
   }  
   
   public void showField(int step, Field field){  
     System.out.println("Step: " + step);  
     for (int r =0; r<row; r++){  
       for (int c = 0; c<col; c++){  
         Object animal = field.getObjectAt(new Location(r, c));  
         if (animal == null){  
           System.out.print('.');  
         } else {  
           Character viewChar = fieldView.get(animal.getClass());  
           if (viewChar != null){  
             System.out.print(viewChar);  
           } else {  
             System.out.print("?");  
            }
         }  
       }  
       System.out.println();  
     }  
     System.out.println();  
   }  
 }  