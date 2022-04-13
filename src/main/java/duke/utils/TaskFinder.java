package duke.utils;
import duke.task.Task;
import java.util.ArrayList;

public class TaskFinder {
    public TaskFinder () {};

    /**
     * Takes in a search keyword, and finds tasks with that matches the keyword.
     * @return An arraylist of tasks that matches the search keyword
     */
    public ArrayList<Task> findTask (ArrayList<Task> tasks, String searchTaskKeyword) {
        searchTaskKeyword = searchTaskKeyword.toLowerCase();
        ArrayList<Task> tasksWithKeyword = new ArrayList<Task>();

        for (int i = 0 ; i < tasks.size() ; i++) {
            Task currTask = tasks.get(i);
            if (currTask.getTask().toLowerCase().contains(searchTaskKeyword))
                tasksWithKeyword.add(currTask);
        }
        return tasksWithKeyword;
    }
}
