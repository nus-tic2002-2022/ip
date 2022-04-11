package duke.UI;

import duke.Exception.DukeException;

import java.util.*;

public class UI {
    /**
     *
     * @return Return String of user input
     * @throws DukeException is throwable if user input does not meet the specification or contains error
     */

    public String readCommand() throws DukeException {
        Scanner scanInput = new Scanner(System.in);
        return scanInput.nextLine();
    }

    /**
     * Print welcome greeting
     */
    public static void showWelcome() {
        System.out.println("Welcome!");
        System.out.println("This is Duke");
        System.out.println("I am here to help check your on-hand task");
    }

    /**
     * Print partition line
     */
    public static void showLine() {
        System.out.println("______________________________________________");
    }

    /**
     *
     * @param num Show the count of task in the list.
     */
    public static void showAdd(int num) {
        if (num < 1) {
            System.out.println("Now you have " + num + " task in the list");
        } else {
            System.out.println("Now you have " + num + " tasks in the list");
        }
    }

    /**
     * Print if 1 task is added into the list
     */
    public static void showGotit() {
        {
            System.out.println("Got it. I've added this task: ");
        }
    }

    /**
     * Print if multiple tasks are added into the list
     */
    public static void showGotthem() {
        {
            System.out.println("Got it. I've added these tasks: ");
        }
    }

    /**
     * Print if certain task is removed
     */
    public static void showDelete() {
        System.out.println("Noted. I've removed this task:");
    }

    /**
     * Print if all task are removed
     */
    public static void showDeleteAll() {
        System.out.println("Noted. I've removed all the tasks:");
    }

    /**
     * Print if certain task is mark as done
     */
    public static void showMark() {
        System.out.println("Nice! I've marked this task as done: ");
    }

    /**
     * Print if certain task is mark as undone
     */
    public static void showUnmark() {
        System.out.println("OK, I've marked this task as not done yet:");
    }

    /**
     *
     * @param count Pass in the number of result found
     * @throws AssertionError is throwable if negative number or non-number is passed in
     */
    public static void showFindResult(int count) throws AssertionError{
        assert (count > 0);
        if(count == 0) {
            System.out.println("No match task is found");
        }
        else if(count == 1) {
            System.out.println("OK, I found this task shown above");
        }
        else{
            System.out.println("OK, I found these tasks shown above");
        }
    }


}

