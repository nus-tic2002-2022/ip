import java.util.Scanner;

public class Duke {

    public static void greet() {
        String line;

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.toUpperCase().contains("BYE")) {
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            }
            System.out.println("\t" + line);
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
