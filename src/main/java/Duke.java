import java.util.Scanner;

public class Duke {

    
    public static void main(String[] args) {

        String line;
        Scanner in = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.print("Hello! I'm Duke\n" +
                "What can I do for you?");

        line = in.nextLine();

        if(line.toLowerCase().equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else {
            System.out.println(line);
        }

    }
}
