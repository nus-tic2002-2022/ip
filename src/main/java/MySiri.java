import java.util.ArrayList;
import java.util.Scanner;
import java.time.*;

public class MySiri extends Duke{
    protected static ArrayList<Task> task = new ArrayList<>();
    private static int count = 0;
    private static final String ln = "____________________________________________________________";
    protected static boolean iDuke = true;

    public static void main() throws Exception {

        while (iDuke) {
            try {
                run();
            } catch (Exception e) {
            }
        }
    }

    public static void Exit() throws Exception {
        Storage.saveFile();
        System.out.println(ln + System.lineSeparator()
                + "Your tasks are saved"
                + System.lineSeparator()
                + "Goodbye，Hope to see you again soon!");
        iDuke = false;
        System.exit(0);
    }

    public static void printAdded(String content) {
        int numTask = count + 1;
        System.out.println(ln + System.lineSeparator()
                + "Got it. I've added this task: ");
        System.out.println(content);
        System.out.println("Now you have " + numTask
                + " tasks in the list.");
        System.out.println(ln);
    }

    public static void toDo(String input, boolean print) throws Exception {
        String[] _enter = input.split(" ", 2);
        String type = (_enter[0]).trim();
        try {
            task.add(count, new Task(_enter[1], "test"));
            if (print) {
                printAdded(task.get(count).printTask());
            }
            count++;
        } catch (RuntimeException e) {
            throw new MissDescException(type);
        }
    }

    public static void Done(String enter) throws Exception {
        String[] _enter = enter.split(" ", 2);
        int taskNum;
        try {
            taskNum = Integer.parseInt(_enter[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number");
        }
        if (taskNum > count)
            System.out.println("Invalid task number");
        task.get(taskNum - 1).markAsDone();
        System.out.println(ln + System.lineSeparator() +
                "Nice! I've marked this task as done:"
                + System.lineSeparator()
                + "[" + task.get(taskNum - 1).getStatusIcon() + "] "
                + task.get(taskNum - 1).getDescription()
                + System.lineSeparator()
                + ln);
    }

    public static void Event(String enter, boolean print) throws Exception {
        String[] _enter = enter.split(" ", 2);
        String type = (_enter[0]).trim();
        try {
            String[] dl = _enter[1].split("/");
            String[] at = (dl[1].split(" ", 2));
            task.add(count, new Event((dl[0]).trim(), processDate(at[1]), enter));
            if (print) {
                printAdded(task.get(count).printTask());
            }
            count++;
        } catch (RuntimeException e) {
            throw new MissDescException(type);
        }
    }

    public static void Deadline(String enter, boolean print) throws Exception {
        String[] _enter = enter.split(" ", 2);
        String type = (_enter[0]).trim();
        try {
            String[] dl = _enter[1].split("/");
            String[] by = (dl[1].split(" ", 2));
            task.add(count, new Deadline((dl[0]).trim(), processDate(by[1]), enter));
            if (print) {
                printAdded(task.get(count).printTask());
            }
            count++;
        } catch (RuntimeException e) {
            throw new MissDescException(type);
        }
    }

    public static void Delete(String enter) throws Exception {
        String[] _enter = enter.split(" ", 2);
        int numTask;
        try {
            numTask = Integer.parseInt(_enter[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number");
        }
        if (numTask > count)
            throw new DukeException("Invalid task number");
        System.out.println(ln + System.lineSeparator()
                + "Noted. I've removed this task: "
                + System.lineSeparator() + " ["
                + task.get(numTask - 1).getStatusIcon() + "] "
                + task.get(numTask - 1).getDescription());
        task.remove(numTask - 1);
        count--;
        printList(enter);
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println(ln);
    }

    private static void run() throws Exception {
        Scanner in = new Scanner(System.in);
        String enter = (in.nextLine()).trim();
        fileScanner(enter, true);
    }

    public static void fileScanner(String enter, boolean print) throws Exception {
        String[] _enter = enter.split(" ", 2);
        String type = (_enter[0]).trim();

        if (enter.length() == 0) {
            throw new DukeException("Invalid input");
        }
        switch (type) {
            case "bye": {
                Exit();
            }
            break;
            case "todo": {
                toDo(enter, print);
            }
            break;
            case "event": {
                Event(enter, print);
            }
            break;
            case "done": {
                Done(enter);
            }
            break;
            case "deadline": {
                Deadline(enter, print);
            }
            break;
            case "delete": {
                Delete(enter);
            }
            break;
            case "list": {
                printList(enter);
            }
            break;
            default: {
                throw new DukeException("");
            }
        }
    }

    public static void printList(String enter) throws Exception {
        String[] _enter = enter.split(" ", 2);
        if(count == 0){
            System.out.println("You don't have any task in the list");
        } else{
            System.out.println(ln + "Here are the tasks in your list:"
                    + System.lineSeparator());
            listTask();
            }

        System.out.println(ln);
    }
    public static void PrintWithDate(LocalDate d) {
        int n = 1;
        for (int i = 0; i < count; i++) {
            LocalDate date=task.get(i).getDate();
            if(date!=null && date.equals(d)) {
                System.out.println(n+". "+task.get(i).printTask());
                n++;
            }
        }
        if(n==1)
            System.out.println("No task found");
    }

    public static void PrintWithKey(String keyword) {
        int n = 1;
        for (int a = 0; a < count; a++) {
            String title=task.get(a).getDescription();
            if(title.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(n+". "+task.get(a).printTask());
                n++;
            }
        }
        if(n==1)
            System.out.println("No task found");
    }

    public static void listTask() {
        int n = 1;
        for (int i = 0; i < count; i++) {
            System.out.println(n+". "+task.get(i).printTask());
            n++;
        }
    }
    public static LocalDate processDate(String msg) {
        return LocalDate.parse(msg.trim());
    }
}
