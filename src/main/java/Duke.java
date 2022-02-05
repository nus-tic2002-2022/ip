import java.util.Arrays;
import java.util.Scanner;

public class Duke
{
    public static void main(String[] args)
    {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("------------------------------------------\n");
        tasks();
    }
    public static void tasks()
    {
        System.out.println("Hello Mater! I'm Gennie ^_^\n" +
                "     What can I do for you?");
        System.out.println("------------------------------------------\n");
        String line;
        String[] store = new String [100];
        int storeWords = 0;
        Scanner in = new Scanner(System.in);
        while (true)
        {
            line = in.nextLine();
            System.out.println("------------------------------------------\n");
            if (line.equals("bye") || line.equals("Bye"))
                System.out.println("Summon me anytime if you need me! Bye ~ ");
            if (line.equals("bye")|| line.equals("Bye"))
                break;
            if (line.equals("list"))
            {
                for (int i = 0; i < store.length; i++)
                {
                    if (store[i] != null)
                    System.out.println((i + 1) + "." + store[i]);
                }
                System.out.println("------------------------------------------\n");
                continue;
            }
            store[storeWords++] = line;
            System.out.println("\tAdded: " + line);
            System.out.println("------------------------------------------\n");
        }
    }
}
