import java.util.Scanner;

public class Duke 
{
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");
        System.out.println("Hello I am Duke\nWhat can I do for you today?");
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");

        Scanner in = new Scanner(System.in);
        String userInput;
        userInput = in.nextLine();

        while(!userInput.equals("Bye"))
        {
            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");
            System.out.println(userInput);
            userInput = in.nextLine(); 
        }

        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");
        System.out.println("Bye Bye. Enjoy your day and hope to see you again soon!\n");
    }
}
