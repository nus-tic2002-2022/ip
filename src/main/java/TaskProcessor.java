import java.util.ArrayList;

public class TaskProcessor {

    protected static ArrayList<Task> tasks = new ArrayList<Task>();
    protected static int taskListCount = 0;

    public static void changeTaskStatus(int taskIndex, boolean statusToChange)  {
        tasks.get(taskIndex).setDone(statusToChange);
        System.out.printf(tasks.get(taskIndex).getTask() + " set to %s.\n",statusToChange ? "done" : "undone");
    }

    public static void addTasks() {};

    public static void deleteTasks (String response) throws ArrayIndexOutOfBoundsException {
        int deleteIndex = Integer.parseInt(response.split(" ")[1]) - 1; //what if it's not int?
        System.out.printf("Okay! I've deleted the task '%s' from your list!\n", tasks.get(deleteIndex).getTask());
        tasks.remove(deleteIndex);
        taskListCount --;
        System.out.printf("You now have %d in your list!\n", taskListCount);
    }


    public static void processTaskToList(String taskType, String response) throws ArrayIndexOutOfBoundsException {
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

    public static void printList() {
        ui.printListUI(tasks, taskListCount);
    }

}
