package ui;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.*;
import Exception.*;
import tasks.*;
import Duke.*;

/**
 * This is the major class.
 * It initializes MySiri, read commands and process storage.
 */

public class MySiri extends Duke{
    protected static ArrayList<Task> task = new ArrayList<>();
    private static int count = 0;
    private static final String ln = "____________________________________________________________";
    protected static boolean iDuke = true;

    private static void run() throws Exception {
        Scanner in = new Scanner(System.in);
        String enter = (in.nextLine()).trim();
        String[] _enter= enter.split(" ", 2);
        String type = (_enter[0]).trim().toLowerCase();
        Type t =  Type.valueOf(type);
        fileScanner(enter, true, t);
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

    public static void printAdded(String input) {
        int numTask = count + 1;
        System.out.println(ln + System.lineSeparator()
                + "Got it. I've added this task: ");
        System.out.println(input);
        System.out.println("Now you have " + numTask
                + " tasks in the list.");
        System.out.println(ln);
    }

    public static void toDo(String input, boolean print) throws Exception {
        String[] _input = input.split(" ", 2);
        try {
            task.add(count, new Task(_input[1]));
            if (print) {
                printAdded(task.get(count).toString());
            }
            count++;
        } catch (RuntimeException e) {
            throw new MissDescException("Sorry I don't understand what you mean.");
        }
    }

    public static void Event(String enter, boolean addTask) throws Exception {
        String[] _enter = enter.split(" ", 2);
        String[] dl = _enter[1].split("at/");
        String[] at = (dl[1].split(" ", 3));
        LocalDate date = LocalDate.parse(at[1]);
        int time = Integer.parseInt(at[2]);
        if(!chkDateTime(date, time)) return;
        try {
            for (String s : at) {
                System.out.println(s);
                break;
            }
            task.add(count, new Event(dl[0].trim(), Date(at[1]), time));
            if(addTask) {
                printAdded(task.get(count).toString());
            }
            count++;
        } catch (RuntimeException e) {
            throw new MissDescException("Please enter in correct date and time format. Example: 2020-01-01 1300");
        }
    }
    public static void Deadline(String enter, boolean addTask) throws Exception {
        String[] _enter = enter.split(" ", 2);
        String[] dl = _enter[1].split("by/");
        String[] by = (dl[1].split(" ", 3));
        LocalDate date = LocalDate.parse(by[1]);
        int time = Integer.parseInt(by[2]);
        if(!chkDateTime(date, time)) return;
        try {
            task.add(count, new Deadline((dl[0]).trim(), Date(by[1]), time));
            if(addTask) {
                printAdded(task.get(count).toString());
            }
            count++;
        } catch (NumberFormatException e) {
            throw new MissDescException("Please enter in correct date and time format. Example: 2020-01-01 1300");
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
                + System.lineSeparator() + (taskNum)
                + "." + "[" + task.get(taskNum - 1).getStatusIcon() + "] "
                + task.get(taskNum - 1).getDescription()
                + System.lineSeparator()
                + ln);
    }
    public static void unMark(String enter) throws Exception {
        String[] _enter = enter.split(" ", 2);
        int taskNum;
        try {
            taskNum = Integer.parseInt(_enter[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number");
        }
        if (taskNum > count)
            System.out.println("Invalid task number");
        task.get(taskNum - 1).markAsNotDone();
        System.out.println(ln + System.lineSeparator() +
                "Nice! I've re-marked this task as not done:"
                + System.lineSeparator() + (taskNum)
                + "." + "[" + task.get(taskNum - 1).getStatusIcon() + "] "
                + task.get(taskNum - 1).getDescription()
                + System.lineSeparator()
                + ln);
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

    public static void Find (String input) throws Exception {
        String[] _enter = input.split(" ", 2);
        if(_enter[1].equals("")) {
            System.out.println(ln + "Key "+_enter[1]+" not found.");
        } else {
            try {
                PrintWithKey(_enter[1]);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid number");
            }
        }
        System.out.println(ln);
    }

    public static void printList() {
        if (count == 0){
            System.out.println("You don't have any task\n" + "Please add your task");
        }
        else{
            System.out.println(ln +  System.lineSeparator() +"Here are the tasks in your list:");
            int num = 1;
            for (int i = 0; i < count; i++) {
                System.out.println(num+". "+task.get(i).toString());
                num++;
            }
            System.out.println(ln);
        }
    }

    public static void PrintWithKey(String keyword) {
        int numTask = 1;
        for (int a = 0; a < count; a++) {
            String item = task.get(a).getDescription();
            if(item.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(numTask+". "+task.get(a).toString());
                numTask++;
            }
        }
        if(numTask==1)
            System.out.println("You don't have any task list");
    }

    public static LocalDate Date(String msg) {
        return LocalDate.parse(msg.trim());
    }
    public static boolean chkDateTime(LocalDate date, int time) {
        if(count==0||time==0) return true;
        for (int a = 0; a < count; a++) {
            Task t=task.get(a);
            if(t.getTime()==0 || t.getDate()==null) return true;
            if(t.getDate().isEqual(date) && t.getTime()==time) {
                System.out.println("New task was not added because it corrupt with "+t);
                return false;
            }
        }
        return true;
    }
    public static LocalDate processDate(String date) {
        LocalDate d = LocalDate.parse(date.trim());
        return d;
    }

    public static void fileScanner(String enter, boolean addTask, Type t) throws Exception {

        if (enter.length() == 0) {
            throw new DukeException("Invalid input");
        }
        switch (t) {
            case bye: {
                Exit();
            }break;
            case todo: {
                toDo(enter, addTask);
            }break;
            case event: {
                Event(enter, addTask);
            }break;
            case done: {
                Done(enter);
            }break;
            case unmark: {
                unMark(enter);
            }break;
            case deadline: {
                Deadline(enter, addTask);
            }break;
            case delete: {
                Delete(enter);
            }break;
            case list: {
                printList();
            }break;
            case find: {
                Find(enter);
            }break;
            default: {
                throw new DukeException("");
            }
        }
    }

    public static void main() throws Exception {

        while (iDuke) {
            try {
                run();
            } catch (Exception e) {
               new MySiri();
            }
        }
    }
}
