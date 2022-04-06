package ui;

import java.util.Scanner;

import tasks.TaskList;


public class UI {
    private final Scanner in = new Scanner(System.in);

    public void showLine() {
        System.out.println("---------------------------------");
    }

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

    public String readCommand() {
        return in.nextLine();
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printMarkedTask() {
        System.out.println("Marked task as done");
    }

    public void printErrorTaskAlreadyMarked() {
        System.out.println("Task is already marked as done");
    }

    public void printUnmarkedTask() {
        System.out.println("Unmarked task");
    }

    public void printErrorTaskAlreadyUnmarked() {
        System.out.println("Task is already unmarked");
    }

    public void printNumberOfTask(int number) {
        System.out.println("Now you have " + number + " in the list.");
    }

    public void printTask(String task) {
        System.out.println("  " + task);
    }

    public void printTaskAdded(String task, int number) {
        System.out.println("Got it. I've added this task:");
        printTask(task);
        printNumberOfTask(number);
    }

    public void printTaskDeleted(String task, int number) {
        System.out.println("Noted. I've removed this task:");
        printTask(task);
        printNumberOfTask(number);
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printErrorTaskDoesNotExist(String msg) {
        System.out.println("Task does not exist: " + msg);
    }

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

}
