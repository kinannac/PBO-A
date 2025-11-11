
/**
 * Write a description of class Field here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.ArrayList;  
import java.util.List;
  
 public class Field {  
     
   private Object[][] field;  
   
   public Field(int row, int col) {  
     field = new Object[row][col];  
   }  
   
   public void clear(Location location) {  
     field[location.getRow()][location.getCol()] = null;  
   } 
   
   public void place(Object object, Location location) {  
     field[location.getRow()][location.getCol()] = object;  
   } 
   
   public void clearAll() {  
     for (int row = 0; row < getRow(); row++) {  
       for (int col = 0; col < field[row].length; col++) {  
         field[row][col] = null;  
       }  
     }  
   }
   
   public Object getObjectAt(Location location) {  
     if (location.getRow() >= 0 && location.getRow() < getRow() && location.getCol() >= 0  
         && location.getCol() < getCol()) {  
       return field[location.getRow()][location.getCol()];  
     } else {  
       return null;  
     }  
   } 
   
   public List<Location> getAllFreeAdjacentLocations(Location location) {  
     List<Location> free = new ArrayList<>();  
     List<Location> adjacent = adjacentLocations(location);  
     for (Location loc : adjacent) {  
       if (getObjectAt(loc) == null) {  
         free.add(loc);  
       }  
     }  
     return free;  
   }  
   
   public Location getFreeAdjacentLocations(Location location) {  
     List<Location> free = getAllFreeAdjacentLocations(location);  
     if (free.size() > 0) {  
       return free.get(0);  
     }  
     return null;  
   } 
   
   public List<Location> adjacentLocations(Location location) {  
     List<Location> locations = new ArrayList<>();  
     if (location==null) {  
       return locations;  
     }  
     
     int row = location.getRow();  
     int col = location.getCol();  
     
     if (row > 0) {  
       locations.add(new Location(row - 1, col));  
     }  
     if (row < getRow() - 1) {  
       locations.add(new Location(row + 1, col));  
     }  
     if (col > 0) {  
       locations.add(new Location(row, col - 1));  
     }  
     if (col < getCol() - 1) {  
       locations.add(new Location(row, col + 1));  
     }  
     return locations;  
   }  
   
   public int getRow() {  
     return field.length;  
   } 
   
   public int getCol() {  
     return field[0].length;  
   }  
 }  