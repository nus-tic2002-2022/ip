import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static int taskListCount = 0;

    public static void printIntroduction() {
        System.out.println("  (\\_/)");
        System.out.println("  (^_^)");
        System.out.println("  (____)0");
        System.out.println("\tHey how's it going? I'm Bugs, a transient robotic bunny.");
        System.out.println("\tHow may I help? (I take carrots as payment)");
    }

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0 ; i < taskListCount ; i ++) {
            System.out.println( (i+1)+"."+tasks[i] ); //+1 to i here due to numbering
        }
    }

    public static void chatting() {
        printIntroduction();

        Scanner input = new Scanner(System.in);
        String response;

        while(true) {
            System.out.println("\tWhat's up doc?");
            response = input.nextLine();
            if (response.equals("bye")) {
                System.out.println("\tAlready? :( Ok then... Cya...");
                return;
            }

            if (response.equals("list")) {
                printList();
                continue;
            }

            if ( response.contains("mark") ) {
                if ( response.contains("unmark")) {
                    int taskToChange = Integer.parseInt(response.split(" ")[1]);
                    tasks[taskToChange].setDone(false);
                    System.out.println(tasks[taskToChange].getTask() + "set to undone.");
                    continue;
                }
                int taskToChange = Integer.parseInt(response.split(" ")[1]);
                tasks[taskToChange].setDone(true);
                System.out.println(tasks[taskToChange - 1].getTask() + " set to done.");
                continue;
            }
            //bugs 2 - can't mark just "mark"

            tasks[taskListCount] = new Task(response);
            taskListCount ++;
            System.out.println("\tAdded: " + response);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        chatting();
        return;
    }
}
