import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;

public class Duke {
    public static class DukeException extends Exception{
        public DukeException(String str)
        {
            super(str);
        }
    }

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
        public void setIsDone() {
            this.isDone = true;
        }
        public void setNotDone() {
            this.isDone = false;
        }
        public String toString() {
            return "[" + getStatusIcon() + "] " + getDescription();

        }
    }
    public static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class Event extends Task {

        protected String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }

    public static void showList(ArrayList<Task> taskList){
        Iterator<Task> iterator = taskList.iterator();

        while(iterator.hasNext())
        {
            System.out.println("    " + iterator.next());
        }
    }

    public static boolean isNumber(String userDescription) {
        try {
            Integer.parseInt(userDescription);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        ArrayList<Task> taskList = new ArrayList<>();

        String userInput = "notBye";
        String userCommand ="", userDescription ="", userDate ="";

        int taskCounter = 0;
        int commandTracker, dateTracker;

        Scanner in = new Scanner(System.in);

        System.out.println("    ____________________________________________________________");
        System.out.println("    What up! I'm D. Spawn");
        System.out.println("    Something need doing?");
        System.out.println("    ____________________________________________________________");

        do {
            userInput = in.nextLine();
            try {
                if(userInput.startsWith("list")) {
                    userCommand = "list";
                } else {
                    commandTracker = userInput.indexOf(' ');
                    if (commandTracker <= 0) {//used to be if
                        throw new DukeException("Incorrect syntax!");
                    } else if (userInput.startsWith("todo")) {
                        userCommand = userInput.substring(0, commandTracker);
                        userDescription = userInput.substring(commandTracker + 1);
                    } else if (userInput.startsWith("mark") || userInput.startsWith("unmark") || userInput.startsWith("delete")) {
                            userCommand = userInput.substring(0, commandTracker);
                            userDescription = userInput.substring(commandTracker + 1);
                            if(isNumber(userDescription)){
                            } else{
                                throw new DukeException("Not a number!");
                            }
                    } else if (userInput.startsWith("deadline") || userInput.startsWith("event")) {
                        dateTracker = userInput.indexOf('/');
                        userCommand = userInput.substring(0, commandTracker);
                        userDescription = userInput.substring(commandTracker + 1, dateTracker - 1);
                        userDate = userInput.substring(dateTracker + 1);
                    } else {
                        throw new DukeException("Incorrect Command!");
                    }
                }
                switch (userCommand) {
                    case "list":
                        showList(taskList);
                        break;
                    case "todo":
                        taskList.add(new ToDo(userDescription));
                        System.out.println("    added: " + taskList.get(taskCounter).toString()); //do stuff here after input
                        taskCounter++;
                        System.out.println("    Now you have " + taskCounter + " tasks in the list.");
                        break;
                    case "deadline":
                        taskList.add(new Deadline(userDescription, userDate));
                        System.out.println("    added: " + taskList.get(taskCounter).toString()); //do stuff here after input
                        taskCounter++;
                        System.out.println("    Now you have " + taskCounter + " tasks in the list.");
                        break;
                    case "event":
                        taskList.add(new Event(userDescription, userDate));
                        System.out.println("    added: " + taskList.get(taskCounter).toString()); //do stuff here after input
                        taskCounter++;
                        System.out.println("    Now you have " + taskCounter + " tasks in the list.");
                        break;
                    case "mark":
                        taskList.get(Integer.parseInt(userDescription) - 1).setIsDone();
                        System.out.println("    Job's done:");
                        System.out.println("    " + taskList.get(Integer.parseInt(userDescription) - 1).toString());
                        break;
                    case "unmark":
                        taskList.get(Integer.parseInt(userDescription) - 1).setNotDone();
                        System.out.println("    Job's UNdone:");
                        System.out.println("    " + taskList.get(Integer.parseInt(userDescription) - 1).toString());
                        break;
                    case "delete":
                        taskList.remove(Integer.parseInt(userDescription) - 1);
                        System.out.println("    Job's disappeared:");
                        System.out.println("    " + taskList.get(Integer.parseInt(userDescription) - 2).toString());
                        taskCounter--;
                        System.out.println("    Now you have " + taskCounter + " tasks in the list.");
                        break;
                    default:
                        System.out.println("    Tell me correctly what needs doing"); //do stuff here after input
                        break;
                }
            }catch (DukeException e){
                System.out.println("Caught the exception");
                System.out.println("Exception occurred: " + e);
            }

            System.out.println("    ____________________________________________________________");
            System.out.println("    ____________________________________________________________");


        } while (!userInput.equals("bye"));


        System.out.println("    Off I go then!");
        System.out.println("    ____________________________________________________________");
    }
}
