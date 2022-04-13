package duke.utils;
import duke.memoryaccess.Storage;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Schedule;
import duke.task.Task;
import duke.task.TasksWithDate;
import duke.task.ToDos;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * <h1>Task Processor</h1>
 * This class is the heavy lifter of the Duke program. It contains the entire task list,
 * and also is in charge of creating different objects required to carry out the duke operations.
 *
 * @author  Sam Yong
 * @since   2022-03-15
 */
public class TaskProcessor {

    protected ArrayList<Task> tasks = new ArrayList<Task>();
    protected int taskListCount = 0;
    private String filePath = "data/";
    private String fileName = "duke.txt";
    private Storage fileStoreman = new Storage(filePath,fileName);
    private TaskFinder taskFinder = new TaskFinder();
    private Ui ui = new Ui();

    public void TaskProcessor() {
        fileStoreman = new Storage(filePath,fileName);
    };

    /**
     * Changes current status of tasks. Input is either true or false, depending on user requirement
     */
    public void changeTaskStatus(int taskIndex, boolean statusToChange)  {
        tasks.get(taskIndex).setDone(statusToChange);
        System.out.printf(tasks.get(taskIndex).getTask() + " set to %s.\n",statusToChange ? "done" : "undone");
    }


    /**
     * Deletes task on list. Task is specified by the task num as it is on the list
     * @param User input (as string) with 'delete' keyword
     * @return Nothing. Messages will be printed on Ui indicating tasks deleted, and remaining number of tasks in list
     * @exception IndexOutOfBoundsException if user inputs an int larger than current list size
     * @exception NumberFormatException if user inputs a non-int
     * @see IndexOutOfBoundsException , NumberFormatException
     * @version 1
     * @author Sam Yong
     * @since 2022-03-15
     */
    public void deleteTasks ( String response) throws IndexOutOfBoundsException,NumberFormatException {
        int deleteIndex = Integer.parseInt(response.split(" ")[1]) - 1; //what if it's not int?
        System.out.printf("Okay! I've deleted the task '%s' from your list!\n", tasks.get(deleteIndex).getTask());
        tasks.remove(deleteIndex);
        taskListCount --;
        assert(taskListCount >= 0);
        System.out.printf("You now have %d in your list!\n", taskListCount);
    }

    /**
     * Processes task to list. Task is processed into a Task Array, using polymorphism
     */
    public void processTaskToList( String taskType, String response) throws ArrayIndexOutOfBoundsException,NumberFormatException, DateTimeParseException {
        if (taskType.equals("todo")) {
            String task = response.substring(5);
            tasks.add(new ToDos(task));
        } else if (taskType.equals("deadline")) {
            //throw error (what if wrong format)?
            String task = response.substring(response.indexOf(' ') + 1); //can be improved
            String[] taskDetails = task.split(" /by ");
            tasks.add(new Deadlines(taskDetails[0],taskDetails[1]));
            //tasks[taskListCount] = new duke.task.Deadlines(taskDetails[0],taskDetails[1]);
        } else if (taskType.equals("event")) {
            //throw error (what if wrong format)?
            String task = response.substring(response.indexOf(' ') + 1); //can be improved
            String[] taskDetails = task.split(" /at ");
            tasks.add(new Events(taskDetails[0],taskDetails[1]));
            //tasks[taskListCount] = new duke.task.Events(taskDetails[0],taskDetails[1]);
        } else if (taskType.equals("schedule")) {
            String task = response.substring(response.indexOf(' ') + 1); //can be improved
            String[] taskDetails = task.split(" /from ");
            String taskName = taskDetails[0];

            String[] taskDates = taskDetails[1].split(" /to ");
            String startDate = taskDates[0];
            String endDate = taskDates[1];

            tasks.add(new Schedule(taskName,startDate,endDate));

        }

        System.out.println("Added the following task to your list:");
        System.out.println(tasks.get(taskListCount));
        taskListCount ++;
        assert(taskListCount >= 0);
        System.out.printf("You now have %d in your list!\n", taskListCount);
    }


    public void printList() {
        ui.printListUI(tasks, taskListCount);
    }

    public void saveTasks () {
        fileStoreman.writeFile(tasks);
    };

    public void loadTasks () {
        tasks = fileStoreman.readFile();

        taskListCount = tasks.size();
        assert (taskListCount >= 0);
    };

    /**
     * Prints a list of tasks that matches user's input search keyword
     */
    public void findTasksWithKeyword (String keyword) {
        ArrayList<Task> tasksWithKeyword = taskFinder.findTask(tasks, keyword);
        int tasksWithKeywordCount = tasksWithKeyword.size();
        ui.printListUI(tasksWithKeyword,tasksWithKeywordCount);
    }

    /**
     * Reschedules a tasks. Takes in a task num and a date in string format
     */
    public void rescheduleTask (int taskNum, String rescheduleTaskDate) {
        Task currTask = tasks.get(taskNum);
        try {
            TasksWithDate currTaskToReschedule = (TasksWithDate)currTask;
            currTaskToReschedule.rescheduleTask(rescheduleTaskDate);
            ui.printRescheduleInfo(taskNum,currTaskToReschedule);
        } catch (ClassCastException e) {
            ui.printRescheduleErrorMessage(taskNum,currTask);
        }
        return;
    }


}
