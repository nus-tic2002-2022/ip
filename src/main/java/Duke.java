import java.util.Scanner;

public class Duke {

    private static Task[] taskList = new Task[100];
    private static int taskCount = 0;

    public static void addItem(Task item) {
        taskList[taskCount] = item;
        taskCount++;
        System.out.println("added this task: " + item);
        System.out.println("you have " + taskCount + " tasks in your list.");
    }

    public static void bye() {
        System.out.println("Bye bye!");
        System.exit(0);
    }

    private static void list(Task[] taskList, int taskCount) {
        if (taskCount > 0) {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(i + 1 + "." + taskList[i]);
            }
        }
        else {
            System.out.println("No tasks in your list.");
        }
    }

    private static void mark(String input, int taskIndex) {

        String markTask = input.substring(0, input.indexOf(' ')).trim();
        if (markTask.equals("mark")) {
            taskList[taskIndex].isDone = true;
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(taskList[taskIndex]);

        }
        else if (markTask.equals("unmark")) {
            taskList[taskIndex].isDone = false;
            System.out.println("OK, I've marked this task as not done yet: ");
            System.out.println(taskList[taskIndex]);
        }
    }

    private static String getWords(String text, int where) { // first word = 1, event/deadline = 2, rest is 3

        int index = text.indexOf(' ');

        if (where == 1) {
            if (index > -1) { // Check if there is more than one word.
                return text.substring(0, index).trim(); // Extract first word.
            }
            else {
                return text; // Text is the first word itself.
            }
        }
        else if (where == 2){
            return text.substring(index, text.lastIndexOf('/')).trim();
        }
        else if (text.contains("/by") || text.contains("/at")){
            index = text.lastIndexOf('/')+3;
            return text.substring(index).trim();
        }
        else {
            return text.substring(index);
        }
    }

    private static String indexer(String input) {

        String index = input.replaceAll("\\D+","");
        return index;
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
            String firstWord = getWords(input, 1);

            if (firstWord.equals("bye")) {
                bye();
            }

            if (firstWord.equals("list")) {
                list(taskList, taskCount);
                continue;
            }

            if(firstWord.contains("todo")) {
                Task t = new Todo(getWords(input, 3).trim());
                addItem(t);
                continue;
            }

            if(firstWord.contains("event")) {
                Task t = new Event(getWords(input, 2),getWords(input, 3));
                addItem(t);
                continue;
            }

            if(firstWord.contains("deadline")) {
                Task t = new Deadline(getWords(input, 2),getWords(input, 3));
                addItem(t);
                continue;
            }

            if(firstWord.contains("mark")) {
                String index = input.replaceAll("\\D+","");
                int taskIndex = Integer.parseInt(index)-1;
                if(taskIndex == -1){
                    continue;
                }
                mark(input, taskIndex);
                continue;
            }

            Task t = new Task(input);
            addItem(t);
        }
    }
}
