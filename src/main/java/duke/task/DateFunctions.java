package duke.task;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.*;
import java.time.LocalDateTime;

import static java.util.Collections.*;

public class DateFunctions {

    /**
     * Sorts the tasks in order.
     * Todo task will always appear on top as they have neither date nor time.
     * @param ascendingOrder true = ascending order, false = descending order
     */
    public static void listSort(boolean ascendingOrder){
        ArrayList<Task> sortTask = new ArrayList<>(TaskList.taskList);
        ArrayList<Task> sortedTaskList = new ArrayList<>();
        ArrayList<Task> taskWithNoDates = new ArrayList<>();
        ArrayList<Task> taskWithDates = new ArrayList<>();

        for (Task task : sortTask) {
            if (task.getTaskType().equals("T")) {
                taskWithNoDates.add(task);
            } else {
                taskWithDates.add(task);
            }
        }
        if(ascendingOrder) {
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
     * The tasks are compared to current time to determine which tasks have expired.
     * Results are then displayed on screen.
     * Todo tasks never expire as they do not have an end date.
     */
    public static void expiredList(){
        ArrayList<Task> sortTask = new ArrayList<>(TaskList.taskList);
        ArrayList<Task> expiredTaskList = new ArrayList<>();
        for (Task task : sortTask) {
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
     * The tasks are compared to current time to determine which tasks are still valid
     * Results are then displayed on screen.
     * Todo tasks never expire as they do not have an end date.
     */
    public static void upcomingList(){
        ArrayList<Task> sortTask = new ArrayList<>(TaskList.taskList);
        ArrayList<Task> taskWithNoDates = new ArrayList<>();
        ArrayList<Task> upcomingTaskList = new ArrayList<>();
        for (Task task : sortTask) {
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
     * Displays all the tasks which fall on the provided day parameter
     * @param day
     * for Month @see {@link #monthSearch(Month)}
     */
    public static void daySearch(DayOfWeek day){
        ArrayList<Task> sortTask = new ArrayList<>(TaskList.taskList);
        ArrayList<Task> taskWithDates = new ArrayList<>();
        for (Task task : sortTask) {
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
     * Displays all the tasks which fall on the provided month parameter
     * @param month
     * for day @see {@link #daySearch(DayOfWeek)}
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
}
