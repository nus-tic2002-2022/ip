import java.util.Arrays;
import java.util.Scanner;


public class Task {
    public static int counter = 0;
    public static int list_order = 1;
    public static Task[] list = new Task[100];

    protected String description;
    protected boolean isDone;



    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString()
    {
        return "[" + getStatusIcon() + "] " + description;
    }

    public static void add_list() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        if (command.toLowerCase().equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (command.toLowerCase().equals("list")) {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");

            for (int i = 0; i < counter; i++) {
                System.out.println(list_order + ". [" + list[i].getStatusIcon() + "] " + list[i].description);
                list_order++;
            }
            System.out.println("____________________________________________________________");
            System.out.println("\n");

            list_order = 1;
            add_list();
        } else if (command.toLowerCase().startsWith("mark")){
            System.out.println("____________________________________________________________" );
            System.out.println("Nice! I've marked this task as done: ");

            int taskOrder = Integer.parseInt(command.split(" ")[1]) - 1;
            list[taskOrder].markAsDone();
            list[taskOrder].printMark();
            add_list();
        }else if (command.toLowerCase().startsWith("unmark")){
            System.out.println("____________________________________________________________" );
            System.out.println("Oh no! I've unmarked this task as not done: ");

            int taskOrder = Integer.parseInt(command.split(" ")[1]) - 1;
            list[taskOrder].markAsNotDone();
            list[taskOrder].printMark();
            add_list();
        }

        else {
            System.out.println("added: " + command + "\n");
            list[counter] = new Task(command);
            counter++;
            add_list();
        }
    }

    public void printMark()
    {
        System.out.println("  [" + getStatusIcon() + "] " + description);
    }

    public void markAsDone()
    {
        this.isDone = true;
    }

    public void markAsNotDone()
    {
        this.isDone = false;
    }
    //...
}

