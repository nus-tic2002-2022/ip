import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    public static class Task {
        protected String description, taskType;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
            //this.taskType = " ";
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String getDescription() {
            return description;
        }
        //public String getTaskType() { return taskType; }

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

    public static void showTaskList(Task[] taskList, int taskCounter){
        int counter = 1;
        for (int i = 0; i < taskCounter; i++) {
            System.out.println("    " + counter + "." + taskList[i].toString());
            counter++;
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
        Task[] taskList = new Task[100];
        String userInput = "notBye";
        String userCommand, userContent, userDate;
        int taskCounter = 0;
        int commandTracker, dateTracker;
        //ArrayList<Task> taskList = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        System.out.println("    ____________________________________________________________");
        System.out.println("    What up! I'm D. Spawn");
        System.out.println("    Something need doing?");
        System.out.println("    ____________________________________________________________");

        do {
            userInput = in.nextLine();

            if(userInput.startsWith("mark")){
                taskList[Integer.parseInt(userInput.substring(5))-1].setIsDone();
                System.out.println("    Job's done:");
                System.out.println("    " + taskList[Integer.parseInt(userInput.substring(5))-1].toString());
            } else if(userInput.startsWith("unmark")){
                taskList[Integer.parseInt(userInput.substring(7))-1].setNotDone();
                System.out.println("    Job IS NOT done:");
                System.out.println("    " + taskList[Integer.parseInt(userInput.substring(7))-1].toString());
            } else if(userInput.startsWith("list")){
                showTaskList(taskList, taskCounter);
            } else if(userInput.startsWith("todo")){
                commandTracker = userInput.indexOf(' ');
                if (commandTracker>0) {
                    userContent = userInput.substring(commandTracker + 1);
                    taskList[taskCounter] = new ToDo(userContent);
                    System.out.println("    added: " + taskList[taskCounter].toString()); //do stuff here after input
                    taskCounter++;
                    System.out.println("    Now you have " + taskCounter + " tasks in the list.");
                }
            } else if(userInput.startsWith("deadline")){
                commandTracker = userInput.indexOf(' ');
                if (commandTracker>0) {
                    dateTracker = userInput.indexOf('/');
                    userDate = userInput.substring(dateTracker + 1);
                    userContent = userInput.substring(commandTracker + 1, dateTracker-1);
                    taskList[taskCounter] = new Deadline(userContent, userDate);
                    System.out.println("    added: " + taskList[taskCounter].toString()); //do stuff here after input
                    taskCounter++;
                    System.out.println("    Now you have " + taskCounter + " tasks in the list.");
                }
            } else if(userInput.startsWith("event")){
                commandTracker = userInput.indexOf(' ');
                if (commandTracker>0) {
                    dateTracker = userInput.indexOf('/');
                    userDate = userInput.substring(dateTracker + 1);
                    userContent = userInput.substring(commandTracker + 1, dateTracker-1);
                    taskList[taskCounter] = new Event(userContent, userDate);
                    System.out.println("    added: " + taskList[taskCounter].toString()); //do stuff here after input
                    taskCounter++;
                    System.out.println("    Now you have " + taskCounter + " tasks in the list.");
                }
            } else {
                System.out.println("    Something need doing?"); //do stuff here after input
            }

            System.out.println("    ____________________________________________________________");
            System.out.println("    ____________________________________________________________");


        } while (!userInput.equals("bye"));


        System.out.println("    Ciao!");
        System.out.println("    ____________________________________________________________");
    }
}
