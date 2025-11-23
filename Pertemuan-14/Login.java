
/**
 * Write a description of class Login here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login {
    private String Username = "kinannac";
    private String Password = "passkinannac";

    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public static void main(String[] args) {
        Login gui = new Login();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame("Login Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));

        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");

        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new LoginListener());

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new CancelListener());

        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnLogin);
        panel.add(btnCancel);

        frame.getContentPane().add(panel);
        frame.setSize(300, 150);
        frame.setVisible(true);
    }

    public class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String userInput = txtUsername.getText();
            String passInput = new String(txtPassword.getPassword());

            if (userInput.equals(Username) && passInput.equals(Password)) {
                JOptionPane.showMessageDialog(null, "Login Successed!");
            } else {
                JOptionPane.showMessageDialog(null, "Login Denied!");
            }
        }
    }

    public class CancelListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            txtUsername.setText("");
            txtPassword.setText("");
            txtUsername.requestFocus();
        }
    }
}
