import java.util.Scanner;

public class Duke {

    private static Task[] taskList = new Task[100];
    private static int taskCount = 0;

    public static void addItem(Task item) {
        taskList[taskCount] = item;
        taskCount++;
    }

    public static void bye() {
        System.out.println("Bye bye!");
        System.exit(0);
    }

    private static void list(Task[] taskList, int taskCount) {
        if (taskCount > 0) {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(i + 1 + "." + "[" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
            }
        }
        else {
            System.out.println("No tasks in your list.");
        }
    }

    public static boolean mark(String input) {

        boolean isDone = false;

        String markTask = input.substring(0, input.indexOf(' ')).trim();
        if (markTask.equals("mark")) {
            System.out.println("Nice! I've marked this task as done: ");
            isDone = true;
        }
        else if (markTask.equals("unmark")) {
            System.out.println("OK, I've marked this task as not done yet: ");
        }

        return isDone;
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I'm Duke\n" + "What can I do for you?");

        String input;
        Scanner in = new Scanner(System.in);

        while (true) {
            input = in.nextLine();

            if (input.toLowerCase().equals("bye")) {
                bye();
            }

            if (input.toLowerCase().equals("list")) {
                list(taskList, taskCount);
                continue;
            }

            if(input.contains("mark")) {
                String index = input.replaceAll("\\D+","");
                int taskIndex = Integer.parseInt(index)-1;
                if(taskIndex == -1){
                    continue;
                }
                taskList[taskIndex].isDone = mark(input);
                System.out.println(" [" + taskList[taskIndex].getStatusIcon() + "] " + taskList[taskIndex].description);

                continue;
            }

            Task t = new Task(input);
            addItem(t);
            System.out.println("added: " + input);
        }
    }
}
