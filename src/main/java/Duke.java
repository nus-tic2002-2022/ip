import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        String userInput = "notBye";
        ArrayList<String> taskList = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        System.out.println("    ____________________________________________________________");
        System.out.println("    What up! I'm D. Spawn");
        System.out.println("    Something need doing?");
        System.out.println("    ____________________________________________________________");

        do {
            userInput = in.nextLine();
            System.out.println("    ____________________________________________________________");
            System.out.println("    " + userInput); //do stuff here after input
            System.out.println("    ____________________________________________________________");
        } while (!userInput.equals("bye"));

        System.out.println("    Ciao!");
        System.out.println("    ____________________________________________________________");
    }
}
