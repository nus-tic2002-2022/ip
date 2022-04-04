package ui;

import java.util.Scanner;

public class UI{
    private final Scanner in = new Scanner(System.in);

    public void showLine(){
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

    public String readCommand(){
        return in.nextLine();
    }

    public void showBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printMarkedTask(){
        System.out.println("Marked task as done");
    }

    public void printErrorTaskAlreadyMarked(){
        System.out.println("Task is already marked as done");
    }

    public void printUnmarkedTask(){
        System.out.println("Unmarked task");
    }

    public void printErrorTaskAlreadyUnmarked(){
        System.out.println("Task is already unmarked");
    }

    public void printNumberOfTask(int number){
        System.out.println("Now you have " + number + " in the list.");
    }

    public void printTask(String task){
        System.out.println("  " + task);
    }

    public void printTaskAdded(String task, int number){
        System.out.println("Got it. I've added this task:");
        printTask(task);
        printNumberOfTask(number);
    }

    public void printTaskDeleted(String task, int number) {
        System.out.println("Noted. I've removed this task:");
        printTask(task);
        printNumberOfTask(number);
    }

    public void printError(String errorMessage){
        System.out.println(errorMessage);
    }

    public void printErrorTaskDoesNotExist(String msg){
        System.out.println("Task does not exist: " + msg);
    }

    public void printErrorInvalidTaskNumber(String msg){
        System.out.println("Invalid task number: " + msg);
    }

    public void printErrorDescriptionDateMissing(String msg){
        System.out.println("Description or Date is missing: " + msg);
    }

    public void printErrorInvalidCommand(){
        System.out.println("please enter a valid command");
    }

    public void printErrorInformationMissing(){
        System.out.println("Missing information!");
    }

}