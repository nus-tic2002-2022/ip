package seedu.tojava.util;

import java.util.ArrayList;

public class TaskProcessor {

    protected ArrayList<Task> tasks = new ArrayList<Task>();
    protected int taskListCount = 0;
    private String filePath = "data/duke.txt";
    private Storage fileStoreman = new Storage(filePath);

    public void TaskProcessor() {
        fileStoreman = new Storage(filePath);
    };

    public void changeTaskStatus(int taskIndex, boolean statusToChange)  {
        tasks.get(taskIndex).setDone(statusToChange);
        System.out.printf(tasks.get(taskIndex).getTask() + " set to %s.\n",statusToChange ? "done" : "undone");
    }

    public static void addTasks() {};

    public void deleteTasks ( String response) throws IndexOutOfBoundsException,NumberFormatException {
        int deleteIndex = Integer.parseInt(response.split(" ")[1]) - 1; //what if it's not int?
        System.out.printf("Okay! I've deleted the task '%s' from your list!\n", tasks.get(deleteIndex).getTask());
        tasks.remove(deleteIndex);
        taskListCount --;
        System.out.printf("You now have %d in your list!\n", taskListCount);
    }


    public void processTaskToList( String taskType, String response) throws ArrayIndexOutOfBoundsException,NumberFormatException {
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

    public void printList() {
        Ui.printListUI(tasks, taskListCount);
    }

    public void saveTasks () {
        fileStoreman.writeFile(tasks);
    };

    public void loadTasks () {
        fileStoreman.readFile();
    };

}
