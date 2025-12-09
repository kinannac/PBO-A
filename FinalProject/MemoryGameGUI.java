
/**
 * Write a description of class MemoryGameGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.Timer;


public class MemoryGameGUI extends JFrame {

    private JButton[] buttons = new JButton[16];
    private int[] values = new int[16];

    private int firstIndex = -1;
    private int secondIndex = -1;

    private int skor = 0;
    private long startTime;

    public MemoryGameGUI() {
        setTitle("Memory Game - OOP Final Project");
        setSize(400, 450);
        setLayout(new BorderLayout());

        JPanel grid = new JPanel(new GridLayout(4, 4));
        add(grid, BorderLayout.CENTER);

        generateCards();

        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton("");
            int index = i;
            buttons[i].setFont(new Font("Arial", Font.BOLD, 18));

            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handleClick(index);
                }
            });

            grid.add(buttons[i]);
        }

        startTime = System.currentTimeMillis();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void generateCards() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            list.add(i);
            list.add(i);
        }
        Collections.shuffle(list);

        for (int i = 0; i < 16; i++) {
            values[i] = list.get(i);
        }
    }

    void handleClick(int index) {
        if (!buttons[index].getText().equals("")) return;

        buttons[index].setText(String.valueOf(values[index]));

        if (firstIndex == -1) {
            firstIndex = index;
        } else {
            secondIndex = index;

            Timer t = new Timer(500, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    checkMatch();
                }
            });
            t.setRepeats(false);
            t.start();
        }
    }

    void checkMatch() {
        if (values[firstIndex] == values[secondIndex]) {
            skor++;
        } else {
            buttons[firstIndex].setText("");
            buttons[secondIndex].setText("");
        }
        firstIndex = -1;
        secondIndex = -1;

        if (skor == 8) {
            finishGame();
        }
    }

    void finishGame() {
        long waktu = (System.currentTimeMillis() - startTime) / 1000;
    
        String nama = JOptionPane.showInputDialog(this,
                        "Selamat! Masukkan nama:");
    
        try {
            LeaderboardDAO.insertScore(nama, skor, (int) waktu);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "DB Error: " + ex.getMessage());
        }
    
        JOptionPane.showMessageDialog(this,
            "Game selesai!\nSkor: " + skor + "\nWaktu: " + waktu + " detik");
    
        try {
            ArrayList<String> list = LeaderboardDAO.getScores();
            StringBuilder sb = new StringBuilder(" LEADERBOARD \n\n");
    
            for (String s : list) {
                sb.append(s).append("\n");
            }
    
            JOptionPane.showMessageDialog(this, sb.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error load leaderboard: " + e.getMessage());
        }
    
        System.exit(0);
    }
}
