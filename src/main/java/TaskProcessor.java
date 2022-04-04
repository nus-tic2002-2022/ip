import duke.task.*;
import duke.task.TasksWithDate;
import duke.utils.TaskFinder;

import java.util.ArrayList;

public class TaskProcessor {

    protected ArrayList<Task> tasks = new ArrayList<Task>();
    protected int taskListCount = 0;
    private String filePath = "data/duke.txt";
    private Storage fileStoreman = new Storage(filePath);
    private TaskFinder taskFinder = new TaskFinder();

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
            //tasks[taskListCount] = new duke.task.Deadlines(taskDetails[0],taskDetails[1]);
        } else if (taskType.equals("event")) {
            //throw error (what if wrong format)?
            String task = response.substring(response.indexOf(' ') + 1); //can be improved
            String[] taskDetails = task.split(" /at ");
            tasks.add(new Events(taskDetails[0],taskDetails[1]));
            //tasks[taskListCount] = new duke.task.Events(taskDetails[0],taskDetails[1]);
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
        ArrayList<String> stringsFromDataFile = fileStoreman.readFile();

        for (int counter = 0 ; counter < stringsFromDataFile.size() ; counter++) {
            loadStringsToTasklist(stringsFromDataFile.get(counter));
            taskListCount++;
        }
    };

    public void findTasksWithKeyword (String keyword) {
        ArrayList<Task> tasksWithKeyword = taskFinder.findTask(tasks, keyword);
        int tasksWithKeywordCount = tasksWithKeyword.size();
        Ui.printListUI(tasksWithKeyword,tasksWithKeywordCount);
    }

    public void rescheduleTask (int taskNum, String rescheduleTaskDate) {
        Task currTask = tasks.get(taskNum);
        TasksWithDate currTaskToReschedule = (TasksWithDate)currTask;
        currTaskToReschedule.rescheduleTask(rescheduleTaskDate);
        Ui.printRescheduleInfo(taskNum,currTaskToReschedule);
    }

    public void loadStringsToTasklist (String task) {
        Character taskType = task.charAt(0);
        if (taskType.equals('T')) {
            String todoName = task.split("\\|")[2];
            ToDos newTodos = new ToDos(todoName);
            if (task.split("\\|")[1].equals('1')) {
                newTodos.setDone(true);
            }
            tasks.add(newTodos);

        } else if (taskType.equals('D')) {
            String deadlineName = task.split("\\|")[2];
            String deadlineDate = task.split("\\|")[3];

            Deadlines newDeadline = new Deadlines(deadlineName,deadlineDate);
            if (task.split("\\|")[1].equals('1')) {
                newDeadline.setDone(true);
            }
            tasks.add(newDeadline);

        } else if (taskType.equals('E')) {
            String eventName = task.split("\\|")[2];
            String eventDate = task.split("\\|")[3];

            Events newEvent = new Events(eventName,eventDate);
            if (task.split("\\|")[1].equals('1')) {
                newEvent.setDone(true);
            }
            tasks.add(newEvent);
        }

    }

}
