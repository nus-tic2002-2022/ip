import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void printIntroduction() {
        System.out.println("  (\\_/)");
        System.out.println("  (^_^)");
        System.out.println("  (____)0");
        System.out.println("\tHey how's it going? I'm Bugs, a transient robotic bunny.");
        System.out.println("\tHow may I help? (I take carrots as payment)");
    }
    public static void chatting() {
        printIntroduction();

        String[] tasks = new String[100];
        int taskList = 0;

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
                System.out.println("\t" + Arrays.toString(Arrays.copyOf(tasks,taskList)));
                continue;
            }
            tasks[taskList] = response;
            taskList ++;
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
