package za.edu.vcconnect.st10067040.poe;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

/*
 * @author Chelden Booysen (ST10067040)
 * Programming POE 2022
 * 3 May 2022
 * Sorry for over engineering everything Sir
 */
public class Main {


    public static void main(String[] args) {

        // init panel and labels for login window
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Username", SwingConstants.RIGHT));
        label.add(new JLabel("Password", SwingConstants.RIGHT));
        panel.add(label, BorderLayout.WEST);
        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField username = new JTextField();
        controls.add(username);
        JPasswordField password = new JPasswordField();
        controls.add(password);
        panel.add(controls, BorderLayout.CENTER);
        // add custom buttons to option dialog
        Object[] options = {"Login", "Register", "Cancel"};
        int result = JOptionPane.showOptionDialog(null, panel, "Login System",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, null);

        switch (result) {
            case 0: // login button

                // check if username and password fields aren't empty, if they are then restart the program
                if (username.getText().isEmpty() || password.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Both username and password field are required!", "Login System", JOptionPane.ERROR_MESSAGE);
                    main(null);
                    return;
                } else {
                    // try to log the user in with the provided credentials
                    new LoginUser(username.getText(), password.getText());
                }
                break;

            case 1: // register button

                // init register window (only because we need two extra fields for first name and last name when registering)
                JPanel panel1 = new JPanel(new BorderLayout(5, 5));
                JPanel label1 = new JPanel(new GridLayout(0, 1, 2, 2));
                label1.add(new JLabel("First Name", SwingConstants.RIGHT));
                label1.add(new JLabel("Last Name", SwingConstants.RIGHT));
                label1.add(new JLabel("Username", SwingConstants.RIGHT));
                label1.add(new JLabel("Password", SwingConstants.RIGHT));
                panel1.add(label1, BorderLayout.WEST);
                JPanel controls1 = new JPanel(new GridLayout(0, 1, 2, 2));
                JTextField firstname = new JTextField();
                controls1.add(firstname);
                JTextField lastname = new JTextField();
                controls1.add(lastname);
                JTextField username1 = new JTextField();
                controls1.add(username1);
                JPasswordField password1 = new JPasswordField();
                controls1.add(password1);
                panel1.add(controls1, BorderLayout.CENTER);
                // add custom buttons to option dialog
                Object[] options1 = {"Register", "Login", "Cancel"};
                int result1 = JOptionPane.showOptionDialog(null, panel1, "Login System",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options1, null);

                if(result1 == 0){
                    // check if the username, password, firstname or lastname fields aren't empty
                    if (username1.getText().isEmpty() || password1.getText().isEmpty() || firstname.getText().isEmpty() || lastname.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Username, password, firstname and lastname fields are required!", "Login System", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // test if the username matches the needed criteria and if not, then restart the program
                        if (!testUsername(username1.getText())) {
                            main(null);
                            System.out.println("Username does not match criteria! Restarting program");
                            return;
                        }
                        // test if the password matches the needed criteria and if not, then restart the program
                        if (!testPassword(password1.getText())) {
                            main(null);
                            System.out.println("Password does not match criteria! Restarting program");
                            return;
                        }
                        // if all tests meet the requirements, register the user.
                        new RegisterUser(username1.getText(), password1.getText(), firstname.getText(), lastname.getText());
                        JOptionPane.showMessageDialog(null, "You have successfully registered!\nYou can now login.", "Login System", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                main(null);
                return;

            case 2: // cancel button
                // exit the process with exit code 0
                System.exit(0);
                break;
        }
    }


    // this method returns a boolean based on if the username meets all the requirements
    public static boolean testUsername(String username) {
        // check if the username is smaller than 5, and contains an underscore(_)
        if (username.length() > 5 || !username.contains("_")) {
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.", "Login System", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Username successfully captured.", "Login System", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
    }

    // this method returns a boolean based on if the password meets all the requirements
    public static boolean testPassword(String password) {
        // check if the password is greater than or equal to 8, contains a number, a capital letter and a special character.
        if (password.length() < 8 ||
                !Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d+.*).+$").matcher(password).matches() ||
                Pattern.compile("[^A-Za-z\\d]").matcher(password).matches()) {
            JOptionPane.showMessageDialog(null, "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.", "Login System", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Password successfully captured.", "Login System", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
    }
}
