import java.util.*;
import java.io.*;

public class Duke {
    private static final ArrayList<Task> task = new ArrayList<>();
    private static boolean iDuke = true;
    private static int count = 0;
    private static final String ln = "____________________________________________________________";
    private static final String path = "C:/Users/wangs/Documents/duke/data";
    private static final String filename = "duke.txt";
    private static final String filePath = path + filename;

    public static void Greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(ln);
        System.out.println("I'm Duke" + System.lineSeparator()
                + "What can I do for you?");
        System.out.println(ln);
    }

    public static void Exit() throws Exception {
        saveFile();
        System.out.println("Goodbye，Hope to see you again soon!");
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

    public static void printList() {
        int num = 1;
        System.out.println(ln + System.lineSeparator()
                + "Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println(num + ".[" + task.get(i).getStatusIcon()
                    + "] " + task.get(i).getDescription());
            num++;
        }
        System.out.println(ln);
    }

    public static void toDo(String enter, boolean print) throws Exception {
        String[] _enter = enter.split(" ", 2);
        String type = (_enter[0]).trim();
        try {
            task.add(count, new Task(_enter[1]));
            if (print) {
                printAdded(task.get(count).toString());
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
            task.add(count, new Event((dl[0]).trim(), at[1].trim()));
            if (print) {
                printAdded(task.get(count).toString());
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
            task.add(count, new Deadline((dl[0]).trim(), by[1].trim()));
            if (print) {
                printAdded(task.get(count).toString());
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
        printList();
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println(ln);
    }

    private static void execute() throws Exception {
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
                printList();
            }
            break;
            default: {
                throw new DukeException("");
            }
        }
    }

    public static void checkFile() throws IOException {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(filePath);
        if (!file.exists())
            file.createNewFile();
    }

    public static void readFile() throws Exception {
        try {
            Scanner in = new Scanner(new File(filePath));
            while (in.hasNext()) {
                String str = in.nextLine();
                if (str.length() > 0) {
                    fileScanner(str, false);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Here is your Existing List");
        printList();
    }

    public static void saveFile() throws Exception {
        PrintWriter prw = new PrintWriter(filePath);
        StringBuilder inputs = new StringBuilder();
        for (Task t : task) {
            inputs.append(t.getDescription()).append(System.lineSeparator());
        }
        prw.println(inputs);
        prw.close();
    }

    public static void main(String[] args) throws Exception {
        Greet();
        readFile();
        checkFile();
        while (iDuke) {
            try {
                execute();
            } catch (Exception e) {
            }
        }
    }
}