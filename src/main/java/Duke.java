import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    //enum for the tasks

    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int taskListCount = 0;

    public static void printList() {
        UI.printListUI(tasks , taskListCount);
    }
    
    public static void changeTaskStatus(int taskIndex, boolean statusToChange) {
        tasks.get(taskIndex).setDone(statusToChange);
        System.out.printf(tasks.get(taskIndex).getTask() + " set to %s.\n",statusToChange ? "done" : "undone");
    }

    public static void processTaskToList(String taskType, String response) {
        if (taskType.equals("todo")) {
            String task = response.split(" ")[1];
            tasks.add(new ToDos(task));
        } else if (taskType.equals("deadline")) {
            //throw error (what if wrong format)?
            String task = response.substring(response.indexOf(' ') + 1); //can be improved
            String[] taskDetails = task.split(" /by ");
            tasks.add(new Deadlines(taskDetails[0],taskDetails[1]));
            //tasks[taskListCount] = new Deadlines(taskDetails[0],taskDetails[1]);
        } else if (taskType.equals("event")) {
            //throw error (what if wrong format)?
            String task = response.substring(response.indexOf(' ') + 1); //can be improved
            String[] taskDetails = task.split(" /at ");
            tasks.add(new Events(taskDetails[0],taskDetails[1]));
            //tasks[taskListCount] = new Events(taskDetails[0],taskDetails[1]);
        }

        System.out.println("Added the following task to your list:");
        System.out.println(tasks.get(taskListCount));
        taskListCount ++;
        System.out.printf("You now have %d in your list!\n", taskListCount);
    }

    public static void deleteTasks (String response) {
        int deleteIndex = Integer.parseInt(response.split(" ")[1]) - 1; //what if it's not int?
        System.out.printf("Okay! I've deleted the task '%s' from your list!\n", tasks.get(deleteIndex).getTask());
        tasks.remove(deleteIndex);
        taskListCount --;
        System.out.printf("You now have %d in your list!\n", taskListCount);
    }

    public static void chatting() {
        UI.printIntroduction();

        Scanner input = new Scanner(System.in);
        String response;

        while(true) {
            System.out.println("\tWhat's up doc?");
            response = input.nextLine();

            if (response.equals("bye")) {
                UI.printBye();
                return;
            } else if (response.equals("list")) {
                printList();
                continue;
            } else if ( response.startsWith("mark") ) {
                //error here. what if mark is not integer?
                int indexToChange = Integer.parseInt(response.split(" ")[1]) - 1 ; //minus 1 to account for Java starting array at index 0
                changeTaskStatus(indexToChange,true);
                continue;
            } else if ( response.startsWith("unmark")) {
                //error here. what if unmark is not integer?
                int indexToChange = Integer.parseInt(response.split(" ")[1]) - 1; //minus 1 to account for Java starting array at index 0
                changeTaskStatus(indexToChange,false);
                continue;
            } else if (response.startsWith("todo") || response.startsWith("event") || response.startsWith("deadline")) {
                String taskType = response.split(" ")[0];
                try {
                    processTaskToList(taskType,response);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("=^-.-^= You didn't provide your task description...");
                }
            } else if (response.startsWith("delete")) {
                deleteTasks(response);
            } else {
                System.out.println("=^-.-^= Not sure what that means...");
            }

        }
    }




    public static void main(String[] args) {
        UI.printLogo();
        chatting();
        return;
    }
}
