import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        String line;
        Scanner in = new Scanner(System.in);
        int status = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        do {

            System.out.print("What can I do for you? ");

            line = in.nextLine();

            if(line.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                status = 1; // can escape
            } else {
                System.out.println(line);
            }

        } while (status == 0);

    }
}
