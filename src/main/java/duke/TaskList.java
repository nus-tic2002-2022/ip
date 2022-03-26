/**
 * TaskList interacts between Tasks objects vs Ui and Storage.
 *
 */
package duke;
import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> list = new ArrayList<>();

    public TaskList() {    }

    public static void printList () {
        if (list.size() == 0) {
            Ui.printNothingInListMsg();
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(i + 1 + ".");
                list.get(i).getTask();
            }
        }
    }

    public static void printNoOfItemsInList() {
        System.out.println("Now you have " + list.size() + " task(s) in the list.");
    }

    public static int getListLength () {
        return list.size();
    }

    public static void printOneTask (int i) {
        System.out.println(list.get(i).toString());
    }

    public static String getOneTask(int i) {
        return list.get(i).toString();
    }

    public static void addTodo(String line) {
        list.add(new Todo(line));
    }

    public static void addDeadline(String line) {
        list.add(new Deadline(line));
    }

    public static void addEvent(String line) {
        list.add(new Event(line));
    }

    public static void markDone(int index) {
        list.get(index).markAsDone();
    }

    public static Boolean checkDone(int index) {
        return list.get(index).getStatusBoolean();
    }

    public static void markNotDone(int index) {
        list.get(index).markAsNotDone();
    }

    public static void deleteTask(int index) {
        list.remove(index);
    }

    public static ArrayList<Task> sortList() {

        if (list.size() == 0) {
            Ui.printNothingInListMsg();
        } else {

            for (int i = 0; i < list.size(); i++) { //outer loop
                for (int j = i + 1; j < list.size(); j++) { //inner loop

                    //checking tasks
                    ArrayList<Task> tempTask = new ArrayList<>();
                    if(list.get(j).getTaskDate().compareTo(list.get(i).getTaskDate()) < 0) { //check if next task is before current task

                        // swapping
                        tempTask.add(0,list.get(i));
                        list.set(i, list.get(j));
                        list.set(j, tempTask.get(0));

                    }

                }

            }
        }

        return list;
    }

    public static void findTask (String line) {
        int noOfTasks = 0;
        if (list.size() == 0) {
            Ui.printNothingInListMsg();
        } else {
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).toString().toLowerCase().contains(line.toLowerCase())) {
                    noOfTasks++;
                    System.out.print(noOfTasks + ".");
                    list.get(i).getTask();
                }
            }
            System.out.println("There are " + noOfTasks + " matching task(s) in the list.");
        }
    }
}
