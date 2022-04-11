import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void hello() {
        System.out.println("Hi! This is Duke.\nWhat can I do for you?");
    }

    public static void echo(String commands) {
        System.out.println("\tadded: " + commands);
    }

    public static void exit() {
        System.out.println("\tBye. See you again!");
    }

    public static void showTask(Task[] tasks) {
        System.out.println("\tHere is the task list:");
        for (int i = 0; i < tasks.length; i++) {
            System.out.println("\t" + (i+1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
    }

    public static int getNumber(String commands) {
        return Integer.parseInt(commands.substring(commands.indexOf(" ")+1, commands.length()));
    }

    public static void markAsDone(Task[] tasks, int taskNumber) {
        tasks[taskNumber-1].isDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t [X] " + tasks[taskNumber-1].description);
    }

    public static void umTask(Task[] tasks, int taskNumber) {
        tasks[taskNumber-1].unmark();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t [ ] " + tasks[taskNumber-1].description);
    }





    public static void main(String[] args){

        Duke.hello();

        String line;
        int counter = 0;
        Task[] list = new Task[100];

        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.toLowerCase().contains("bye")) {
                Duke.exit();
                break;
            }
            if (line.toLowerCase().startsWith("list")) {
                Duke.showTask(Arrays.copyOf(list,counter));
                continue;
            }
            if (line.toLowerCase().startsWith("mark")) {
                Duke.markAsDone(Arrays.copyOf(list,counter),Duke.getNumber(line));
                continue;
            }
            if (line.toLowerCase().startsWith("unmark")) {
                Duke.umTask(Arrays.copyOf(list,counter),Duke.getNumber(line));
                continue;
            }

            list[counter] = new Task(line);
            counter++;

            Duke.echo(line);
        } while (true);

    }
}