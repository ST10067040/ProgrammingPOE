package za.edu.vcconnect.st10067040.poe;

import java.util.Scanner;

/*
 * @author Chelden Booysen (ST10067040)
 * Programming POE 2022
 * 2 July 2022
 * This is the simple one because you
 * said the other one was too complex
 * Also I don't need to reference since
 * I didn't use any code from the internet
 */
public class Report {
    // declare variables
    String[] developerDetails, taskNames, taskIDs;
    Float[] taskDurations;
    TaskStatus[] taskStatuses;

    // report constructor
    Report(String[] developerDetails, String[] taskNames, String[] taskIDs, Float[] taskDurations, TaskStatus[] taskStatuses) {
        // initialize variables from constructor
        this.developerDetails = developerDetails;
        this.taskNames = taskNames;
        this.taskIDs = taskIDs;
        this.taskDurations = taskDurations;
        this.taskStatuses = taskStatuses;

        // infinite while loop for report menu
        while (true) {
            //display all options to user
            System.out.println("a. Status Done");
            System.out.println("b. Longest Duration");
            System.out.println("c. Search for a task");
            System.out.println("d. Search for all tasks assigned to developer");
            System.out.println("e. Delete a task");
            System.out.println("f. Display Report");
            System.out.println("g. Exit");
            System.out.println("Enter your choice:");

            // init scanner for menu
            Scanner input = new Scanner(System.in);
            // switch statement for handling user input
            switch (input.next()) {
                case "a":
                    // displays all tasks with the "DONE" task status
                    displayDoneTasks();
                    break;
                case "b":
                    // displays the task with the longest duration
                    displayLongestTask();
                    break;
                case "c":
                    System.out.println("Enter task name");
                    String taskName = new Scanner(System.in).nextLine();
                    // searches for a specific task based on its name
                    findTask(taskName);
                    break;
                case "d":
                    System.out.println("Enter developer name");
                    // searches for all tasks assigned to a developer
                    findDeveloperAssignedTask(new Scanner(System.in).nextLine());
                    break;

                case "e":
                    System.out.println("Enter task name");
                    // deletes the task
                    deleteTask(new Scanner(System.in).nextLine());
                    break;
                case "f":
                    // displays all details of each task 
                    displayFullDetails();
                    break;
                case "g":
                    // returns to the original menu
                    return;
                default:
                    System.out.println("Please enter a character regarding what you would like to select!");
            }
        }

    }


    // displays all tasks with the "DONE" task status
    public void displayDoneTasks() {
        System.out.println("Developer\t\t\tTask Name\t\t\tTask Duration");
        for (int i = 0; i < taskStatuses.length; i++) {
            if (taskStatuses[i] == TaskStatus.DONE)
                System.out.println(developerDetails[i] + "\t\t\t" + taskNames[i] + "\t\t\t" + taskDurations[i]);
        }
    }

    // displays the task with the longest duration
    public void displayLongestTask() {
        float max = taskDurations[0];
        int index = 0;
        for (int i = 1; i < taskDurations.length; i++)
            if (taskDurations[i] > max) {
                max = taskDurations[i];
                index = i;
            }

        System.out.println("Developer Name\t\t\tDuration");
        System.out.println(developerDetails[index] + "\t\t\t" + max);

    }

    // finds and displays a task based on its name
    public void findTask(String taskName) {
        System.out.println("Task Name\t\t\tDeveloper\t\t\tTask Status");
        boolean check = true;
        for (int i = 0; i < taskNames.length; i++) {
            if (taskNames[i].equals(taskName)) {
                System.out.println(taskName + "\t\t\t" + developerDetails[i] + "\t\t\t" + taskStatuses[i]);
                check = false;
            }
        }
        if (check)
            System.out.println("A task with that name does not exist!");
    }


    //Search assigned task
    public void findDeveloperAssignedTask(String developer) {
        System.out.println("Task Name\t\t\tTask Status");
        boolean check = true;
        for (int i = 0; i < developerDetails.length; i++) {
            if (developerDetails[i].equals(developer)) {
                System.out.println(taskNames[i] + "\t\t\t" + taskStatuses[i]);
                check = false;
            }
        }
        if (check)
            System.out.println("A task assigned to that developer does not exist!");
    }

    // shift data into new arrays for deletion
    public void shiftData(int index) {
        String[] newDevelopers, newTaskNames, newTaskIDs;
        Float[] newTaskDurations;
        TaskStatus[] newTaskStatuses;
        //task is deleted so length will be decreased for all arrays
        int size = developerDetails.length - 1;
        // initialize variables
        newDevelopers = new String[size];
        newTaskNames = new String[size];
        newTaskIDs = new String[size];
        newTaskDurations = new Float[size];
        newTaskStatuses = new TaskStatus[size];
        
        // iterate through each task and assign them to the new arrays
        for (int i = 0; i < developerDetails.length; i++) {
            if (i != index) {
                newDevelopers[i] = developerDetails[i];
                newTaskNames[i] = taskNames[i];
                newTaskIDs[i] = taskIDs[i];
                newTaskDurations[i] = taskDurations[i];
                newTaskStatuses[i] = taskStatuses[i];
            }
        }
        
        // overwrite the old arrays with the new array
        developerDetails = newDevelopers;
        taskNames = newTaskNames;
        taskIDs = newTaskIDs;
        taskDurations = newTaskDurations;
        taskStatuses = newTaskStatuses;

    }


    // deletes a task based on its task name
    public void deleteTask(String taskName) {
        boolean check = true;
        for (int i = 0; i < taskNames.length; i++) {
            if (taskNames[i].equals(taskName)) {

                shiftData(i);  //Shifting elements to delete item
                System.out.println(taskName + " has been successfully deleted");
                check = false;
            }
        }
        if (check)
            System.out.println("A task with that name does not exist!");
    }

    // displays all details of each task 
    public void displayFullDetails() {
        System.out.println("Developer\t\t\tTask Name\t\t\tTask ID\t\t\tTask Duration\t\t\tTask Status");
        for (int i = 0; i < developerDetails.length; i++)
            System.out.println(developerDetails[i] + "\t\t\t" + taskNames[i] + "\t\t\t" + taskIDs[i] + "\t\t\t" + taskDurations[i] + "\t\t\t" + taskStatuses[i]);


    }


}