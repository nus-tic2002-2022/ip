package duke.tasklist;

import duke.constants.DukeConstants;
import duke.tasks.Task;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;

public class TaskList {
    protected ArrayList<Task> taskArr;

    //This class takes in an ArrayList and passes the value to the protected ArrayList taskArr.
    public TaskList(ArrayList<Task> taskArr) {
        this.taskArr = taskArr;
    }

    //This method prints out all values found in the taskArr
    public void list() {
        final String listIsEmpty = "There are no tasks added yet. :(";
        for (int i = 0; i < taskArr.size(); i++) {
            System.out.println((i + 1) + ". " + taskArr.get(i).getDescription());
        }
        if (taskArr.size() == 0)
            System.out.println(listIsEmpty);
    }

    //This method lists out all values from the taskArr that matches the user supplied input.
    public void list(String find) {
        final String noMatchingTask = "There are no matching tasks.";
        int number = 1;
        for (int i = 0; i < taskArr.size(); i++) {
            if (taskArr.get(i).getDescription().contains(find)) {
                System.out.println(number + ". " + taskArr.get(i).getDescription());
                number++;
            }
        }
        if (number == 1)
            System.out.println(noMatchingTask);
    }

    //This method is called to print out the schedules matching the date specified by the user.
    public void viewSchedule(String dateStr) {
        final String noTasksFound = "There are no tasks found on this date: " + dateStr;
        ArrayList<LocalTime> timeScheduleArr = new ArrayList<>();
        ArrayList<Task> noTimeTaskArr = new ArrayList<>();
        int number = 0, taskArrScheduleCount = 0;
        for (int i = 0; i < taskArr.size(); i++) {
            if (taskArr.get(i).getDescription().contains(dateStr)) {
                Matcher m = DukeConstants.TIME.matcher(taskArr.get(i).getDescription());
                while (m.find()) {
                    timeScheduleArr.add(LocalTime.parse(m.group()));
                }

                if (!DukeConstants.TIME.matcher(taskArr.get(i).getDescription()).find())
                    noTimeTaskArr.add(taskArr.get(i));
            }
        }
        Collections.sort(timeScheduleArr);

        while (!timeScheduleArr.isEmpty()) {
            if (taskArr.get(taskArrScheduleCount).getDescription().contains(timeScheduleArr.get(0).toString())) {
                number++;
                System.out.println(number + ". " + taskArr.get(taskArrScheduleCount).getDescription());
                timeScheduleArr.remove(0);
                taskArrScheduleCount = -1;
            }
            taskArrScheduleCount++;
        }

        for (int i = 0; i < noTimeTaskArr.size(); i++) {
            if (i == 0)
                System.out.println("\nHere are the lists of tasks with no specified timing:");

            System.out.println((i + 1) + ". " + noTimeTaskArr.get(i).getDescription());
        }

        if (number == 0)
            System.out.println(noTasksFound);
    }

    //This method is used to mark an item on the taskArr. There is an input validation to check if the number supplied is within bounds of the Array List.
    public void mark(int i) {
        if (i <= taskArr.size() && i != 0) {
            taskArr.get(i - 1).setDone(true);
            System.out.println("Nice! I've marked this task as done:\n" + taskArr.get(i - 1).getDescription());
        } else {
            System.out.println("Out of range!");
        }
    }

    //This method is used to unmark an item on the taskArr. There is an input validation to check if the number supplied is within bounds of the Array List.
    public void unmark(int i) {
        if (i <= taskArr.size() && i != 0) {
            taskArr.get(i - 1).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:\n" + taskArr.get(i - 1).getDescription());
        } else {
            System.out.println("Out of range!");
        }
    }

    //This method is used to delete an item on the taskArr. There is an input validation to check if the number supplied is within bounds of the Array List.
    public void delete(int i) {
        if (i <= taskArr.size() && i != 0) {
            System.out.println("Noted. I've removed this task: \n" + taskArr.get(i - 1).getDescription() + "\nNow you have " + (taskArr.size() - 1) + " tasks in the list.");
            taskArr.remove(i - 1);
        } else {
            System.out.println("Out of range!");
        }
    }

    //function to delete all tasks in tasklist, used for the C-Archive feature.
    public void deleteAll() {
        taskArr.clear();
        System.out.println("The tasks list will now start new!");
    }

    //Used to add a new task.
    public void add(Task task) {
        taskArr.add(task);
    }

    //Used to get a new task.
    public Task get(int i) {
        return taskArr.get(i);
    }

    //Used to return the size of the taskArr.
    public int size() {
        return taskArr.size();
    }
}
