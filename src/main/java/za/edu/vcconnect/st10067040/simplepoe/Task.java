package za.edu.vcconnect.st10067040.simplepoe;

import java.util.HashSet;
import java.util.Set;

/*
 * @author Chelden Booysen (ST10067040)
 * Programming POE 2022
 * 7 June 2022
 * This is the simple one because you
 * said the other one was too complex
 * Also I don't need to reference since
 * I didn't use any code from the internet
 */
public class Task {

    // declare variables
    String taskName;
    int taskNumber;
    String taskDescription;
    String developerDetails;
    TaskStatus taskStatus;
    float taskDuration;
    String taskId;
    // init a hash set to save all tasks
    public static Set<Task> savedTasks = new HashSet<>();

    /*constructor for the task object*/
    public Task(){
        // init empty constructor so we can make an instance
    }

    /*returns true if the description matches all criteria*/
    public static boolean checkTaskDescription(String description){
        return description.length() <= 50;

    }
    /*returns a string of all the task details*/
    public String printTaskDetails(){

        return "Task Name:\t" + this.getTaskName() +
                "\nTask Number:\t" + this.getTaskNumber() +
                "\nTask Description:\t" + this.getTaskDescription() +
                "\nDeveloper Details:\t" + this.getDeveloperDetails() +
                "\nTask Duration:\t" + this.getTaskDuration() +
                "\nTask ID:\t" + this.getTaskId() +
                "\nTask Status:\t" + this.getTaskStatus().toString();
    }

    /*returns the total amount of hours among all tasks*/
    public float returnTotalHours(){
        float totalHours = 0;

        for (Task task : savedTasks){
            totalHours = totalHours + task.getTaskDuration();

        }
        return totalHours;
    }


    // setters and getters


    /*returns the tasks name*/
    public String getTaskName() {
        return taskName;
    }

    /*sets the task name*/
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /*returns the task number*/
    public int getTaskNumber() {
        return taskNumber;
    }

    /*sets the task number*/
    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /*gets the task description*/
    public String getTaskDescription() {
        return taskDescription;
    }

    /*sets the task description*/
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /*get the developer details*/
    public String getDeveloperDetails() {
        return developerDetails;
    }
    /*sets the developer details*/
    public void setDeveloperDetails(String developerDetails) {
        this.developerDetails = developerDetails;
    }

    /*gets the task duration*/
    public float getTaskDuration() {
        return taskDuration;
    }

    /*sets the task duration*/
    public void setTaskDuration(float taskDuration) {
        this.taskDuration = taskDuration;
    }

    /*gets the task id*/
    public String getTaskId() {
        return taskId;
    }

    /*sets the task id*/
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /*gets the task status*/
    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    /*sets the task status*/
    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }


}
