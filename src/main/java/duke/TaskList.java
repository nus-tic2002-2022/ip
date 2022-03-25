/**
 * Creates a Storage object.
 */
package duke;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> list = new ArrayList<>();

    public TaskList() {    }

    public static void printList () {
        if (list.size() == 0) {
            System.out.println("There is nothing on the list! :)");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(i + 1 + ".");
                list.get(i).getTask();
            }
        }
    }

    public static void printListLength () {
        System.out.println("Now you have " + list.size() + " task(s) in the list.");
    }

    public static int getListLength () {
        return list.size();
    }

    public static void printLastTask (int i) {
        //System.out.println(list.get(i).printTask());
    }

    public static String getTask (int i) {
        return list.get(i).toString();
    }

    public static void printLastTask () {
        System.out.println("Now you have " + list.size() + " task(s) in the list.");
    }

    public static void addTodo(String line) {
        list.add(new Todo(line.substring(5, line.length())));
        //list.get(list.size() - 1).printTask(); // print newly added tasks
    }
    public static void addDeadline(String line) {
        list.add(new Deadline(line.substring(9, line.length())));
        //list.get(list.size() - 1).printTask(); // print newly added tasks
    }

    public static void addEvent(String line) {
        list.add(new Event(line.substring(6, line.length())));
        //list.get(list.size() - 1).printTask(); // print newly added tasks
    }

    public static void markDone(int index) {
        if (list.get(index).getStatusIcon().equals("X")) {
            System.out.println("This task has been marked done before!");
        } else {
            list.get(index).markAsDone();
            //System.out.println("Nice! I've marked this task as done: ");
            //list.get(index).getTask();
        }
    }

    public static void markNotDone(int index) {
        if (list.get(index).getStatusIcon().equals(" ")) {
            System.out.println("This task is currently marks as not done!");
        } else {
            list.get(index).markAsNotDone();
            //System.out.println("OK, I've marked this task as not done yet: ");
            //list.get(index).getTask();
        }
    }

    public static void deleteTask(int index) {
        //System.out.println("Noted. I've removed this task: ");
        //list.get(index).getTask();
        list.remove(index);
    }
}
