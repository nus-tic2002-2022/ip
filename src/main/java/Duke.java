import java.util.Scanner;
import java.util.ArrayList;
import exceptions.InvalidTaskException;
import exceptions.UnknownCommandException;

public class Duke {

    private static Task[] taskList = new Task[100];
    private static int taskCount = 0;

    public static void bye() {
        System.out.println("Bye bye!");
        System.exit(0);
    }

    private static void list(Task[] taskList, int taskCount) {
        if (taskCount > 0) {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(i + 1 + "." + taskList[i]);
            }
        }
        else {
            System.out.println("No tasks in your list.");
        }
    }

    private static void mark(String input, int taskIndex){

        String markTask = input.substring(0, input.indexOf(' ')).trim();
        boolean success;
        try {
            if (taskIndex<1) throw new UnknownCommandException("List is empty.");
            if (taskCount<taskIndex) throw new UnknownCommandException("Selected task to mark is invalid.");
            taskIndex = taskIndex-1;
            if (markTask.equals("mark")) {
                success = taskList[taskIndex].markAsDone();
                if (success) {
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(taskList[taskIndex]);
                }
                else {
                    System.out.println("Task is already marked as done: ");
                    System.out.println(taskList[taskIndex]);
                }
            }
            else if (markTask.equals("unmark")) {
                success = taskList[taskIndex].markAsUndone();
                if (success) {
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println(taskList[taskIndex]);
                }
                else {
                    System.out.println("Task is already marked as not done: ");
                    System.out.println(taskList[taskIndex]);
                }
            }
        }
        catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void addItem(String type, String input){
        try {
            if (type.equals("todo")) {
                if (input.isEmpty()) throw new InvalidTaskException("Todo cannot be empty.");
                taskList[taskCount] = new Todo(input);
                taskCount++;
            }
            else if (type.equals("event")){
                if (!input.contains("/at")) throw new InvalidTaskException("Use the correct command (event DESCRIPTION /at EVENT_DATE).");
                String[] task = input.split("/at");
                if (task[0].isEmpty()) throw new InvalidTaskException("Include description (event DESCRIPTION /at EVENT_DATE).");
                if (task.length != 2) throw new InvalidTaskException("Include description and event date (event DESCRIPTION /at EVENT_DATE).");
                taskList[taskCount] = new Event(task[0], task[1]);
                taskCount++;
            }
            else if (type.equals("deadline")){
                if (!input.contains("/by")) throw new InvalidTaskException("Use the correct command (deadline DESCRIPTION /by DEADLINE).");
                String[] task = input.split("/by");
                if (task[0].isEmpty()) throw new InvalidTaskException("Include description (deadline DESCRIPTION /by DEADLINE).");
                if (task.length != 2) throw new InvalidTaskException("Include deadline (deadline DESCRIPTION /by DEADLINE).");
                taskList[taskCount] = new Event(task[0], task[1]);
                taskCount++;
            }
            System.out.println("added this task: " + taskList[taskCount-1]);
            System.out.println("you have " + taskCount + " tasks in your list.");
        }
        catch (InvalidTaskException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void processInput(String input) throws UnknownCommandException {

        if (input.startsWith("bye")) {
            bye();
        }
        else if (input.startsWith("list")) {
            list(taskList, taskCount);
        }
        else if (input.startsWith("todo")) {
            addItem("todo", input.replaceFirst("todo", "").trim());
        }
        else if (input.startsWith("event")) {
            addItem("event", input.replaceFirst("event", "").trim());
        }
        else if (input.startsWith("deadline")) {
            addItem("deadline", input.replaceFirst("deadline", "").trim());
        }
        else if (input.startsWith("mark") || input.startsWith("unmark")) {
            String index = input.replaceAll("\\D+","");
            mark(input, Integer.parseInt(index));
        }
        else {
            throw new UnknownCommandException();
        }
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I'm Duke\n" + "What can I do for you?");

        String input;
        Scanner in = new Scanner(System.in);

        while (true) {
            input = in.nextLine();

            try {
                processInput(input);
            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
