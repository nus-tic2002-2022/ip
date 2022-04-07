package ui;

import java.util.Scanner;

import tasks.TaskList;

/**
 * Text UI of the application.
 * Prints responses based on input provided.
 */
public class UI {
    private final Scanner in = new Scanner(System.in);

    /** Prints the divider line. */
    public void showLine() {
        System.out.println("---------------------------------");
    }

    /** Prints the welcome message. */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        showLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        showLine();
    }

    /** Take in user input. */
    public String readCommand() {
        return in.nextLine();
    }

    /** Prints the goodbye message. */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /** Prints reply when task successfully marked as done. */
    public void printMarkedTask() {
        System.out.println("Marked task as done");
    }

    /** Prints reply when task unsuccessfully marked as done because it is already marked done. */
    public void printErrorTaskAlreadyMarked() {
        System.out.println("Task is already marked as done");
    }

    /** Prints reply when task successfully unmarked as done. */
    public void printUnmarkedTask() {
        System.out.println("Unmarked task");
    }

    /** Prints reply when task unsuccessfully unmarked as done because it is already unmarked. */
    public void printErrorTaskAlreadyUnmarked() {
        System.out.println("Task is already unmarked");
    }

    /**
     * Prints the number of tasks in the list
     *
     * @param number The Number of tasks in the list
     */
    public void printNumberOfTask(int number) {
        System.out.println("Now you have " + number + " in the list.");
    }

    /**
     * Prints an individual task out with an indentation
     *
     * @param task The task to be printed out
     */
    public void printTask(String task) {
        System.out.println("  " + task);
    }

    /**
     * Prints the success message when a task is successfully added.
     *
     * @param task Task that was added
     * @param number Number of task in the list now
     */
    public void printTaskAdded(String task, int number) {
        System.out.println("Got it. I've added this task:");
        printTask(task);
        printNumberOfTask(number);
    }

    /**
     * Prints the success message when a task is successfully deleted.
     *
     * @param task Task that was deleted
     * @param number Number of task in the list now
     */
    public void printTaskDeleted(String task, int number) {
        System.out.println("Noted. I've removed this task:");
        printTask(task);
        printNumberOfTask(number);
    }

    /**
     * Prints the success message when a task is successfully updated.
     *
     * @param task Task that was updated
     */
    public void printUpdatedTask(String task) {
        System.out.println("Got it. I've updated this task:");
        printTask(task);
    }

    /**
     * Prints the error message when a task is unsuccessfully updated.
     *
     * @param task Task that was attempted to be updated
     */
    public void printTaskNotUpdated(String task) {
        System.out.println("Task did not have a date to update");
        printTask(task);
    }

    /**
     * Prints an error message
     *
     * @param errorMessage The Error Message
     */
    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints the error message when the task does not exist
     *
     * @param msg The input provided by the user which caused this error
     */
    public void printErrorTaskDoesNotExist(String msg) {
        System.out.println("Task does not exist: " + msg);
    }

    /**
     * Prints all tasks
     * If list is empty prints a message saying the list is empty
     *
     * @param taskList The list containing all the tasks to be printed
     */
    public void printAllTasks(TaskList taskList) {
        if (taskList.getNumberOfTask() == 0) {
            System.out.println("There is currently nothing in the list");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.getNumberOfTask(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
    }

    /**
     * Prints search results from a find function
     * If list is empty prints a message saying the list is empty
     *
     * @param taskList The list containing all the tasks returned from the function
     */
    public void printSearchResults(TaskList taskList) {
        if (taskList.getNumberOfTask() == 0) {
            System.out.println("There is currently nothing matching your search");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < taskList.getNumberOfTask(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
    }

    /** Prints the success message when all tasks are successfully deleted. */
    public void printAllTasksDeleted() {
        System.out.println("All task have been deleted!");
    }

    /** Prints the success message when all marked tasks are successfully deleted. */
    public void printAllMarkedTasksDeleted() {
        System.out.println("All marked tasks have been deleted!");
    }

    public void printHelp(String helpMsg) {
        System.out.println(helpMsg);
    }

}
