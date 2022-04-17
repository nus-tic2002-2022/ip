import java.util.Scanner;
import java.util.Arrays;

public class Duke {
        public static int count = 0;
        public static int seq = 1;
    public static Task[] list = new Task[100];

    public static void sort(String t) throws DukeException {
        if (t.contains("todo")) {
            if (t.trim().length() < 5) {
                throw new DukeException("OOPS!!! Description of task cannot be empty.\n");
            }

            String description = t.substring(5);
            Task[] print = Arrays.copyOf(list,count);
            for (Task p : print) {
                if (p.description.equals(description)) {
                    throw new DukeException("OOPS!!! Task has already been added previously\n");
                }
            }
            list[count] = new Todo(description);
        }
        else if (t.contains("deadline")) {
            if (t.trim().length() < 9) {
                throw new DukeException("OOPS!!! Description of task cannot be empty.\n");
            }
            if (!t.contains("/")) {
                throw new DukeException("OOPS!!! Please specify time.\n");
            }

            int n = t.indexOf('/');
            String description = t.substring(9, n-1);
            String by = t.substring(n+4);
            Task[] print = Arrays.copyOf(list,count);
            for (Task p : print) {
                if (p.description.equals(description)) {
                    throw new DukeException("OOPS!!! Task has already been added previously\n");
                }
            }
            list[count] = new Deadline(description, by);
        }
        else if (t.contains("event")) {
            if (t.trim().length() < 6) {
                throw new DukeException("OOPS!!! Description of task cannot be empty.\n");
            }
            if (!t.contains("/")) {
                throw new DukeException("OOPS!!! Please specify time.\n");
            }

            int n = t.indexOf('/');
            String description = t.substring(6, n-1);
            String at = t.substring(n+4);
            Task[] print = Arrays.copyOf(list,count);
            for (Task p : print) {
                if (p.description.equals(description)) {
                    throw new DukeException("OOPS!!! Task has already been added previously\n");
                }
            }
            list[count] = new Event(description, at);
        }
        else {
            throw new DukeException("OOPS!!! Please enter a valid task such as todo / deadline / event\n");
        }
    }

    public static void echo() throws DukeException {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        if (line.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        }
        else if (line.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            Task[] print = Arrays.copyOf(list,count);
            for (Task p : print) {
                System.out.println(seq + ". " + p);
                seq++;
            }
            System.out.println("\n");
            seq = 1;
        }
        else if (line.contains("mark")) {
            String[] words = line.split(" ");
            if (words.length < 2 || words[1].trim().equals("")) {
                throw new DukeException("OOPS!!! Please enter which task is done\n");
            }
            int n = Integer.parseInt(words[1]);
            if (n > count) {
                System.out.println("OOPS!!! Please enter a valid task number\n");
                echo();
            }
            if (list[n-1].getStatusIcon().equals("\u2713")) {
                throw new DukeException("OOPS!!! Task has already been completed");
            }
            else {
                System.out.println("Good Job! I've marked this task as done:");
                list[n-1].setStatus(true);
                System.out.println(list[n-1] + "\n");
            }
        }
        else {
            sort(line);
            System.out.println("Done. I've added this task:");
            System.out.println(list[count]);
            count++;
            System.out.println("Total " + count + " tasks in the list.\n");
        }
    }


        public static void main (String[]args) throws DukeException {

            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println(logo);

            String hello = "Hi! This Duke :)\n" + "What can I do for you?\n";
            System.out.println(hello);
            while(true) {
                try {
                    echo();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
