package duke.task;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.time.LocalDateTime;

import static java.util.Collections.sort;
import static java.util.Collections.reverseOrder;

public class DateFunctions {

    /**
     * Sorts the tasks by date.
     * Todo task will always appear on top as they have neither date nor time.
     *
     * @param isAscendingOrder true = ascending order, false = descending order.
     */
    public static void listSort(boolean isAscendingOrder){
        ArrayList<Task> copyOfTaskList = new ArrayList<>(TaskList.taskList);
        ArrayList<Task> sortedTaskList = new ArrayList<>();
        ArrayList<Task> taskWithNoDates = new ArrayList<>();
        ArrayList<Task> taskWithDates = new ArrayList<>();

        for (Task task : copyOfTaskList) {
            if (task.getTaskType().equals("T")) {
                taskWithNoDates.add(task);
            } else {
                taskWithDates.add(task);
            }
        }
        if(isAscendingOrder) {
            dateSort(taskWithDates);
        }else{
            dateSortReverse(taskWithDates);
        }
        sortedTaskList.addAll(taskWithNoDates);
        sortedTaskList.addAll(taskWithDates);
        for(Task t : sortedTaskList){
            System.out.println(t.toString());
        }
    }
    private static void dateSort(ArrayList<Task> tasks){
        sort(tasks);
    }
    private static void dateSortReverse(ArrayList<Task> tasks){
        tasks.sort(reverseOrder());
    }

    /**
     * Shows tasks past their due date.
     * Tasks are compared to current time to determine which tasks have expired.
     * Results are then displayed on screen.
     * Todo tasks never expire as they do not have an end date.
     */
    public static void expiredList(){
        ArrayList<Task> copyOfTaskList = new ArrayList<>(TaskList.taskList);
        ArrayList<Task> expiredTaskList = new ArrayList<>();
        for (Task task : copyOfTaskList) {
            if (task.getTaskType().equals("D") || task.getTaskType().equals("E")) {
                if (task.getDT().compareTo(LocalDateTime.now()) < 0) {
                    expiredTaskList.add(task);
                }
            }
        }
        dateSort(expiredTaskList);
        for(Task t : expiredTaskList){
            System.out.println(t.toString());
        }
    }
    /**
     * Shows tasks that have an upcoming date.
     * Tasks are compared to current time to determine which tasks are still valid.
     * Results are then displayed on screen.
     * Todo tasks never expire as they do not have an end date.
     */
    public static void upcomingList(){
        ArrayList<Task> copyOfTaskList = new ArrayList<>(TaskList.taskList);
        ArrayList<Task> taskWithNoDates = new ArrayList<>();
        ArrayList<Task> upcomingTaskList = new ArrayList<>();
        for (Task task : copyOfTaskList) {
            if (task.getTaskType().equals("D") || task.getTaskType().equals("E")) {
                if (task.getDT().compareTo(LocalDateTime.now()) >= 0) {
                    upcomingTaskList.add(task);
                }
            } else {
                taskWithNoDates.add(task);
            }
        }
        dateSort(upcomingTaskList);
        upcomingTaskList.addAll(taskWithNoDates);
        for(Task t : upcomingTaskList){
            System.out.println(t.toString());
        }
    }

    /**
     * Displays all the tasks which fall on the provided day parameter.
     *
     * @param day Day.
     * @see DateFunctions#monthSearch(Month)
     */
    public static void daySearch(DayOfWeek day){
        ArrayList<Task> copyOfTaskList = new ArrayList<>(TaskList.taskList);
        ArrayList<Task> taskWithDates = new ArrayList<>();
        for (Task task : copyOfTaskList) {
            if (task.getTaskType().equals("D") || task.getTaskType().equals("E")) {
                if (task.getDT().getDayOfWeek() == day) {
                    taskWithDates.add(task);
                }
            }
        }
        for(Task t : taskWithDates){
            System.out.println(t.toString());
        }
    }
    /**
     * Displays all the tasks which fall on the provided month parameter.
     *
     * @param month Month.
     * @see DateFunctions#daySearch(DayOfWeek)
     */
    public static void monthSearch(Month month){
        ArrayList<Task> sortTask = new ArrayList<>(TaskList.taskList);
        ArrayList<Task> taskWithDates = new ArrayList<>();
        for (Task task : sortTask) {
            if (task.getTaskType().equals("D") || task.getTaskType().equals("E")) {
                if (task.getDT().getMonth() == month) {
                    taskWithDates.add(task);
                }
            }
        }
        for(Task t : taskWithDates){
            System.out.println(t.toString());
        }
    }

    /**
     * Searches for tasks to be done after a given tasks.
     *
     * @param t task which contains the timestamp for comparison.
     */
    public static void after(Task t){
        ArrayList<Task> copyOfTaskList = new ArrayList<>(TaskList.taskList);
        ArrayList<Task> taskWithDates = new ArrayList<>();
        for (Task task : copyOfTaskList) {
            if (task.getTaskType().equals("D") || task.getTaskType().equals("E")) {
                if (task.getDT().compareTo(t.getDT()) > 0) {
                    taskWithDates.add(task);
                }
            }
        }
        dateSort(taskWithDates);
        if(taskWithDates.size() == 0){
            System.out.println("There are no tasks after this!");
        }else {
            for (Task task : taskWithDates) {
                System.out.println(task.toString());
            }
        }
    }
}
