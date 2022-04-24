import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Duke {
    /**
     * Custom exception handling class for Duke
     */
    public static class DukeException extends Exception{
        public DukeException(String str)
        {
            super(str);
        }
    }

    /**
     * Parent class of the 3 different types of possible tasks
     */
    public abstract static class Task {
        protected String description, type, tag;
        protected LocalDate date;
        protected boolean isDone;

        /**
         * Constructor for Task
         * @param description the activity to be done
         */
        public Task(String description) {
            this.description = description;
            this.isDone = false;
            this.tag = "";
        }
        /**
         * Constructor for Task
         * @param description the activity to be done
         * @param type the type of Task. used for storing purpose
         */
        public Task(String description, String type) {
            this.description = description;
            this.type = type;
            this.isDone = false;
            this.tag = "";
        }
        /**
         * Constructor for Task
         * @param description the activity to be done
         * @param type the type of Task. used for storing purpose
         * @param isDone to check if activity has been completed or not
         */
        public Task(String description, String type, boolean isDone) {
            this.description = description;
            this.type = type;
            this.isDone = isDone;
            this.tag = "";
        }
        /**
         * Constructor for Task
         * @param description the activity to be done
         * @param type the type of Task. used for storing purpose
         * @param isDone to check if activity has been completed or not
         */
        public Task(String description, String type, boolean isDone, String tag) {
            this.description = description;
            this.type = type;
            this.isDone = isDone;
            this.tag = tag;
        }
        /**
         * Constructor for Task
         * @param description the activity to be done
         * @param isDone to check if activity has been completed or not
         */
        public Task(String description, boolean isDone) {
            this.description = description;
            this.isDone = isDone;
            this.tag = "";
        }
        /**
         * Get the result if activity is completed or not
         * @return the tasks completion status
         */
        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }
        /**
         * Get the activity to be done
         * @return the activity in text
         */
        public String getDescription() {
            return description;
        }
        /**
         * Get the type of task:
         * T for ToDo
         * D for Deadline
         * E for Events
         * @return the Task type
         */
        public String getType() {
            return type;
        }
        /**
         * Get the date in LocalDate format:
         * @return the date in LocalDate format
         */
        public LocalDate getDate(){return date;}
        /**
         * Update activity to be done
         */
        public void setDescription(String description) {
            this.description = description;
        }
        /**
         * Mark activity as complete
         */
        public void setIsDone() {
            this.isDone = true;
        }
        /**
         * Mark activity as incomplete
         */
        public void setNotDone() {
            this.isDone = false;
        }
        /**
         * Get the tag of the activity
         * @return the tagging
         */
        public String getTag() {
            return tag;
        }
        /**
         * Update tagging
         */
        public void setTag(String tag) {
            this.tag = tag;
        }
        /**
         * Display content of the task in format of [isDone] description
         */
        public String toString() {
            return "[" + getStatusIcon() + "] " + getDescription() + " " + getTag();

        }
    }
    /**
     * Sub-class ToDo, similar to basic class
     */
    public static class ToDo extends Task {
        /**
         * Constructor for ToDo
         * @param description the activity to be done
         */
        public ToDo(String description) {
            super(description);
        }
        /**
         * Constructor for ToDo
         * @param description the activity to be done
         * @param type the type of Task. used for storing purpose
         */
        public ToDo(String description, String type) {
            super(description, type);
        }
        /**
         * Constructor for ToDo
         * @param description the activity to be done
         * @param type the type of Task. used for storing purpose
         * @param isDone to check if activity has been completed or not
         */
        public ToDo(String description, String type, Boolean isDone) {
            super(description, type, isDone);
        }
        /**
         * Constructor for ToDo
         * @param description the activity to be done
         * @param type the type of Task. used for storing purpose
         * @param isDone to check if activity has been completed or not
         * @param tag extra notes
         */
        public ToDo(String description, String type, Boolean isDone, String tag) {
            super(description, type, isDone, tag);
        }
        /**
         * Constructor for ToDo
         * @param description the activity to be done
         * @param isDone to check if activity has been completed or not
         */
        public ToDo(String description, Boolean isDone) {
            super(description, isDone);
        }
        /**
         * Display content of the task in format of [Type][isDone] description
         * Overrides the parent class method
         */
        @Override
        public String toString() {
            return "[T]" + super.toString();
        }

    }
    /**
     * Sub-class DeadLine, contains local variable LocalDate
     */
    public static class Deadline extends Task {

        protected LocalDate by;
        /**
         * Constructor for Deadline
         * @param description the activity to be done
         * @param by date in LocalDate format
         */
        public Deadline(String description, LocalDate by) {
            super(description);
            this.by = by;
        }
        /**
         * Constructor for Deadline
         * @param description the activity to be done
         * @param by date in LocalDate format
         * @param type the type of Task. used for storing purpose
         * @param isDone to check if activity has been completed or not
         */
        public Deadline(String description, LocalDate by, String type, Boolean isDone) {
            super(description, type, isDone);
            this.by = by;
        }
        /**
         * Constructor for Deadline
         * @param description the activity to be done
         * @param by date in LocalDate format
         * @param type the type of Task. used for storing purpose
         */
        public Deadline(String description, LocalDate by, String type) {
            super(description, type);
            this.by = by;
        }
        /**
         * Constructor for Deadline
         * @param description the activity to be done
         * @param by date in LocalDate format
         * @param isDone to check if activity has been completed or not
         */
        public Deadline(String description, LocalDate by, Boolean isDone) {
            super(description, isDone);
            this.by = by;
        }
        /**
         * Display content of the task in format of [Type][isDone] description
         * Overrides the parent class method
         */
        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        /**
         * Get the date in LocalDate format:
         * @return the date in LocalDate format
         */
        @Override
        public LocalDate getDate() {
            return by;
        }
    }
    /**
     * Sub-class Events, contains local variable LocalDate
     */
    public static class Events extends Task {

        protected LocalDate at;
        /**
         * Constructor for Events
         * @param description the activity to be done
         * @param at date in LocalDate format
         */
        public Events(String description, LocalDate at) {
            super(description);
            this.at = at;
        }/**
         * Constructor for Events
         * @param description the activity to be done
         * @param at date in LocalDate format
         * @param type the type of Task. used for storing purpose
         * @param isDone to check if activity has been completed or not
         */
        public Events(String description, LocalDate at, String type, Boolean isDone) {
            super(description, type, isDone);
            this.at = at;
        }/**
         * Constructor for Events
         * @param description the activity to be done
         * @param at date in LocalDate format
         * @param type the type of Task. used for storing purpose
         */
        public Events(String description, LocalDate at, String type) {
            super(description, type);
            this.at = at;
        }/**
         * Constructor for Events
         * @param description the activity to be done
         * @param at date in LocalDate format
         * @param isDone to check if activity has been completed or not
         */
        public Events(String description, LocalDate at, Boolean isDone) {
            super(description, isDone);
            this.at = at;
        }
        /**
         * Display content of the task in format of [Type][isDone] description
         * Overrides the parent class method
         */
        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        /**
         * Get the date in LocalDate format:
         * @return the date in LocalDate format
         */
        @Override
        public LocalDate getDate() {
            return at;
        }
    }
    /**
     * Class to print out all Tasks in the list
     * @param taskList provide the ArrayList variable of Type Task
     */
    public static void showList(ArrayList<Task> taskList){
        Iterator<Task> iterator = taskList.iterator();
        int counter = 1;
        while(iterator.hasNext())
        {
            System.out.println("    " + counter + ". " + iterator.next());
            counter++;
        }
    }
    /**
     * Class to print out all Tasks in the list
     * @param taskList provide the ArrayList variable of Type Task
     */
    public static void showList(ArrayList<Task> taskList, String search){
        Iterator<Task> iterator = taskList.iterator();
        Task itr2;
        int counter = 1;
        while(iterator.hasNext())
        {
            itr2 = iterator.next();
            if(itr2.getDescription().contains(search)) {
                System.out.println("    " + counter + ". " + itr2.toString());
                counter++;
            }
        }
    }
    /**
     * Check if the string after parsing is a number
     * @param userDescription to check if the string is a number
     */
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
        String textLine, taskType, taskStatus, taskDescription, taskTag;
        LocalDate taskDate;
        int dateTracker, tagTracker, counter=0;
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
                tagTracker = textLine.indexOf('#');
                if (tagTracker<=0) {
                    taskDescription = textLine.substring(2);
                    taskList.add(new ToDo(taskDescription, taskType, isDone));
                } else{
                    taskDescription = textLine.substring(2, tagTracker);
                    taskTag = textLine.substring(tagTracker);
                    taskList.add(new ToDo(taskDescription, taskType, isDone, taskTag));
                }
            } else if(taskType.startsWith("D")){
                dateTracker = textLine.indexOf('|');
                taskDate = LocalDate.parse(textLine.substring(dateTracker+1));
                taskDescription = textLine.substring(2, dateTracker);
                taskList.add(new Deadline(taskDescription + " ", taskDate, taskType, isDone));
            } else if(taskType.startsWith("E")){
                dateTracker = textLine.indexOf('|');
                taskDate = LocalDate.parse(textLine.substring(dateTracker+1));
                taskDescription = textLine.substring(2, dateTracker);
                taskList.add(new Events(taskDescription + " ", taskDate, taskType, isDone));
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
    /**
     * User to overwrite the whole textfile with items in the current list
     * @param filePath provide the full file path to open and write content
     * @param taskList provide the ArrayList variable of Type Task
     */
    public static void writeList(String filePath, ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        Iterator<Task> iterator = taskList.iterator();
        Task itr2;
        String textLine, taskType, taskStatus, taskTag;
        LocalDate taskDate;
        Boolean isDone=false;
        int counter = 1;
        while(iterator.hasNext())
        {
            itr2 = iterator.next();
            textLine = itr2.getDescription();
            taskType = itr2.getType();
            taskStatus = itr2.getStatusIcon();
            taskDate = itr2.getDate();
            taskTag = itr2.getTag();
            if(taskStatus.equals(" "))
            {
                taskStatus = "0";
            } else {
                taskStatus = "1";
            }
            if(taskType.equals("T")){
                fw.write(taskType + taskStatus + textLine + taskTag + System.lineSeparator());
            } else if(taskType.equals("D") || taskType.equals("E")){
                fw.write(taskType + taskStatus + textLine + "|" + taskDate + System.lineSeparator());
            }
            counter++;
        }

        fw.close();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String userInput = "notBye", userCommand ="", userDescription ="", userTag ="";
        LocalDate userDate = LocalDate.parse("2022-04-24");
        int taskCounter = 0;
        int commandTracker, dateTracker, tagTracker;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        File directory = new File("data");
        if (! directory.exists()){
            directory.mkdir();
        }
        File f = new File(directory.getAbsolutePath() + "/spawn.txt");

        try {
            taskCounter = loadFile(f.getPath(), taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("    What up! I'm D. Spawn");
        System.out.println("    Something need doing?");
        System.out.println("    ____________________________________________________________");

        while (!userInput.equals("bye")) {
            userInput = in.nextLine();
            assert !userInput.isEmpty(): "Input should not be empty";
            try {
                if (userInput.startsWith("bye")) {
                    userCommand = "bye";
                } else if (userInput.startsWith("list")) {
                    userCommand = "list";
                } else {
                    commandTracker = userInput.indexOf(' ');
                    if (commandTracker <= 0) {//used to be if
                        throw new DukeException("Incorrect syntax!");
                    } else if (userInput.startsWith("todo") || userInput.startsWith("find")) {
                        userCommand = userInput.substring(0, commandTracker);
                        userDescription = userInput.substring(commandTracker + 1);
                        assert !userDescription.isEmpty(): "String following space after todo or find should not be empty";
                    } else if (userInput.startsWith("mark") || userInput.startsWith("unmark") || userInput.startsWith("delete")) {
                        userCommand = userInput.substring(0, commandTracker);
                        userDescription = userInput.substring(commandTracker + 1); //userDescription is recycled as the task number instead
                        if (isNumber(userDescription)) {
                        } else {
                            throw new DukeException("Not a number!");
                        }
                    } else if (userInput.startsWith("tag")) {
                        userCommand = userInput.substring(0, commandTracker);
                        tagTracker = userInput.indexOf('#');
                        userDescription = userInput.substring(commandTracker + 1, tagTracker-1); //userDescription is recycled as the task number instead
                        userTag = userInput.substring(tagTracker);
                        if (isNumber(userDescription)) {
                        } else {
                            throw new DukeException("Not a number!!");
                        }
                    }else if (userInput.startsWith("deadline") || userInput.startsWith("events")) {
                        dateTracker = userInput.indexOf('/');
                        userCommand = userInput.substring(0, commandTracker);
                        userDescription = userInput.substring(commandTracker + 1, dateTracker - 1);
                        userDate = LocalDate.parse(userInput.substring(dateTracker + 4));
                    } else {
                        throw new DukeException("Incorrect Command!");
                    }
                }
                switch (userCommand) {
                    case "list":
                        assert !taskList.isEmpty(): "List should not be empty";
                        showList(taskList);
                        break;
                    case "find":
                        assert !taskList.isEmpty(): "List should not be empty";
                        showList(taskList, userDescription);
                        break;
                    case "todo":
                        taskList.add(new ToDo(userDescription, "T"));
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
                        System.out.println(userDate);
                        try {
                            writeToFile(f.getPath(), "D0" + userDescription + "|" + userDate + System.lineSeparator(), true);
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        System.out.println("    added: " + taskList.get(taskCounter).toString()); //do stuff here after input
                        taskCounter++;
                        System.out.println("    Now you have " + taskCounter + " tasks in the list.");
                        break;
                    case "events":
                        taskList.add(new Events(userDescription, userDate, "E"));
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
                        System.out.println("    Job's disappeared:");
                        System.out.println("    " + taskList.get(Integer.parseInt(userDescription) - 1).toString());
                        taskList.remove(Integer.parseInt(userDescription) - 1);
                        taskCounter--;
                        System.out.println("    Now you have " + taskCounter + " tasks in the list.");
                        try {
                            writeList(f.getPath(), taskList);
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        break;
                    case "tag":
                        taskList.get(Integer.parseInt(userDescription) - 1).setTag(userTag);
                        System.out.println("    Tagging complete!:");
                        System.out.println("    " + taskList.get(Integer.parseInt(userDescription) - 1).toString());
                        try {
                            writeList(f.getPath(), taskList);
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        break;
                    case "bye":
                        break;
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

        System.out.println("    Off I go then!");
        System.out.println("    ____________________________________________________________");
    }
}
