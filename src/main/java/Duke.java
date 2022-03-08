import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    // Declarations
    static String line;
    static List<Task> tasks = new ArrayList<Task>();

    public static void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null)
                System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println("------------------------------------------\n");
    }

    public static void unmarkTask() {
        int get_unmark = Integer.parseInt(line.substring(7)) - 1;
        Task unmark_task = tasks.get(get_unmark);
        unmark_task.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[" + unmark_task.getStatusIcon() + "] " + unmark_task.description);
        System.out.println("------------------------------------------\n");
    }

    public static void markTask() throws DukeException {
        int get_mark = Integer.parseInt(line.substring(5)) - 1;
        String toStore = line.substring(5);
        Task mark_task = tasks.get(get_mark);
        if (toStore.equals(""))
            throw  new DukeException("☹ OOPS!!! Please type the number you want to mark.");
        else
        {
            mark_task.markAsDone();
            System.out.println("Nice! I've marked this task as done");
            System.out.println("[" + mark_task.getStatusIcon() + "] " + mark_task.description);
            System.out.println("------------------------------------------\n");
        }

    }

    public static void todoTask() throws DukeException {
        String toStore = line.substring(5);
            Task tsk = new Todo(toStore);
        if (toStore.equals(""))
            throw  new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");

        else{
            tasks.add(tsk);
            System.out.println("Got it. I've added this task:");
            System.out.println(tsk);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("------------------------------------------\n");
        }
    }
    public static void deleteTask() {

        int get_index = Integer.parseInt(line.substring(7)) - 1;
        Task toDelete = tasks.get(get_index);
        tasks.remove(get_index);
        System.out.println("Noted. I've removed this task");
        System.out.println(toDelete.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("------------------------------------------\n");

    }

    public static void eventTask() {
        int buffer = line.indexOf("/");

        // Initiate Event Class
        Task deadline = new Event(line.substring(6, buffer - 1), line.substring(buffer + 4));
        tasks.add(deadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(deadline.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("------------------------------------------\n");
    }

    public static void deadlineTask() {
        int buffer = line.indexOf("/");
        Task deadline = new Deadline(line.substring(9, buffer - 1), line.substring(buffer + 4));
        tasks.add(deadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(deadline.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("------------------------------------------\n");
    }

    public static void byeTask() {
        System.out.println("Bye. Summon me when you need me again!");
        System.out.println("------------------------------------------\n");
        System.exit(0);
    }

    private static void addTask() {

        System.out.println("\tAdded: " + line);
        System.out.println("------------------------------------------\n");

        Task t = new Task(line);
        t.description = line;
        tasks.add(t);
    }
    // Program Start
    public static void start() {

        Scanner in = new Scanner(System.in);
        // Keep running the program as long as user type "Bye"
        while (true) {
            // Scan user input
            line = in.nextLine();
            System.out.println("------------------------------------------\n");
            // List down all the added items when user type "list"
            if (line.equals(Command.LIST.getCommand())) {
                listTask();
            }
            // Mark the task when user type "unmark"
            else if (line.contains(Command.UNMARK.getCommand())) {
                unmarkTask();
            }
            // Mark the task when user type "mark"
            else if (line.contains(Command.MARK.getCommand())) {
                try {
                    markTask();
                } catch (NullPointerException | DukeException e) {
                    System.out.println(e.getMessage());
                    System.out.println("------------------------------------------\n");
                }
            }
            // Set deadline when user type "deadline"
            else if (line.contains(Command.DEADLINE.getCommand())) {
                deadlineTask();
            }
            // Set todo when user type "todo"
            else if (line.contains(Command.TODO.getCommand())) {
                try {
                    todoTask();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    System.out.println("------------------------------------------\n");
                }
            }
            // Set event when user type "event"
            else if (line.contains(Command.EVENT.getCommand())) {
                eventTask();
            }
            //Delete tasks
            else if(line.contains(Command.DELETE.getCommand())){
                deleteTask();
            }
            // End program when user type "bye"
            else if (line.equals(Command.BYE.getCommand())) {
                byeTask();
            }
            // Store all the words and print out
            else {
                addTask();
            }
        }
    }

    // Greeting
    public static void main(String[] args) throws DukeException {
        System.out.println("------------------------------------------\n");
        System.out.println("Hello Mater! I'm Gennie ^_^\n" + "     What can I do for you?");
        System.out.println("------------------------------------------\n");
        start();
    }
}