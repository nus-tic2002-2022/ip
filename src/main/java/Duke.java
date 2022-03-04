import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    //enum for the tasks

    private static Task[] tasks = new Task[100];
    private static int taskListCount = 0;

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0 ; i < taskListCount ; i ++) {
            System.out.println( (i+1)+"."+tasks[i] ); //+1 to i here due to numbering
        }
    }
    
    public static void changeTaskStatus(int taskIndex, boolean statusToChange) {
        tasks[taskIndex].setDone(statusToChange);
        System.out.printf(tasks[taskIndex].getTask() + " set to %s.\n",statusToChange ? "done" : "undone");
    }

    public static void processTaskToList(String taskType, String response) {

        String task = response.substring(response.indexOf(' ') + 1); //can be improved
        System.out.println(task);

        if (taskType.equals("todo")) {
            tasks[taskListCount] = new ToDos(task);
        }

        if (taskType.equals("deadline")) {
            String[] taskDetails = task.split(" /by ");
            tasks[taskListCount] = new Deadlines(taskDetails[0],taskDetails[1]);
        }

        if (taskType.equals("event")) {
            String[] taskDetails = task.split(" /at ");
            tasks[taskListCount] = new Events(taskDetails[0],taskDetails[1]);
        }
        System.out.println("Added the following task to your list:");
        System.out.println(tasks[taskListCount]);
        taskListCount ++;
        System.out.printf("You now have %d in your list!\n", taskListCount);
    }

    public static void chatting() {
        printIntroduction();

        Scanner input = new Scanner(System.in);
        String response;

        while(true) {
            System.out.println("\tWhat's up doc?");
            response = input.nextLine();

            String firstWord = response.split(" ")[0];

            if (firstWord.equals("bye")) {
                printBye();
                return;
            }

            if (firstWord.equals("list")) {
                printList();
                continue;
            }

            if ( firstWord.equals("mark") ) {
                int indexToChange = Integer.parseInt(response.split(" ")[1]) - 1 ; //minus 1 to account for Java starting array at index 0
                changeTaskStatus(indexToChange,true);
                continue;
            }

            if ( firstWord.equals("unmark")) {
                int indexToChange = Integer.parseInt(response.split(" ")[1]) - 1; //minus 1 to account for Java starting array at index 0
                changeTaskStatus(indexToChange,false);
                continue;
            }

            if (firstWord.matches("todo|event|deadline")) {
                processTaskToList(firstWord,response);
            }

            //tasks[taskListCount] = new Task(response);

        }
    }

    public static void printIntroduction() {
        System.out.println("  (\\_/)");
        System.out.println("  (^_^)");
        System.out.println("  (____)0");
        System.out.println("\tHey how's it going? I'm Bugs, a transient robotic bunny.");
        System.out.println("\tHow may I help? (I take carrots as payment)");
    }

    public static void printBye() {
        System.out.println("Already? Cya...");
        System.out.println("  //");
        System.out.println(" ('')");
        System.out.println(" UU \\   ");
        System.out.println(" <><>*");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        chatting();
        return;
    }
}
