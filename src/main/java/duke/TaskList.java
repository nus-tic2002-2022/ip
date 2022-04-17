package duke;

import java.time.LocalDate;
import java.util.ArrayList;

import static duke.Storage.writeFile;

public class TaskList {
    public static final ArrayList<Task> taskList = new ArrayList<>();
    public static final ArrayList<Deadline> deadlineTaskList = new ArrayList<>();
    public static int taskCount = 0;


    /**
     * Add ToDo Task to taskList
     * Increase task count by 1
     *
     * @param item ToDo task
     */
    public static void addToDoTask(ToDo item) {
        taskList.add(item);
        taskCount++;
        writeFile();
    }

    /**
     * Add Deadline Task to taskList
     * Increase task count by 1
     * Add Deadline Task to deadline taskList
     *
     * @param item Deadline task
     */
    public static void addDeadlineTask(Deadline item) {
        taskList.add(item);
        taskCount++;
        deadlineTaskList.add(item);
        writeFile();
    }

    /**
     * Add Event Task to taskList
     * Increase task count by 1
     *
     * @param item Event task
     */
    public static void addEventTask(Event item) {
        taskList.add(item);
        taskCount++;
        writeFile();
    }

    /**
     * Add DoAfter Task to taskList
     * Increase task count by 1
     *
     * @param item DoAfter task
     */
    public static void addDoAfterTask(DoAfter item) {
        taskList.add(item);
        taskCount++;
        writeFile();
    }

    /**
     * Remove Task from taskList
     * Decrease task count by 1
     *
     * @param index remove
     */
    public static void removeTask(int index) {
        System.out.println("Noted. I've removed this task: " + taskList.get(index));
        taskList.remove(index);
        taskCount--;
        System.out.println("You have " + taskCount + " tasks in your task list.");
        writeFile();
    }

    public static void mark(String input, int taskIndex) {
        String markTask = input.substring(0, input.indexOf(' ')).trim();
        if (markTask.equals("mark")) {
            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");
            System.out.println("I have mark this task as completed:");
            taskList.get(taskIndex).isDone = true;
        } else if (markTask.equals("unmark")) {
            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _\n");
            System.out.println("I have mark this task as incompleted: ");
            taskList.get(taskIndex).isDone = false;
        }
        writeFile();
    }

    public static void findTask(String input) {
        int listCount = 1;
        for (int i = 0; i < taskCount; i++) {
            if(taskList.get(i).getDescription().contains(input)){
                System.out.println(listCount + ". " + taskList.get(i).toString());
                listCount ++;
            }
        }
    }

    public static void remindTask(int day) {
        int listCount = 1;
        LocalDate remindDay = LocalDate.now().plusDays(day);
        for(int i = 0; i < deadlineTaskList.size(); i ++){
            if(deadlineTaskList.get(i).getBy().compareTo(remindDay) < 1){
                System.out.println(listCount + ". " + deadlineTaskList.get(i).toString());
                listCount ++;
            }
        }
    }
}
