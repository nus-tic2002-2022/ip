/**
 * Ui handles user's commands and prints messages to user in Duke.
 *
 */
package duke;
import java.util.Scanner;
import java.util.ArrayList;

public class Ui {

    private static Scanner in;
    private static ArrayList<Task> list = new ArrayList<>(); // ArrayList of Tasks

    public Ui () { this.in = new Scanner(System.in); }

    public static String getLine(){
        return in.nextLine();
    }

    //Print general messages

    public static void printHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void printBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printPrompt() {
        System.out.print("What can I do for you? (type 'help' if you need help to remember the commands) ");
    }

    public static void printHelp() {
        String help = "Here are the commands you can type: \n"
                + "1. type 'bye' to escape\n"
                + "2. type 'list' to check your list\n"
                + "3. type 'todo <add task here>' (e.g. todo read a book) [todo is assumed as today's date.]\n"
                + "4. type 'deadline <add task here> /by <add deadline  in yyyy-MM-dd'T'HH:mm>' (e.g. deadline submit duke project /by 2022-04-11T23:59)\n"
                + "5. type 'event <add task here> /at <add event timing in yyyy-MM-dd'T'HH:mm>' (e.g. event attend TIC2002 class /at 2022-03-11T19:00)";
        System.out.println(help);
    }

    //Print helpful messages after command

    public static void printAddTaskMsg() {
        System.out.println("Nice! I've added this task: ");
    }

    public static void printMarkDoneMsg() {
        System.out.println("Nice! I've marked this task as done: ");
    }

    public static void printMarkNotDoneMsg() {
        System.out.println("OK, I've marked this task as not done yet: ");
    }

    public static void printDeleteMsg() {
        System.out.println("Noted. I've removed this task: ");
    }

    public static void printSortMsg() {
        System.out.println("Noted. I've sorted the list by date: ");
    }

    public static void printFindMsg() {
        System.out.println("Here are the matching task(s) in your list: ");
    }

    public static void printNothingInListMsg() { System.out.println("There is nothing on the list! :)"); }

    //Print error messages

    public static void printOutOfRangeMsg() {
        System.out.println("Sorry, you have chosen the item number you choose to mark is out of range. Please type 'list' to check the number of tasks you have.");
    }

    public static void printBlankDescMsg() {
        System.out.println("Sorry, description cannot be blank. Type 'help' to check the format for Todo/Deadlines/Events.");
    }

    public static void printMarkDoneBeforeMsg() {
        System.out.println("This task has been marked done before!");
    }

    public static void printUnknownCommandMsg() {
        System.out.print("Sorry, I don't understand. ");
    }

    public static void printFileErrorMsg() {
        System.out.println("The list file (duke.txt) is not found and/or cannot be created/saved.");
    }

    public static void printDateFormatErrorMsg() {
        System.out.println("The date format is not correct. Please type 'help' to check the date format for Events/Deadlines.");
    }

    public static void printShortDescErrorMsg() {
        System.out.println("The description is too short. Please type 'help' to check the date format for Events/Deadlines.");
    }

}
