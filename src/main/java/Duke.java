import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I'm Duke\n" + "What can I do for you?");

        String input;
        Scanner in = new Scanner(System.in);
        boolean bye = false;



        while (bye == false) {

            input = in.nextLine();

            if(input.toLowerCase().equals("bye"))
            {
                System.out.println("Bye bye!");
                bye = true;
            }
            else {
                System.out.println(input);
            }
        }
    }
}
