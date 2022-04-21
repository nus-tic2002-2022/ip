import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDate;

import java.time.temporal.ChronoUnit;
import java.util.regex.*;

public class TaskList {

    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int numOfTasks;

    //Constructor of TaskList
    public TaskList() {
        numOfTasks = 0;
    }

    //Finding index of taskList
    private static int indexer(String userInput) {
        if (numOfTasks == 0) {
            System.out.println("☹ OOPS!!! There is no task in your list!");
            return -1;
        }
        String listIndexer = userInput;
        listIndexer = listIndexer.replaceAll("[^\\d]", "");
        int index = Integer.parseInt(listIndexer);
        if (index > numOfTasks) {
            System.out.println("ERROR: The number entered exceeds the number of task(s) in the list!");
            System.out.println(numOfTasks + " task(s) in the list.");
            return -1;
        }
        return index - 1;
    }

    //Level 2 List
    public static void list() {
        if (numOfTasks > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < numOfTasks; i++) {
                System.out.println(i + 1 + "." + taskList.get(i).toString());
            }
        } else {
            System.out.println("☹ OOPS!!! There is no task in your list!");
        }
    }

    //Level 3 Mark as Done
    public static void mark(String userInput) {
        int index = indexer(userInput);
        if (index == -1) {
            return;
        }
        if (taskList.get(index).isDone) {
            System.out.println("This task was already marked as done:");
        } else {
            taskList.get(index).isDone = true;
            overwriteTaskFile();
            System.out.println("Nice! I've marked this task as done:");
        }
        System.out.println(taskList.get(index).toString());
    }

    public static void unmark(String userInput) {
        int index = indexer(userInput);
        if (index == -1) {
            return;
        }
        if (taskList.get(index).isDone) {
            taskList.get(index).isDone = false;
            overwriteTaskFile();
            System.out.println("OK, I've marked this task as not done yet:");
        } else {
            System.out.println("STATUS ERROR: CANNOT UNMARK PENDING TASK");
        }
        System.out.println(taskList.get(index).toString());
    }

    //Level 4 Todos, Events, Deadlines
    public static void todo(String userInput) {
        String description = userInput.replaceFirst("\\w+\\s", "");
        //Creating new todo task
        ToDo task = new ToDo(description);
        taskList.add(task);
        numOfTasks++;
        System.out.println("Now you have " + numOfTasks + " task(s) in the list.");
    }

    /*
    public interface DateValidator {
        boolean isValid(String dateStr);
    }

    // public static LocalDate parse(CharSequence text)
    // parses dates using using DateTimeFormatter.ISO_LOCAL_DATE
    // public static LocalDate parse(CharSequence text, DateTimeFormatter formatter)
    // parses dates using the provided formatter

    public class DateValidatorUsingLocalDate implements DateValidator {
        private DateTimeFormatter dateFormatter;

        public DateValidatorUsingLocalDate(DateTimeFormatter dateFormatter) {
            this.dateFormatter = dateFormatter;
        }

        @Override
        public boolean isValid(String dateStr) {
            try {
                LocalDate.parse(dateStr, this.dateFormatter);
            } catch (DateTimeParseException e) {
                return false;
            }
            return true;
        }
    }
    static DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;
    static DateValidator validator = new DateValidatorUsingLocalDate(dateFormatter);

     */

    /*public interface DateMatcher {
        boolean matches(String date);
    }

    class FormattedDateMatcher implements DateMatcher {

        private Pattern DATE_PATTERN = Pattern.compile(
                "^\\d{4}-\\d{2}-\\d{2}$");

        @Override
        public boolean matches(String date) {
            return DATE_PATTERN.matcher(date).matches();
        }
    }*/

    public static void deadline(String userInput) {
        //Creating new deadline task
        String description = userInput.replaceFirst("\\w+\\s", "");
        String date = description.substring(description.indexOf("/by") + 3);

        /*while (LocalDate.parse(date, this.dateFormatter) == false) {
            while (Objects.equals(validator.isValid(date), false)) {
                System.out.println("Your date is in wrong format! Try again!");
                Scanner scanInput = new Scanner(System.in); //Scan user input
                String userInputs = scanInput.nextLine(); //Read user input
                date = Parser.parse(userInputs);
            }*/

            description = description.substring(0, description.indexOf("/by"));
            Deadline task = new Deadline(description, date);
            taskList.add(task);
            numOfTasks++;
            System.out.println("Now you have " + numOfTasks + " task(s) in the list.");
        }

    public static void event(String userInput) {
        //Creating new event task
        String description = userInput.replaceFirst("\\w+\\s", "");
        String date = description.substring(description.indexOf("/at") + 3);
        description = description.substring(0, description.indexOf("/at"));
        Event task = new Event(description, date);
        taskList.add(task);
        numOfTasks++;
        System.out.println("Now you have " + numOfTasks + " task(s) in the list.");
    }

    //Level 6 Delete
    public static void delete(String userInput) {
        int index = indexer(userInput);
        if (index == -1) {
            return;
        }
        System.out.println("Noted. I've removed this task: ");
        System.out.println(taskList.get(index).toString());
        taskList.remove(index);
        numOfTasks--;
        overwriteTaskFile();
        System.out.println("Now you have " + numOfTasks + " task(s) in the list.");
    }

    //Level 7 Save
    public static void importTodo(String fileInputDescription, boolean fileInputMark){
        //Importing todo task
        ToDo task = new ToDo(fileInputDescription, fileInputMark);
        taskList.add(task);
        numOfTasks++;
        System.out.println("Now you have " + numOfTasks + " task(s) in the list.");
    }

    public static void importDeadline(String fileInputDescription, String fileInputDate, boolean fileInputMark){
        //Importing deadline task
        Deadline task = new Deadline(fileInputDescription, fileInputDate, fileInputMark);
        taskList.add(task);
        numOfTasks++;
        System.out.println("Now you have " + numOfTasks + " task(s) in the list.");
    }

    public static void importEvent(String fileInputDescription, String fileInputDate, boolean fileInputMark){
        //Importing deadline task
        Event task = new Event(fileInputDescription, fileInputDate, fileInputMark);
        taskList.add(task);
        numOfTasks++;
        System.out.println("Now you have " + numOfTasks + " task(s) in the list.");
    }

    public static void overwriteTaskFile(){
        try {
            TaskFile.overwriteTask();
            for (int i=0;i<numOfTasks;i++) {
                TaskFile.appendTask(taskList.get(i).toString());
            }
        }catch(IOException i){
            System.out.println("Unable to make changes to the save file");
        }
    }

    //Level 9 - Find
    public static void find(String userInput) {

        String searchKey = userInput.substring(5);
        int counter = 0;

        for (Task m : taskList) {
            if (m.description.toLowerCase().contains(searchKey)) {
                counter++;
                System.out.println(counter + ". " + m + "   Task Index = " + taskList.indexOf(m));
                //System.out.println("Found it!");
            }
        }

        if (counter == 0) {
            System.out.println("OOPS!!! Cannot be found! Please try again!");
        }
    }
}

