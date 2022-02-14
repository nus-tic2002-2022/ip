import java.util.Scanner;

public class Duke 
{
    private static String[] event = new String[100];
    private static int eventCount = 0;

    public static void addEvent(String item) 
    {
        event[eventCount] = item;
        eventCount++;
    }

    public static void main(String[] args) 
    {
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
            if(userInput.equals("List"))
            {
                System.out.println("Below are the list of events needed to be complete:\n");
                for (int i = 0; i < eventCount; i++)
                {
                    System.out.println((i+1) + ": " + event[i]);
                }
                System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");
                System.out.println("What can I do for you today?");
                userInput = in.nextLine();
                continue;
            }
                addEvent(userInput);
                System.out.println("A new event has been added: " + userInput);
                System.out.println("What can I do for you today?");
                userInput = in.nextLine();
        }

            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");
            System.out.println("Bye Bye. Enjoy your day and hope to see you again soon!\n");
    }
}