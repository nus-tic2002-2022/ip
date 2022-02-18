import java.util.Scanner;

public class Duke
{
    //Program Start
    public static void start()
    {
        //Declarations
        String line;
        String[] store = new String [100];
        int storeWords = 0;
        Scanner in = new Scanner(System.in);
        //Keep running the program as long as user type "Bye"
        while (true)
        {
            //Scan user input
            line = in.nextLine();
            System.out.println("------------------------------------------\n");
            //List down all the added items when user type "list"
            if (line.equals("list"))
            {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < store.length; i++)
                {
                    if (store[i] != null)
                        System.out.println((i + 1) + "." + " [ ] " + store[i]);
                }
                System.out.println("------------------------------------------\n");
                continue;
            }
            //Store all the words and print out
            else
            {
                store[storeWords++] = line;
                System.out.println("\tAdded: " + line);
                System.out.println("------------------------------------------\n");
            }
        }
    }
    //Greeting
    public static void main(String[] args)
    {
        System.out.println("------------------------------------------\n");
        System.out.println("Hello Mater! I'm Gennie ^_^\n" +
                "     What can I do for you?");
        System.out.println("------------------------------------------\n");
        start();
    }
}