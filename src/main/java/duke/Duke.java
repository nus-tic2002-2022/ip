package duke;

import duke.data.exception.DukeException;
import duke.data.entity.Task;
import duke.commands.Commands;
import duke.data.entity.Deadline;
import duke.data.entity.Event;
import duke.data.entity.Todo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    //Declarations
    static String line;
    static List<Task> tasks = new ArrayList<Task>();

    //Print Line
    public static void printLine() {
        System.out.println("\t------------------------------------------\n");
    }

    //Print Line with Message
    public static void printLine(String message) {
        System.out.println("\t" + message);
    }

    //Function to list all the tasks
    public static void listTask() {
        //Check if there are tasks in the list or not
        if (tasks.size() == 0) {
            printLine("There is no tasks in your list.");
            printLine();
        }
        //Print the list
        else {
            printLine("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i) != null)
                    printLine((i + 1) + "." + tasks.get(i).toString());
            }
            printLine();
        }
    }

    //Function to unmark the tasks
    public static void unmarkTask() throws DukeException {
        //Check if user key in empty number
        if (line.trim().equals(Commands.UNMARK.getCommand()))
            throw new DukeException("☹ OOPS!!! The index that want to unmark cannot be empty.");

        int get_unmark = Integer.parseInt(line.substring(7)) - 1;
        //Check if user key in invalid number
        if (get_unmark <= 0 || get_unmark > tasks.size())
            throw new DukeException("☹ OOPS!!! The index that want to unmark is not in the list.");
            //Else unmark the task
        else {
            Task unmark_task = tasks.get(get_unmark);
            //check if the task that user want to unmark is marked or not
            if (unmark_task.getStatusIcon().equals(" "))
                throw new DukeException("☹ OOPS!!! This task is not marked yet.");
            unmark_task.markAsNotDone();
            printLine("OK, I've marked this task as not done yet:");
            printLine("[" + unmark_task.getStatusIcon() + "] " + unmark_task.getDescription());
            printLine();
        }
    }

    //Function to mark the tasks
    public static void markTask() throws DukeException {
        //Check if user key empty number
        if (line.trim().equals(Commands.MARK.getCommand()))
            throw new DukeException("☹ OOPS!!! The index that want to mark cannot be empty.");

        int get_mark = Integer.parseInt(line.substring(5)) - 1;

        //Check if user key in invalid number
        if (get_mark <= 0 || get_mark > tasks.size())
            throw new DukeException("☹ OOPS!!! The index that want to mark is not in the list.");
            //Else unmark the task
        else {
            Task mark_task = tasks.get(get_mark);
            mark_task.markAsDone();
            printLine("Nice! I've marked this task as done");
            printLine("[" + mark_task.getStatusIcon() + "] " + mark_task.getDescription());
            printLine();
        }
    }

    //Function to add todo tasks to list
    public static void todoTask() throws DukeException {
        //Check whether todo description is empty or not
        if (line.trim().equals(Commands.TODO.getCommand()))
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        //Else add todo tasks to list
        Task tsk = new Todo(line.substring(5));
        tasks.add(tsk);
        printLine("Got it. I've added this task:");
        printLine(tsk.toString());
        printLine("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }

    //Function to delete tasks
    public static void deleteTask() throws DukeException {
        //Check whether user key in empty number or not
        if (line.trim().equals(Commands.DELETE.getCommand()))
            throw new DukeException("☹ OOPS!!! The index that want to delete cannot be empty.");

        int get_index = Integer.parseInt(line.substring(7)) - 1;
        //Check if user key in invalid number
        if (get_index < 0 || get_index > tasks.size()) {
            throw new DukeException("☹ OOPS!!! There is no such task to delete.");
        }
        //Else delete the task
        Task toDelete = tasks.get(get_index);
        tasks.remove(get_index);
        printLine("Noted. I've removed this task.");
        printLine(toDelete.toString());
        printLine("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }

    //Function to add event tasks to list
    public static void eventTask() throws DukeException {
        //Check if the event description is empty or not
        if (line.trim().equals(Commands.EVENT.getCommand()))
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        //Find the position of "/"
        int buffer = line.indexOf("/");

        String eventName = line.substring(6, buffer - 1);
        String date = line.substring(buffer + 4);
        //Check if user key in empty event description or event date
        if (eventName.equals("") || date.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of an event or date of the event cannot be empty.");
        }
        //Else add event tasks to list
        Task event = new Event(eventName, date);
        tasks.add(event);
        printLine("Got it. I've added this task:");
        printLine(event.toString());
        printLine("Now you have " + tasks.size() + " tasks in the list.");
        printLine("------------------------------------------\n");

    }

    //Function to add deadline tasks to list
    public static void deadlineTask() throws DukeException {
        //Check if the deadline description is empty or not
        if (line.trim().equals(Commands.DEADLINE.getCommand()))
            throw new DukeException("☹ OOPS!!! The description of an deadline cannot be empty.");
        //Find the position of "/"
        int buffer = line.indexOf("/");

        String deadlineName = line.substring(9, buffer - 1);
        String date = line.substring(buffer + 4);
        //Check if user key in empty deadline description or deadline date
        if (deadlineName.equals("") || date.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of an deadline or date of the deadline cannot be empty.");
        }
        //Else add deadline tasks to list
        Task deadline = new Deadline(deadlineName, date);
        tasks.add(deadline);
        printLine("Got it. I've added this task:");
        printLine(deadline.toString());
        printLine("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }

    //Function to exit program if user type "bye"
    public static void byeTask() {
        printLine("Bye. Summon me when you need me again!");
        printLine();
        System.exit(0);
    }

    //Function for invalid command
    public static void errorTask() throws DukeException {
        throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-( ");
    }

    // Program Start
    public static void start() {

        Scanner in = new Scanner(System.in);
        // Keep running the program as long as user type "Bye"
        while (true) {
            // Scan user input
            line = in.nextLine();
            printLine();
            // List down all the added items when user type "list"
            if (line.trim().equals(Commands.LIST.getCommand())) {
                listTask();
            }
            // Mark the task when user type "unmark"
            else if (line.contains(Commands.UNMARK.getCommand())) {
                try {
                    unmarkTask();
                } catch (NullPointerException | DukeException e) {
                    printLine(e.getMessage());
                    printLine();
                }
            }
            // Mark the task when user type "mark"
            else if (line.contains(Commands.MARK.getCommand())) {
                try {
                    markTask();
                } catch (NullPointerException | DukeException e) {
                    printLine(e.getMessage());
                    printLine();
                }
            }
            // Set deadline when user type "deadline"
            else if (line.contains(Commands.DEADLINE.getCommand())) {
                try {
                    deadlineTask();
                } catch (DukeException e) {
                    printLine(e.getMessage());
                    printLine();
                }
            }
            // Set todo when user type "todo"
            else if (line.contains(Commands.TODO.getCommand())) {
                try {
                    todoTask();
                } catch (DukeException e) {
                    printLine(e.getMessage());
                    printLine();
                }
            }
            // Set event when user type "event"
            else if (line.contains(Commands.EVENT.getCommand())) {
                try {
                    eventTask();
                } catch (DukeException e) {
                    printLine(e.getMessage());
                    printLine();
                }
            }
            //Delete tasks
            else if (line.contains(Commands.DELETE.getCommand())) {
                try {
                    deleteTask();
                } catch (DukeException e) {
                    printLine(e.getMessage());
                    printLine();
                }
            }
            // End program when user type "bye"
            else if (line.trim().equals(Commands.BYE.getCommand())) {
                byeTask();
            }
            // no such command - error
            else {
                try {
                    errorTask();
                } catch (DukeException e) {
                    printLine(e.getMessage());
                    printLine();
                }
            }
        }
    }

    // Greeting
    public static void main(String[] args) {
        printLine();
        printLine("Hello Mater! I'm Gennie ^_^");
        printLine("What can I do for you?");
        printLine();
        start();
    }
}