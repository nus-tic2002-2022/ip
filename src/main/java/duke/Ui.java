/**
 * Creates a Storage object.
 */
package duke;
import java.util.*;

public class Ui {

    private static Scanner in;
    private static ArrayList<Task> list = new ArrayList<>(); // ArrayList of Tasks

    //int index = 0;

    public Ui () { this.in = new Scanner(System.in); }

    public static String getLine(){
        return in.nextLine();
    }

    public static void printHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void printPrompt() {
        System.out.print("What can I do for you? (type 'commands' if you need help to remember the commands) ");
    }

    public static void printCommand() {
        String command = "Here are the commands you can type: \n"
                + "1. type 'bye' to escape\n"
                + "2. type 'list' to check your list\n"
                + "3. type 'todo <add task here>' (e.g. todo read a book)\n"
                + "4. type 'deadline <add task here> /by <add deadline  in yyyy-MM-dd'T'HH:mm>' (e.g. deadline submit duke project /by 2022-04-11T23:59)\n"
                + "5. type 'event <add task here> /at <add event timing in yyyy-MM-dd'T'HH:mm>' (e.g. event attend TIC2002 class /at 2022-03-11T19:00)\n";
        System.out.println(command);
    }

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

    public static void printBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }



}
