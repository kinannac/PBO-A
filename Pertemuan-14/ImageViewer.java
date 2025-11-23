
/**
 * Write a description of class ImageViewer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class ImageViewer {

    private JLabel imageLabel;

    public static void main(String[] args) {
        new ImageViewer().createUI();
    }

    public void createUI() {
        JFrame frame = new JFrame("Image Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnOpen = new JButton("Open Image");
        btnOpen.addActionListener(new OpenImageListener());

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        frame.add(btnOpen, BorderLayout.NORTH);
        frame.add(new JScrollPane(imageLabel), BorderLayout.CENTER);

        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    public class OpenImageListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                Image img = icon.getImage();
                Image newimg = img.getScaledInstance(500, 350, Image.SCALE_SMOOTH);

                imageLabel.setIcon(new ImageIcon(newimg));
            }
        }
    }
}
