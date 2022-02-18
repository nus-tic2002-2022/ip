import java.util.Scanner;

public class Duke
{
    //Program Start
    public static void start()
    {
        //Declarations
        String line;
        Task[] tasks = new Task [100];
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
                for (int i = 0; i < tasks.length; i++)
                {
                    if (tasks[i] != null)
                        System.out.println((i + 1) + "."  + tasks[i].toString());
                }
                System.out.println("------------------------------------------\n");
            }
            //Mark the task when user type "unmark"
            else if (line.contains("unmark"))
            {
                int get_unmark = Integer.parseInt(line.substring(7)) - 1;
                Task unmark_task = tasks[get_unmark];
                unmark_task.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + unmark_task.getStatusIcon() + "] " + unmark_task.description);
                System.out.println("------------------------------------------\n");
            }
            //Mark the task when user type "mark"
            else if (line.contains("mark"))
            {
                int get_mark = Integer.parseInt(line.substring(5)) - 1;
                Task mark_task = tasks[get_mark];
                mark_task.markAsDone();
                System.out.println("Nice! I've marked this task as done");
                System.out.println( "[" + mark_task.getStatusIcon() + "] " + mark_task.description);
                System.out.println("------------------------------------------\n");
            }
            //Set deadline when user type "deadline"
            else if (line.contains("deadline"))
            {
                int buffer = line.indexOf("/");
                tasks[storeWords] = new Deadline(line.substring(9, buffer-1),line.substring(buffer+4));
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[storeWords++].toString());
                System.out.println("Now you have " + (storeWords) + " tasks in the list.");
                System.out.println("------------------------------------------\n");
            }
            //Set todo when user type "todo"
            else if (line.contains("todo"))
            {
                tasks[storeWords] = new Todo(line.substring(5));
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[storeWords++].toString());
                System.out.println("Now you have " + (storeWords) + " tasks in the list.");
                System.out.println("------------------------------------------\n");
            }
            //Set event when user type "event"
            else if (line.contains("event"))
            {
                int buffer = line.indexOf("/");

                //Initiate Event Class
                tasks[storeWords] = new Deadline(line.substring(6, buffer-1),line.substring(buffer+4));
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[storeWords++].toString());
                System.out.println("Now you have " + (storeWords) + " tasks in the list.");
                System.out.println("------------------------------------------\n");
            }
            //End program when user type "bye"
            else if (line.equals("bye"))
            {
                System.out.println("Bye. Summon me when you need me again!");
                System.out.println("------------------------------------------\n");
                System.exit(0);
            }
            //Store all the words and print out
            else
            {
                System.out.println("\tAdded: " + line);
                System.out.println("------------------------------------------\n");

                Task t = new Task(line);
                t.description = line;
                tasks[storeWords++] = t;
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