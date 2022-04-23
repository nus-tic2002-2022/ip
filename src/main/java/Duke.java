import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static class DukeException extends Exception{
        public DukeException(String str)
        {
            super(str);
        }
    }

    public abstract static class Task {
        protected String description, type, date="";
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }
        public Task(String description, String type) {
            this.description = description;
            this.type = type;
            this.isDone = false;
        }
        public Task(String description, String type, boolean isDone) {
            this.description = description;
            this.type = type;
            this.isDone = isDone;
        }
        public Task(String description, boolean isDone) {
            this.description = description;
            this.isDone = isDone;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String getDescription() {
            return description;
        }

        public String getType() {
            return type;
        }
        public String getDate(){return date;}

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
        public ToDo(String description, String type) {
            super(description, type);
        }
        public ToDo(String description, String type, Boolean isDone) {
            super(description, type, isDone);
        }
        public ToDo(String description, Boolean isDone) {
            super(description, isDone);
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
        public Deadline(String description, String by, String type, Boolean isDone) {
            super(description, type, isDone);
            this.by = by;
        }
        public Deadline(String description, String by, String type) {
            super(description, type);
            this.by = by;
        }
        public Deadline(String description, String by, Boolean isDone) {
            super(description, isDone);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
        @Override
        public String getDate() {
            return by;
        }
    }

    public static class Event extends Task {

        protected String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
        }
        public Event(String description, String at, String type, Boolean isDone) {
            super(description, type, isDone);
            this.at = at;
        }
        public Event(String description, String at, String type) {
            super(description, type);
            this.at = at;
        }
        public Event(String description, String at, Boolean isDone) {
            super(description, isDone);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
        @Override
        public String getDate() {
            return at;
        }
    }

    public static void showList(ArrayList<Task> taskList){
        Iterator<Task> iterator = taskList.iterator();
        int counter = 1;
        while(iterator.hasNext())
        {
            System.out.println("    " + counter + ". " + iterator.next());
            counter++;
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

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }
    private static int loadFile(String filePath, ArrayList<Task> taskList) throws FileNotFoundException {
        String textLine, taskType, taskStatus, taskDescription, taskDate;
        int dateTracker, counter=0;
        Boolean isDone=false;
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            textLine = s.nextLine();
            taskType = textLine.substring(0, 1);
            taskStatus = textLine.substring(1, 2);
            if(Integer.parseInt(taskStatus)==1){
                isDone = true;
            } else {
                isDone = false;
            }
            if(taskType.startsWith("T")){
                taskDescription = textLine.substring(2);
                taskList.add(new ToDo(taskDescription, taskType, isDone));
            } else if(taskType.startsWith("D")){
                dateTracker = textLine.indexOf('|');
                taskDate = textLine.substring(dateTracker+2);
                taskDescription = textLine.substring(2, dateTracker);
                taskList.add(new Deadline(taskDescription + " ", taskDate, taskType, isDone));
            } else if(taskType.startsWith("E")){
                dateTracker = textLine.indexOf('|');
                taskDate = textLine.substring(dateTracker+2);
                taskDescription = textLine.substring(2, dateTracker);
                taskList.add(new Event(taskDescription + " ", taskDate, taskType, isDone));
            }
            counter++;
        }
        return counter;
    }

    private static void writeToFile(String filePath, String textToAdd, Boolean isAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, isAppend);
        fw.write(textToAdd);
        fw.close();
    }
    public static void writeList(String filePath, ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        Iterator<Task> iterator = taskList.iterator();
        Task itr2;
        String textLine, taskType, taskStatus, taskDescription, taskDate;
        int dateTracker;
        Boolean isDone=false;
        int counter = 1;
        while(iterator.hasNext())
        {
            itr2 = iterator.next();
            textLine = itr2.getDescription();
            taskType = itr2.getType();
            taskStatus = itr2.getStatusIcon();
            taskDate = itr2.getDate();
            if(taskStatus.equals(" "))
            {
                taskStatus = "0";
            } else {
                taskStatus = "1";
            }
            if(taskType.equals("T")){
                fw.write(taskType + taskStatus + textLine + System.lineSeparator());
            } else if(taskType.equals("D") || taskType.equals("E")){
                fw.write(taskType + taskStatus + textLine + "|" + taskDate + System.lineSeparator());
            }
            counter++;
        }

        fw.close();
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

        String userInput = "notBye";
        String userCommand ="", userDescription ="", userDate ="";

        int taskCounter = 0;
        int commandTracker, dateTracker;

        File directory = new File("data");
        if (! directory.exists()){
            directory.mkdir();
        }

        File f = new File(directory.getAbsolutePath() + "/spawn.txt");
 /*       try {
            printFileContents(f.getPath());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
*/
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            taskCounter = loadFile(f.getPath(), taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        Scanner in = new Scanner(System.in);

        System.out.println("    ____________________________________________________________");
        System.out.println("    What up! I'm D. Spawn");
        System.out.println("    Something need doing?");
        System.out.println("    ____________________________________________________________");

        while (!userInput.equals("bye")){
 //       do {
            userInput = in.nextLine();
            try {
                if (userInput.startsWith("bye")) {
                    userCommand = "bye";
                } else if (userInput.startsWith("list")) {
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
                        if (isNumber(userDescription)) {
                        } else {
                            throw new DukeException("Not a number!");
                        }
                    } else if (userInput.startsWith("deadline") || userInput.startsWith("event")) {
                        dateTracker = userInput.indexOf('/');
                        userCommand = userInput.substring(0, commandTracker);
                        userDescription = userInput.substring(commandTracker + 1, dateTracker - 1);
                        userDate = userInput.substring(dateTracker + 3);
                    } else {
                        throw new DukeException("Incorrect Command!");
                    }
                }
                switch (userCommand) {
                    case "list":
                        showList(taskList);
                        break;
                    case "todo":
                        taskList.add(new ToDo(userDescription,"T"));
                        try {
                            writeToFile(f.getPath(), "T0" + userDescription + System.lineSeparator(), true);
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        System.out.println("    added: " + taskList.get(taskCounter).toString()); //do stuff here after input
                        taskCounter++;
                        System.out.println("    Now you have " + taskCounter + " tasks in the list.");
                        break;
                    case "deadline":
                        taskList.add(new Deadline(userDescription, userDate, "D"));
                        try {
                            writeToFile(f.getPath(), "D0" + userDescription + "|" + userDate + System.lineSeparator(), true);
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        System.out.println("    added: " + taskList.get(taskCounter).toString()); //do stuff here after input
                        taskCounter++;
                        System.out.println("    Now you have " + taskCounter + " tasks in the list.");
                        break;
                    case "event":
                        taskList.add(new Event(userDescription, userDate, "E"));
                        try {
                            writeToFile(f.getPath(), "E0" + userDescription + "|" + userDate + System.lineSeparator(), true);
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        System.out.println("    added: " + taskList.get(taskCounter).toString()); //do stuff here after input
                        taskCounter++;
                        System.out.println("    Now you have " + taskCounter + " tasks in the list.");
                        break;
                    case "mark":
                        taskList.get(Integer.parseInt(userDescription) - 1).setIsDone();
                        System.out.println("    Job's done:");
                        System.out.println("    " + taskList.get(Integer.parseInt(userDescription) - 1).toString());
                        try {
                            writeList(f.getPath(), taskList);
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        break;
                    case "unmark":
                        taskList.get(Integer.parseInt(userDescription) - 1).setNotDone();
                        System.out.println("    Job's UNdone:");
                        System.out.println("    " + taskList.get(Integer.parseInt(userDescription) - 1).toString());
                        try {
                            writeList(f.getPath(), taskList);
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        break;
                    case "delete":
                        taskList.remove(Integer.parseInt(userDescription) - 1);
                        System.out.println("    Job's disappeared:");
                        System.out.println("    " + taskList.get(Integer.parseInt(userDescription) - 2).toString());
                        taskCounter--;
                        System.out.println("    Now you have " + taskCounter + " tasks in the list.");
                        try {
                            writeList(f.getPath(), taskList);
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        break;
                    case "bye": break;
                    default:
                        System.out.println("    Tell me correctly what needs doing"); //do stuff here after input
                        break;
                }
            } catch (DukeException e) {
                System.out.println("Caught the exception");
                System.out.println("Exception occurred: " + e);
            }

            System.out.println("    ____________________________________________________________");
            System.out.println("    ____________________________________________________________");

        }
//        } while (!userInput.equals("bye"));


        System.out.println("    Off I go then!");
        System.out.println("    ____________________________________________________________");
    }
}
