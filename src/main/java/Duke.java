import java.util.Scanner;
public class Duke {

    //Code Starts here!
    //Level-1
    public static void printLine() {
        System.out.println("__________________________________________________________");
    }

    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        printLine();

        if (line.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }

        else {
            System.out.println(line);
            printLine();
            echo();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        //Trigger Code
        echo();
    }
}