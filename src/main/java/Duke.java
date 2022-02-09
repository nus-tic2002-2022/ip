import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("______________________________________________");
        printWithLine(List.of("Hello! I'm Duke", "What can I do for you?"));

        String line;
        Scanner in = new Scanner(System.in);
        List<Task> item = new ArrayList<>();

        do {
            line = in.nextLine();

            if (line.equals("list")) {
                printWithLine(List.of());
                List<String> messages = new ArrayList<>();
                System.out.println("   Here are the tasks in your list: ");
                for (int i = 0; i < item.size(); i++) {
                    messages.add(i + 1 + "." + item.get(i));
                }
                printWithLine(messages);
            } else if (line.split(" ")[0].equals("done")) {
                printWithLine(List.of());
                Task markItem = item.get(Integer.parseInt(line.substring(5)) - 1);
                markItem.markAsDone();
                printWithLine(List.of("Nice! I've marked this task as done: ", " " + markItem));


            } else if (!line.equals("bye")) {
                Task task = new Task(line);
                item.add(task);
                printWithLine(List.of());
                printWithLine(List.of("added: " + task));
            }
        }
        while (!line.equals("bye"));
        printWithLine(List.of());
        printWithLine(List.of("Bye. Hope to see you again soon!"));
    }

            private static void printWithLine (List < String > messages) {
                for (String message : messages) {
                    System.out.println("   " + message);
                }
                System.out.println("______________________________________________");
            }



    }


