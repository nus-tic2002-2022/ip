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
        System.out.println("Hello! I'm Gennie ^_^\n" +
                "     What can I do for you?");
        System.out.println("------------------------------------------\n");
        String line;
        Scanner in = new Scanner(System.in);
        while (true)
        {
            line = in.nextLine();
            System.out.println("------------------------------------------\n");
            System.out.println(line);
            if (line.equals("bye") || line.equals("Bye"))
                System.out.println("Hope to see you again soon!");
            if (line.equals("bye")|| line.equals("Bye"))
                break;
        }
    }
}
