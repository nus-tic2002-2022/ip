import java.util.*;

public class Duke {

    public static void main(String[] args) {

        String line;
        Scanner in = new Scanner(System.in);
        int status = 0;
        //String[] list = new String[100];
        int counter = 0;
        //Task task;
        Task task = new Task();
        int index = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        do {

            System.out.print("What can I do for you? ");

            line = in.nextLine();

            if(line.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                status = 1; // can escape
            } else if(line.toLowerCase().equals("list")){
                task.getTodoList(); //Print list
                continue;
            } else if (line.startsWith("todo ")) {
                task.addTask(line);
                task.getNewTask(); //System.out.println("added: " + line);
                counter += 1;
                continue;
            } else if (line.startsWith("mark ")) {
                index = Integer.parseInt(line.substring(line.indexOf("mark ") + 5, line.length()));
                task.markAsDone(index);
                continue;
            } else if (line.startsWith("unmark ")) {
                index = Integer.parseInt(line.substring(line.indexOf("unmark ") + 7, line.length()));
                task.markAsNotDone(index);
                continue;
            }
            else {
                //list[counter] = line;
                //task.addTask(line);
                //System.out.println("added: " + line);
                System.out.println("Sorry, I don't understand. Please try the following commands:");
                System.out.println("bye, list, todo <add task here>");
            }

        } while (status == 0);

    }
}
