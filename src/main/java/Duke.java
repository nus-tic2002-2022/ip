import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void greet() {
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");
    }

    public static void echo(String commands) {
        System.out.println("\tadded: " + commands);
    }

    public static void exit() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public static void displayTask(Task[] tasks) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.length; i++) {
            System.out.println("\t" + (i+1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
    }

    public static int getTaskNumber(String commands) {
        return Integer.parseInt(commands.substring(commands.indexOf(" ")+1, commands.length()));
    }

    public static void markTaskAsDone(Task[] tasks, int taskNumber) {
        tasks[taskNumber-1].markAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t [X] " + tasks[taskNumber-1].description);
    }

    public static void unmarkTask(Task[] tasks, int taskNumber) {
        tasks[taskNumber-1].unmark();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t [ ] " + tasks[taskNumber-1].description);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        String line;
        int taskCount = 0;
        Task[] tasks = new Task[100];

        Duke.greet();

        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.toLowerCase().contains("bye")) {
                Duke.exit();
                break;
            }
            if (line.toLowerCase().startsWith("list")) {
                Duke.displayTask(Arrays.copyOf(tasks,taskCount));
                continue;
            }
            if (line.toLowerCase().startsWith("mark")) {
                Duke.markTaskAsDone(Arrays.copyOf(tasks,taskCount),Duke.getTaskNumber(line));
                continue;
            }
            if (line.toLowerCase().startsWith("unmark")) {
                Duke.unmarkTask(Arrays.copyOf(tasks,taskCount),Duke.getTaskNumber(line));
                continue;
            }
            // create and save tasks to Task[]
            tasks[taskCount] = new Task(line);
            taskCount++;

            Duke.echo(line);
        } while (true);

    }
}
