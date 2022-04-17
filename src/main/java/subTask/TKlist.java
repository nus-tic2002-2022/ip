package subTask;
import java.util.ArrayList;

/**
 * This class is used for evaluating the actions of the multiple user commands such as add / delete tasks etc.
 */


public class TKlist {
    public static int count = 0;
    public static ArrayList<Task> list = new ArrayList<>();

    public TKlist(ArrayList<Task> list, int count) {
        TKlist.count = count;
        TKlist.list = list;
    }


    public static ArrayList<Task> UpdatedList() {
        return list;
    }


    public static void List() throws DukeException {
        if (count == 0) {
            throw new DukeException("Currently no items  in the list\n");
        }
        System.out.println("Here are the tasks in your list:\n");
        int seq = 1;
        for (Task l : list) {
            System.out.println(seq + ". " + l);
            seq++;
        }
    }


    public static void Bye() {
        System.out.println("Bye. See you again soon :)");
        System.exit(0);
    }


    public static void Mark(String line) throws DukeException {
        int m = line.indexOf("mark");
        String num = line.substring(m+4).trim();
        if (num.length() < 1) {
            throw new DukeException("OOPS!!! Please enter which task is done\n");
        }
        int n = Integer.parseInt(num);
        if (n > count) {
            throw new DukeException("OOPS!!! Please enter a valid task number\n");
        }
        if (list.get(n-1).getStatusIcon().equals("\u2713")) {
            throw new DukeException("OOPS!!! subTask.Task has already been completed");
        }
        else {
            System.out.println("Good Job! I've marked this task as done:");
            list.get(n-1).setStatus(true);
            System.out.println(list.get(n-1) + "\n");
        }
    }


    public static void Delete(String line) throws DukeException {
        int m = line.indexOf("delete");
        String num = line.substring(m+6).trim();
        if (num.length() < 1) {
            throw new DukeException("OOPS!!! Please enter which task to be deleted\n");
        }
        int n = Integer.parseInt(num);
        if (n > count) {
            throw new DukeException("OOPS!!! Please enter a valid task number\n");
        }
        else {
            System.out.println("Noted. I've removed this task:");
            System.out.println(list.get(n-1));
            list.remove(n-1);
            count--;
            System.out.println("Now you have " + count + " tasks in the list.\n");
        }
    }


    public static void Todo(String line) throws DukeException {
        if (line.trim().length() < 5) {
            throw new DukeException("OOPS!!! Description of task cannot be empty.\n");
        }
        int m = line.toLowerCase().indexOf("todo");
        String description = line.substring(m + 4).trim();
        for (Task l : list) {
            if (l.description.equals(description)) {
                throw new DukeException("OOPS!!! subTask.Task has already been added previously\n");
            }
        }
        list.add(new Todo(description));
        UpdateStatus();
    }

    public static void Deadline(String line) throws DukeException {
        if (line.trim().length() < 9) {
            throw new DukeException("OOPS!!! Description of task cannot be empty.\n");
        }
        if (!line.contains("/")) {
            throw new DukeException("OOPS!!! Please specify time.\n");
        }
        int m = line.indexOf("deadline");
        int n = line.indexOf('/');
        String description = line.substring(m + 8, n).trim();
        String by = line.substring(n + 3).trim();
        for (Task l : list) {
            if (l.description.equals(description)) {
                throw new DukeException("OOPS!!! subTask.Task has already been added previously\n");
            }
        }
        list.add(new Deadline(description, by));
        UpdateStatus();
    }


    public static void Event(String line) throws DukeException {
        if (line.trim().length() < 6) {
            throw new DukeException("OOPS!!! Description of task cannot be empty.\n");
        }
        if (!line.contains("/")) {
            throw new DukeException("OOPS!!! Please specify time.\n");
        }
        int m = line.indexOf("event");
        int n = line.indexOf('/');
        String description = line.substring(m + 5, n).trim();
        String at = line.substring(n + 3).trim();
        for (Task l : list) {
            if (l.description.equals(description)) {
                throw new DukeException("OOPS!!! subTask.Task has already been added previously\n");
            }
        }
        list.add(new Event(description, at));
        UpdateStatus();
    }


    public static void UpdateStatus() {
        System.out.println("Okay:) I've added this task:");
        System.out.println(list.get(count));
        count++;
        System.out.println("Total " + count + " tasks in the list.");
    }
}
