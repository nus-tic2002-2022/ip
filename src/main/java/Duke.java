import java.util.Scanner;

public class Duke {

    // separating line function:
    public static void separatingLine() {
        System.out.println("__________________________________________________________");
    }

    public static void system() {

        String line;

        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        separatingLine();

        if (line.equals("bye")) {
            System.out.println("Bye. Glad to be of service to you!\n" +
                    "Hope to assist you again soon!");
        }

        else {
            System.out.println(line);
            separatingLine();
            system();
        }

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        separatingLine();
        System.out.println("Hello! This is Duke here!\n" +
                "Your trusted personal task assistant!\n" +
                "What can I do to assist you today?");
        separatingLine();

        // perform system logic
        system();

    }
    
}
