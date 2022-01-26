import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void greet() {
        String line;
        String[] list = new String[100];

        int count = 0;

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.toLowerCase().contains("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            }
            if (line.toLowerCase().contains("list")) {
                for (String item : Arrays.copyOf(list,count)) {
                    System.out.println("\t"+item);
                }
                continue;
            }
            System.out.println("\tadded: " + line);
            list[count] = ++count + ". " + line;
        } while (true);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke.greet();
    }
}
