package duke.task;

import duke.importer.TaskFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;


public class Task implements Comparable<Task> {
    final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm").withResolverStyle(ResolverStyle.STRICT);
    protected String taskType;
    protected String description;
    protected boolean isDone;
    protected LocalDateTime dateTime;

    /**
     * Constructor for user input
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        System.out.println("Got it. I've added this task:");
    }

    /**
     * Constructor for importing from task file
     * @param description The description of the task
     * @param isDone The status of the task
     */
    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * This method calls to add the new tasks created from user input into the task file
     */
    public void newTask(){
        try {
            TaskFile.appendTask(addToFile());
            System.out.println("The task was saved successfully");
        } catch (IOException i){
            System.out.println("☹ OOPS!!! The task was not saved to the file.");
        }
    }

    /**
     * Returns the task type of the task.
     * Todo, Deadline or Event task.
     * @return T, D or E
     */
    public String getTaskType(){
        return taskType;
    }

    /**
     * Stores the date/time of the task in this format.
     * 1999-30-06 1800
     * @param date Date in String format
     */
    protected void setDT(String date){
                    dateTime = LocalDateTime.parse(date, DATE_TIME_FORMAT);
    }

    /**
     * Returns the date/time of the task
     * @return LocalDateTime
     */
    public LocalDateTime getDT() {
        return dateTime;
    }

    /**
     * Prints the date/time of the task in this format.
     * Jun 30 1999, 06:00pm
     * @return String
     */
    protected String printDT() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd uuuu, hh:mm a"));
    }

    /**
     * Gets the status icon of the task where "X" indicates done and " " indicates it is not done
     * @return "X" or " "
     * @see #isDone
     */
    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    /**
     * Returns a string that is to be saved into the task file
     * @return the status and description of the task
     */
    protected String addToFile() {
        return "[" + getStatusIcon() + "] " + description;
    }
    /**
     * Returns a string that is used for displaying to the user
     * @return the status and description of the task
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Compares the date of 2 tasks that are having date/time associated with them
     * @param otherTask
     * @return -1 if given is smaller, 0 if both are equal and 1 if given is bigger
     * @see {@link java.time.LocalDateTime#compareTo(ChronoLocalDateTime)}
     */
    @Override
    public int compareTo(Task otherTask) {
        return getDT().compareTo(otherTask.getDT());
    }
    //...
}