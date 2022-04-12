import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {
    public static int counter = 0;
    public static int sub = 1;
    public static ArrayList<Task> task = new ArrayList<>();

    public static void handle(String t) throws DukeException {
        if (t.contains("todo")) {
            if (t.trim().length() < 5) {
                throw new DukeException("OOPS!!! The description of task cannot be empty.\n");
            }
            String description = t.substring(5);
            for (Task l : task) {
                if (l.description.equals(description)) {
                    throw new DukeException("OOPS!!! The task has already been added previously\n");
                }
            }
            task.add(new Todo(description));
        }
        else if (t.contains("deadline")) {
            if (t.trim().length() < 9) {
                throw new DukeException("OOPS!!! The description of task cannot be empty.\n");
            }
            if (!t.contains("/")) {
                throw new DukeException("OOPS!!! Please specify time.\n");
            }
            int num = t.indexOf('/');
            String description = t.substring(9, num-1);
            String by = t.substring(num+4);
            for (Task l : task) {
                if (l.description.equals(description)) {
                    throw new DukeException("OOPS!!! The task has already been added previously\n");
                }
            }
            task.add(new Deadline(description, by));
        }
        else if (t.contains("event")) {
            if (t.trim().length() < 6) {
                throw new DukeException("OOPS!!! The description of task cannot be empty.\n");
            }
            if (!t.contains("/")) {
                throw new DukeException("OOPS!!! Please specify time.\n");
            }
            int num = t.indexOf('/');
            String description = t.substring(6, num-1);
            String what = t.substring(num+4);
            for (Task l : task) {
                if (l.description.equals(description)) {
                    throw new DukeException("OOPS!!! The task has already been added previously\n");
                }
            }
            task.add(new Event(description, what));
        }
        else {
            throw new DukeException("OOPS!!! Please enter a valid task such as todo / deadline / event\n");
        }
    }

    public static void echo() throws DukeException{
        String line;
        Scanner inPut = new Scanner(System.in);
        line = inPut.nextLine();

        if (line.equals("bye")) {
            System.out.println("Bye. See you soon!");
            System.exit(0);
        }
        else if (line.equals("list")) {
            System.out.println("Here is the task list:\n");
            for (Task l : task) {
                System.out.println(sub + ". " + l);
                sub++;
            }
            System.out.println("\n");
            sub = 1;
        }
        else if (line.contains("done")) {
            String[] words = line.split(" ");
            if (words.length < 2 || words[1].trim().equals("")) {
                throw new DukeException("OOPS!!! Please enter which task is done\n");
            }
            int num = Integer.parseInt(words[1]);
            if (num > counter) {
                throw new DukeException("OOPS!!! Please enter a valid task number\n");
            }
            if (task.get(num-1).getStatusIcon().equals("\u2713")) {
                throw new DukeException("OOPS!!! The task has already been completed\n");
            }
            else {
                System.out.println("Good job! I've marked this task as done:");
//                list[n-1].setStatus(true);
                System.out.println(task.get(num-1) + "\n");
            }
        }
        else if (line.contains("delete")) {
            String[] words = line.split(" ");
            if (words.length < 2 || words[1].trim().equals("")) {
                throw new DukeException("Error: Please enter which task to be deleted\n");
            }
            int n = Integer.parseInt(words[1]);
            if (n > counter) {
                throw new DukeException("Error: Please enter a valid task number\n");
            }
            else {
                System.out.println("Noted. I've removed this task:");
                System.out.println(task.get(n-1));
                task.remove(n-1);
                counter--;
                System.out.println("Now you have " + counter + " tasks in the list.\n");
            }
        }
        else {
            handle(line);
            System.out.println("Task added:");
            System.out.println(task.get(counter));
            counter++;
            System.out.println("Total " + counter + " tasks in the list.\n");
        }
    }

    public static void main(String[] args) throws DukeException {
        String greet = "Hi! This is Duke\n" + "What can I do for you?\n";
        System.out.println(greet);
        while(true) {
            try {
                echo();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}