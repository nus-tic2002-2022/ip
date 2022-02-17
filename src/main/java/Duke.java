import java.time.LocalTime;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String line = "____________________________________________________________";
        System.out.println(line);
        LocalTime now = LocalTime.now();
        int hours = now.getHour();
        TaskList tl = new TaskList();

        String greeting;
        String greeting2 = null;

        if (hours > 0 && hours < 12) {
            greeting = "Good Morning!";
            greeting2 = "Bye! Have a nice day.";
        } else if (hours <= 16) {
            greeting = "Good Afternoon!";
            greeting2 = "Bye! Have a nice day.";
        } else if (hours <= 21) {
            greeting = "Good Evening!";
            greeting2 = "Bye! Good Night.";
        } else {
            greeting = "Good Night!";
        }
        System.out.println(greeting + " I'm Duke" +System.lineSeparator()+ "What can I do for you?");

        System.out.println(line);

        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("You: ");
            String Enter;
            Enter = in.nextLine();
            // System.out.println("Duke: " + Enter);
            if (Enter.equals("bye")) {
                System.out.println(line);
                System.out.println(greeting2 + System.lineSeparator() + " Hope to see you again soon!");
                System.out.println(line);
                break;
            }

            // add todo
            else if (Enter.contains("todo")) {
                Enter = Enter.replace("todo", "");
                String [] str_list = Enter.split(" ");
                StringBuilder content = new StringBuilder(" ");
                for (String s : str_list) {
                    content.append(s);
                }
                tl.addToDo(content.toString());
            }
            // add deadline
            else if (Enter.contains("deadline")) {
                Enter = Enter.replace("deadline", "");
                String [] str_list = Enter.split("/by");
                tl.addDeadline(str_list[0],str_list[1]);
            }
            // add event
            else if (Enter.contains("event")) {
                Enter = Enter.replace("event", "");
                String [] str_list = Enter.split("/at");
                tl.addDeadline(str_list[0],str_list[1]);
            }

            else if (Enter.equals("list")) {
                tl.print();
                System.out.println(line);
            } else if (Enter.contains("mark")) {
                int index = Integer.parseInt(Enter.split(" ")[1]);
                tl.markAsDone(index);
            } else if (Enter.contains("undo")) {
                int index = Integer.parseInt(Enter.split(" ")[1]);
                tl.markAsNotDone(index);
            } else {
                System.out.println("unknown command detected");
            }
        }

    }
}