
/**
 * Write a description of class Ball here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;

public class Ball {
    int x, y;
    int xVelocity = 3;
    int yVelocity = 3;
    int diameter = 30;
    int startX, startY;
    
    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, diameter, diameter);
    }
    
    public void move() {
        x += xVelocity;
        y += yVelocity;
        
        if (y <= 0 || y >= GamePanel.GAME_HEIGHT - diameter) {
            yVelocity = -yVelocity;
        }
    }
    
    public void reset() {
        x = startX;
        y = startY;
        xVelocity = 3;
        yVelocity = 3;
    }
}
