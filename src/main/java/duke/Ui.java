package duke;

import java.util.Scanner;

import static duke.CommandList.help;
import static duke.Parser.formatFirstWord;
import static duke.Storage.readFile;
import static duke.TaskList.*;

public class Ui {

    /**
     * Main Program
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");
        System.out.println("Hello I am Duke\nWhat can I do for you today?\nType Help for a list of accepted Commands");
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");

        Scanner in = new Scanner(System.in);
        String userInput;
        readFile();
        userInput = in.nextLine();
        userInput = formatFirstWord(userInput);

        while (!userInput.equals("bye")) {
            try {
                if (userInput.equals("list")) {
                    System.out.println("Below are the list of tasks needed to be complete:\n");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ": " + taskList.get(i).toString());
                    }
                    System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");
                    System.out.println("What can I do for you today?");
                } else if (userInput.contains("mark")) {
                    String index = userInput.replaceAll("\\D+", "");
                    int taskIndex = Integer.parseInt(index) - 1;
                    if (taskIndex == -1) {
                        continue;
                    }
                    mark(userInput, taskIndex);
                    System.out.println(taskList.get(taskIndex).toString());
                    System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");
                } else if (userInput.contains("todo")) {
                    if (userInput.split(" ", 2).length < 2) {
                        throw new DukeException();
                    }
                    ToDo t = new ToDo(userInput.split(" ", 2)[1]);
                    addToDoTask(t);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(t.toString());
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else if (userInput.contains("deadline")) {
                    if (userInput.split(" /by ").length < 2) {
                        throw new DukeException();
                    }
                    String[] temp = userInput.split(" /by ");
                    Deadline t = new Deadline(temp[0].substring(9), temp[1]);
                    addDeadlineTask(t);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(t.toString());
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else if (userInput.contains("event")) {
                    if (userInput.split(" /at ").length < 2) {
                        throw new DukeException();
                    }
                    String[] temp = userInput.split(" /at ");
                    Event t = new Event(temp[0].substring(6), temp[1]);
                    addEventTask(t);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(t.toString());
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else if (userInput.contains("doafter")) {
                    if (userInput.split(" /after ").length < 2) {
                        throw new DukeException();
                    }
                    String[] temp = userInput.split(" /after ");
                    DoAfter t = new DoAfter(temp[0].substring(8), temp[1]);
                    addDoAfterTask(t);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(t.toString());
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else if (userInput.contains("delete")) {
                    if (userInput.split(" ", 2).length < 2) {
                        throw new DukeException();
                    }
                    removeTask(Integer.parseInt(userInput.split(" ", 2)[1]) - 1);
                } else if (userInput.contains("find")) {
                    if (userInput.split(" ",2).length < 2) {
                        throw new DukeException();
                    }
                    System.out.println("Here are the matching tasks in your list: ");
                    findTask(userInput.split(" ", 2)[1]);
                    System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");
                    System.out.println("What can I do for you today?");
                } else if (userInput.contains("remind")) {
                    if (userInput.split(" ", 2).length < 2) {
                        throw new DukeException();
                    }
                    System.out.println("Here are the matching tasks in your list: ");
                    remindTask(Integer.parseInt(userInput.split(" ", 2)[1]));
                    System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");
                    System.out.println("What can I do for you today?");
                } else if (userInput.contains("help")) {
                    help();
                } else {
                    throw new DukeException();
                }
                userInput = in.nextLine();
                userInput = formatFirstWord(userInput);
            } catch (DukeException e) {
                System.out.println("OOPS!!! I'm sorry, but I do not know what that means :-(");
                userInput = in.nextLine();
                userInput = formatFirstWord(userInput);
            }
        }
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");
        System.out.println("Bye Bye. Enjoy your day and hope to see you again soon!\n");
    }
}
