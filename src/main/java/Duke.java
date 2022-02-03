import java.util.Scanner;
public class Duke {

    //Code Starts here!

    //Aesthetic Function
    public static void printLine() {
        System.out.println("__________________________________________________________");
    }

    //Level-2 Added List Array
    public static void system() {
        String[] memory = new String[100];
        String line;
        int counter= 0;
        Scanner in = new Scanner(System.in);

        //Keep the System Running
        while (true) {
            //Ask User for Input
            line = in.nextLine();

            //Print the List
            if (line.equals("list")) {
                printLine();
                for (int x = 0; x < counter; x++) {
                    System.out.println(x+1 + ". " + memory[x]);
                }
                printLine();
            }
            //End the System
            else if (line.equals("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                System.exit(0);
            }

            //Add to the List
            else {
                memory[counter] = line;
                printLine();
                System.out.println("added: " + line);
                printLine();
                counter = counter + 1;
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        printLine();

        //Trigger Code
        system();
    }
}