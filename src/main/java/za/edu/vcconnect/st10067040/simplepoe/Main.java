package za.edu.vcconnect.st10067040.simplepoe;

import java.util.Scanner;
import java.util.regex.Pattern;

/*
 * @author Chelden Booysen (ST10067040)
 * Programming POE 2022
 * 10 May 2022
 * This is the simple one because you
 * said the other one was too complex
 * Also I don't need to reference since
 * I didn't use any code from the internet
 */
public class Main {

    // declare registered user
    public static RegisterUser registeredUser;

    public static void main(String[] args) {

        // declare variables
        String firstname, lastname, username, password;
        // init scanner for users input
        Scanner input = new Scanner(System.in);


        System.out.println("Welcome!\nPress 3 at anytime to exit the program\nPlease Register:");
        System.out.print("First Name:\t");
        firstname = input.next();
        System.out.print("Last Name:\t");
        lastname = input.next();
        System.out.print("Username:\t");
        username = checkUsername(input.next());
        System.out.print("Password:\t");
        password = checkPasswordComplexity(input.next());

        // create a new RegisterUser instance to register the user
        // based on the info they inputted
        new RegisterUser(firstname, lastname, username, password);

        // prompt the user to login
        loginUser();

    }

    // this method returns the username only if the username meets all the requirements
    public static String checkUsername(String username) {
        // check if the username is smaller than 5, and contains an underscore(_)
        if (username.length() > 5 || !username.contains("_")) {
            Scanner input = new Scanner(System.in);
            System.out.println("\nUsername is not correctly formatted, \n" +
                    "please ensure that your username contains an \nunderscore and is no more than 5 characters in length.\n");
            // prompt the user to input a new username that meets all requirements
            System.out.print("Username:\t");
            return checkUsername(input.next());

        } else {
            System.out.println("Username successfully captured.");
            return username;
        }
    }

    // this method returns the password only if the password meets all the requirements
    public static String checkPasswordComplexity(String password) {
        // check if the password is greater than or equal to 8, contains a number, a capital letter and a special character.
        if (password.length() < 8 || !Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d+.*).+$").matcher(password).matches() ||
                Pattern.compile("[^A-Za-z\\d]").matcher(password).matches()) {

            Scanner input = new Scanner(System.in);
            System.out.println("\nPassword is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.\n");
            // prompt the user to input a new password that meets all requirements
            System.out.print("Password:\t");
            return checkPasswordComplexity(input.next());
        } else {
            System.out.println("Password successfully captured.");
            return password;
        }
    }

    // this method prompts the user to login and reruns if the user inputs incorrect values
    public static void loginUser() {
        Scanner input = new Scanner(System.in);
        String username, password;


        System.out.println("Please log in:");
        System.out.print("Username:\t");
        username = input.next();
        System.out.print("Password:\t");
        password = input.next();

        new Login(username, password);
    }

}
