
/**
 * Write a description of class GamePanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    static final int GAME_WIDTH = 800;
    static final int GAME_HEIGHT = 600;
    
    Thread gameThread;
    Image image;
    Graphics graphics;
    Paddle player1, player2;
    Ball ball;
    int score1 = 0, score2 = 0;
    
    public GamePanel() {
        player1 = new Paddle(0, (GAME_HEIGHT/2)-50);
        player2 = new Paddle(GAME_WIDTH-20, (GAME_HEIGHT/2)-50);
        ball = new Ball((GAME_WIDTH/2)-15, (GAME_HEIGHT/2)-15);
        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(new AL());
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        g.setColor(Color.WHITE);
        for (int i = 0; i < GAME_HEIGHT; i += 20) {
            g.drawLine(GAME_WIDTH/2, i, GAME_WIDTH/2, i+10);
        }

        g.drawOval(GAME_WIDTH/2-50, GAME_HEIGHT/2-50, 100, 100);

        player1.draw(g);
        player2.draw(g);
        ball.draw(g);
        
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(String.valueOf(score1), GAME_WIDTH/4, 50);
        g.drawString(String.valueOf(score2), 3*GAME_WIDTH/4 - 30, 50);
    }
    
    public void move() {
        player1.move();
        player2.move();
        ball.move();
        
        if (ball.x <= player1.x + player1.width && 
            ball.y >= player1.y && 
            ball.y <= player1.y + player1.height) {
            ball.xVelocity = -ball.xVelocity;
            ball.x = player1.x + player1.width;
        }
        
        if (ball.x + ball.diameter >= player2.x && 
            ball.y >= player2.y && 
            ball.y <= player2.y + player2.height) {
            ball.xVelocity = -ball.xVelocity;
            ball.x = player2.x - ball.diameter;
        }
        
        if (ball.x < 0) {
            score2++;
            ball.reset();
        }
        if (ball.x > GAME_WIDTH) {
            score1++;
            ball.reset();
        }
        
        if (player1.y < 0) player1.y = 0;
        if (player1.y + player1.height > GAME_HEIGHT) 
            player1.y = GAME_HEIGHT - player1.height;
        
        if (player2.y < 0) player2.y = 0;
        if (player2.y + player2.height > GAME_HEIGHT) 
            player2.y = GAME_HEIGHT - player2.height;
    }
    
    public void run() {
        double nsPerFrame = 1000000000 / 60.0;
        long lastTime = System.nanoTime();
        double delta = 0;
        
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerFrame;
            lastTime = now;
            
            while (delta >= 1) {
                move();
                repaint();
                delta--;
            }
        }
    }
    
    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            player1.keyPressed(e);
            player2.keyPressed(e);
        }
        
        public void keyReleased(KeyEvent e) {
            player1.keyReleased(e);
            player2.keyReleased(e);
        }
    }
}