import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String line = "";
        Scanner in = new Scanner(System.in);

        while(!line.contains("bye")) {
            System.out.print("Type something: ");
            line = in.nextLine();
            System.out.println("\tYou said: " + line);

            if (line.equals("bye")){
                System.out.print("\tGood day and good bye");
                break;
            }
        }
    }
}
