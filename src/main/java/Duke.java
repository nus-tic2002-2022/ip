import java.util.Scanner;

public class Duke 
{
    private static Task[] taskList = new Task[100];
    private static int taskCount = 0;

    public static void addTask(Task item) 
    {
        taskList[taskCount] = item;
        taskCount++;
    }

    public static boolean mark(String input) 
    {
        boolean isDone = false;
        String markTask = input.substring(0, input.indexOf(' ')).trim();
        if (markTask.equals("mark")) 
        {
            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");
            System.out.println("I have mark this task as completed:");
            isDone = true;
        }
        else if (markTask.equals("unmark")) 
        {
            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");
            System.out.println("I have mark this task as incompleted: ");
        }
        return isDone;
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
                for (int i = 0; i < taskCount; i++)
                {
                    System.out.println((i+1) + ": " + "[" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
                }
                System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");
                System.out.println("What can I do for you today?");
                userInput = in.nextLine();
            }

            else if(userInput.contains("mark"))  
            {
                String index = userInput.replaceAll("\\D+",""); 
                int taskIndex = Integer.parseInt(index)-1; 
                if(taskIndex == -1)
                {
                    continue;
                }
                taskList[taskIndex].isDone = mark(userInput);
                System.out.println(" [" + taskList[taskIndex].getStatusIcon() + "] " + taskList[taskIndex].description);
                System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");
                userInput = in.nextLine();
            }

            else
            {
                Task t = new Task(userInput);
                addTask(t);
                System.out.println("A new event has been added: " + userInput);
                System.out.println("What can I do for you today?");
                userInput = in.nextLine();
            }
        }

            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");
            System.out.println("Bye Bye. Enjoy your day and hope to see you again soon!\n");
    }
}