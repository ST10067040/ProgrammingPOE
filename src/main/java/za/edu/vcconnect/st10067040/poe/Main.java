package za.edu.vcconnect.st10067040.poe;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import static za.edu.vcconnect.st10067040.poe.Task.checkTaskDescription;

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


    // init variables for report functionality
    static String[] developerDetails, taskNames, taskIDs;
    static TaskStatus[] taskStatuses;
    static Float[] taskDurations;


    public static void main(String[] args) {

        // declare variables
        String firstname, lastname, username, password;
        // init scanner for users input
        Scanner input = new Scanner(System.in);


        System.out.println("Welcome!\nPlease Register:");
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
        // print the users' login status
        System.out.println(Main.registeredUser.returnLoginStatus());

        // welcome the user if they have logged in, else prompt the user to login again
        if (registeredUser.isLoggedIn()) {
            System.out.println("Welcome " + Main.registeredUser.getFirstname() + ", " + Main.registeredUser.getLastname() + " it is great to see you.");
            System.out.println("Welcome to EasyKanBan.");


            // infinite while loop
            while (true) {
                System.out.println("1.Add Tasks 2.Show Report 3.Quit");
                int option;


                // test if the user entered a number, if not notify the user rerun the program
                try {
                    option = input.nextInt();
                } catch (InputMismatchException ignored) {
                    System.out.println("Please enter a number regarding what you would like to select! Process will exit.");
                    System.exit(0);
                    return;
                }

                // switch statement for users input
                switch (option) {
                    // add task option
                    case 1:

                        System.out.println("Please specify the amount of tasks you would like to add consecutively:");
                        int numberOfTasks = input.nextInt();

                        //initialize vars for show report functionality
                        developerDetails = new String[numberOfTasks];
                        taskNames = new String[numberOfTasks];
                        taskIDs = new String[numberOfTasks];
                        taskDurations = new Float[numberOfTasks];
                        taskStatuses = new TaskStatus[numberOfTasks];

                        // counter for setting each task in the arrays
                        int consecutiveTaskNumber = 0;


                        // while loop to allow the user to input multiple tasks consecutively
                        while (numberOfTasks > 0) {
                            Scanner sc = new Scanner(System.in);
                            Task newTask = new Task();

                            // set task name from users input
                            System.out.println("Please enter the name of the task you would like to add:");
                            String taskName = sc.nextLine();
                            newTask.setTaskName(taskName);
                            taskNames[consecutiveTaskNumber] = taskName;

                            // set task description from users input
                            System.out.println("Please enter the description of the task you would like to add:");
                            // test if the description matches all requirements, if not notify the user
                            String description = sc.nextLine();
                            while (!checkTaskDescription(description)) {
                                System.out.println("Please enter a task description containing less than 50 characters.");
                                description = sc.nextLine();
                            }
                            newTask.setTaskDescription(description);
                            // set task developer details from users input
                            System.out.println("Please enter developer details.");
                            String developer = sc.nextLine();
                            newTask.setDeveloperDetails(developer);
                            developerDetails[consecutiveTaskNumber] = developer;

                            // set task duration from users input
                            System.out.println("Please enter task duration. (hours)");
                            float duration = sc.nextFloat();
                            newTask.setTaskDuration(duration);
                            taskDurations[consecutiveTaskNumber] = duration;
                            // prompt the user to select one of the following task statuses
                            System.out.println("Please select a task status\n1. To Do\n2. Done\n3. Doing");
                            // switch statement for users input
                            switch (sc.nextInt()) {

                                // set task status to TODO
                                case 1:
                                    newTask.setTaskStatus(TaskStatus.TODO);
                                    taskStatuses[consecutiveTaskNumber] = TaskStatus.TODO;
                                    break;

                                // set task status to DONE
                                case 2:
                                    newTask.setTaskStatus(TaskStatus.DONE);
                                    taskStatuses[consecutiveTaskNumber] = TaskStatus.DONE;
                                    break;

                                // set task status to DOING
                                case 3:
                                    newTask.setTaskStatus(TaskStatus.DOING);
                                    taskStatuses[consecutiveTaskNumber] = TaskStatus.DOING;
                                    break;

                                // default parameter in case something goes wrong
                                default:
                                    System.err.println("Something went terribly wrong :(");
                                    System.exit(0);
                            }

                            // set task number based on the number of the saved tasks in the hash set
                            newTask.setTaskNumber(Task.savedTasks.size());


                            // split the task and developer strings into a character arrays
                            char[] taskChars = newTask.getTaskName().toUpperCase().toCharArray();
                            char[] developerChars = newTask.getDeveloperDetails().toUpperCase().toCharArray();

                            // set this task id with the first 2 chars of the task name
                            // and the last 3 chars of the developer details
                            String taskId =
                                    taskChars[0] + "" + taskChars[1] + ":" + newTask.getTaskNumber() + ":" +
                                            developerChars[developerChars.length - 3] +
                                            developerChars[developerChars.length - 2] +
                                            developerChars[developerChars.length - 1];
                            newTask.setTaskId(taskId);
                            taskIDs[consecutiveTaskNumber] = taskId;

                            // add the task to the saved tasks array
                            Task.savedTasks.add(newTask);
                            System.out.println("Your task was successfully saved.");
                            // display all details back to the user using a JOptionPane
                            JOptionPane.showMessageDialog(null,
                                    "Task Status: " + newTask.getTaskStatus().toString() +
                                            "\nDeveloper Details: " + newTask.getDeveloperDetails() +
                                            "\nTask Number: " + newTask.getTaskNumber() +
                                            "\nTask Name: " + newTask.getTaskName() +
                                            "\nTask Description: " + newTask.getTaskDescription() +
                                            "\nTask ID: " + newTask.getTaskId() +
                                            "\nTask Duration: " + newTask.getTaskDuration(),
                                    "Task Details", JOptionPane.PLAIN_MESSAGE);

                            // minus one from the number of tasks the user should still enter
                            numberOfTasks--;
                            consecutiveTaskNumber++;
                        }
                        // break out of the loop once the user has inputted the specified amount of tasks
                        break;

                    // show report option
                    case 2:
                        // check if there are any tasks saved before trying to display them
                        if (Task.savedTasks.size() == 0) {
                            System.out.println("There are no tasks to display. \nMaybe try adding a task first.");
                        } else {
                            // construct a new report based on all details captured above
                            new Report(developerDetails, taskNames, taskIDs, taskDurations, taskStatuses);
                        }

                        break;
                    // quit option
                    case 3:
                        // exit the program with exit code 0
                        System.out.println("Exiting with exit code 0");
                        System.exit(0);
                        break;

                    // default option in case something goes wrong
                    default:
                        System.err.println("Something went terribly wrong, exiting with exit code 1");
                        System.exit(1);

                }
            }
        } else {
            loginUser();
        }
    }
}
