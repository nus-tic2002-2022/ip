import Task.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;

/**
 * This class is designed to handle all Task List functions like adding a task/deadline/event.
 * It also includes additional functions like finding specific tasks using a keyword or adding a hashtag
 * to a task.
 *
 * @author (Bay Min Han)
 */

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns the array list.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Loops through the array list and prints it out.
     */
    public void displayList() {
        int taskCounter = 1;
        assert taskCounter == 1 : "List numbering is wrong.";
        if (taskList.size() == 0) {
            System.out.println("\t" + "There are no saved tasks.");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("\t" + taskCounter + "." + taskList.get(i));
                taskCounter++;
            }
        }
    }

    /**
     * Takes in the user input, creates a To-Do task.
     */
    public void createTodoTask(String userInput) {
        userInput = userInput.replaceAll("todo ", "");
        Task todo = new Todo(userInput);
        taskList.add(todo);
        printAddedTask(todo);
    }

    /**
     * Takes in the user input and creates a Deadline task by taking every character before
     * "/by" as the description and everything after "/by" as the date and time. The date and time
     * is saved as LocalDateTime object.
     */
    public void createDeadlineTask(String userInput) throws DukeException {
        userInput = userInput.replaceAll("deadline ", "");
        String name = userInput.substring(0, userInput.indexOf("/") - 1);
        LocalDateTime date = getDateTime(userInput, "/by");
        Task deadline = new Deadline(name, date);
        taskList.add(deadline);
        printAddedTask(deadline);
    }

    /**
     * Takes in the input from the save file and creates a Deadline Task by taking everything
     * before "(by:" as the description and taking everything after "by:" as the date and time.
     */
    public void createDeadlineTaskFromFile(String userInput) throws DukeException {
        //input from text file : deadline task by: 2022-04-10T12:00
        String name = userInput.substring(0, userInput.indexOf("by:") - 1);
        LocalDateTime date = getDateTimeFromSaveFile(userInput, "by:");
        Task deadline = new Deadline(name, date);
        if (userInput.contains("#")){
            userInput = userInput.substring(userInput.indexOf("#"));
            userInput = userInput.replaceAll("#*", "");
            deadline.setTag(userInput);
        }
        taskList.add(deadline);
    }

    /**
     * Takes in the user input and creates an Event task by taking every character before
     * "/at" as the description and everything after "/at" as the date and time. The date and time
     * is saved as LocalDateTime object.
     */
    public void createEventTask(String userInput) throws DukeException {
        userInput = userInput.replaceAll("event ", "");
        String name = userInput.substring(0, userInput.indexOf("/") - 1);
        LocalDateTime date = getDateTime(userInput, "/at");
        Task event = new Event(name, date);
        taskList.add(event);
        printAddedTask(event);
    }

    /**
     * Takes in the input from the save file and creates an Event Task by taking everything
     * before "(at:" as the description and taking everything after "(at:" as the date and time.
     */
    public void createEventTaskFromFile(String userInput) throws DukeException {
        String name = userInput.substring(0, userInput.indexOf("at:") - 1);
        LocalDateTime date = getDateTimeFromSaveFile(userInput, "at:");
        Task event = new Event(name, date);
        if (userInput.contains("#")){
            userInput = userInput.substring(userInput.indexOf("#"));
            userInput = userInput.replaceAll("#*", "");
            event.setTag(userInput);
        }
        taskList.add(event);
    }

    /**
     * Takes in the user input which will be "delete [task number]". It removes everything else
     * except for the number and converts it to an integer for accessing the array list to delete
     * the object specified.
     */
    public void deleteTask(String userInput) throws IOException {
        userInput = userInput.replaceAll("[^0-9]", "");
        System.out.println("This task has been removed: " + "\n" + "\t" + taskList.get(Integer.parseInt(userInput) - 1));
        taskList.remove(Integer.parseInt(userInput) - 1);
        System.out.println("There are " + taskList.size() + " tasks in the list");
    }

    /**
     * Takes in the user input, which will be "mark [task number]". It removes everything else
     * except for the number, converts it into an integer for accessing the object inside
     * the array list and set the isDone variable as true.
     */
    public void markTaskAsDone(String userInput) {
        userInput = userInput.replaceAll("[^0-9]", "");
        taskList.get(Integer.parseInt(userInput) - 1).setStatus(true);
    }

    /**
     * Takes in the user input, which will be "unmark [task number]". It removes everything else
     * except for the number, converts it into an integer for accessing the object inside the
     * array list and sets the isDone variable as false.
     */
    public void markTaskAsNotDone(String userInput) {
        userInput = userInput.replaceAll("[^0-9]", "");
        taskList.get(Integer.parseInt(userInput) - 1).setStatus(false);
    }

    /**
     * Takes in the user input, which will be "tag [task number] [tag content]". It removes "tag "
     * and String variables takes in the number and the tag content. The number is converted into
     * an integer for accessing the object inside the array list and the tag content is placed in
     * as an argument for the isTagged variable.
     */
    public void addTag(String userInput) throws DukeException {
        //input : tag 1 fun
        userInput = userInput.replaceAll("tag ", "");
        String taskNumber = userInput.substring(0, 1);
        String tagContent = getTagContent(userInput, " ");
        taskList.get(Integer.parseInt(taskNumber) - 1).setTag(tagContent);
    }

    /**
     * Takes in the user input, which will be "untag [task number]". It removes "untag " leaving the
     * [task number]. The number is converted into an integer for accessing the object inside the
     * array list and the tag content is placed in as an argument for the isTagged variable.
     */
    public void unTag(String userInput) throws DukeException {
        //input : untag 1
        userInput = userInput.replaceAll("untag ", "");
        taskList.get(Integer.parseInt(userInput) - 1).setTag("");
    }

    /**
     * Takes in the user input, which will be "find [keyword]". It removes "find " leaving the
     * [keyword]. The for loop will iterate through the array list and checks the description of
     * each task for the keyword. If the keyword is present, it prints out the task.
     */
    public void findTask(String userInput) {
        //input : find book
        userInput = userInput.replaceAll("find ", "");
        int findCounter = 1;
        assert findCounter == 1 : "List numbering is wrong";
        System.out.print("\t" + "Here are the tasks you searched for: " + "\n");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(userInput)) {
                System.out.print("\t" + findCounter + "." + taskList.get(i) + "\n");
                findCounter++;
            }
        }
    }

    /**
     * Prints the task that was added as well as the total number of tasks in the list.
     */
    public void printAddedTask(Task task) {
        System.out.println("\t" + "This task has been added: " + "\n" + "\t" + task);
        System.out.println("There are " + taskList.size() + " tasks in the list");
    }

    /**
     * This method takes in the string from the createDeadlineTask and createEventTask methods
     * after "deadline " or "event " has been removed and the start
     */
    private LocalDateTime getDateTime(String userInput, String str) throws DukeException {
        Matcher matcher = Constant.DATE.matcher(userInput.substring(userInput.indexOf(str)));
        if (!matcher.matches()) {
            throw new DukeException(Constant.DATE_TIME_CANNOT_BE_FOUND);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(matcher.group(1), formatter);
    }

    /**
     * This method takes in the string from the createDeadlineTaskFromFile and createEventTaskFromFile
     * methods and reads the date and time to create the LocalDateTime object for object creation in
     * the array list.
     */
    private LocalDateTime getDateTimeFromSaveFile(String userInput, String str) throws DukeException {
        Matcher matcher = Constant.DATE.matcher(userInput.substring(userInput.indexOf(str), userInput.indexOf(" #")));
        if (!matcher.matches()) {
            throw new DukeException(Constant.DATE_TIME_CANNOT_BE_FOUND);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return LocalDateTime.parse(matcher.group(1), formatter);
    }

    /**
     * The command and input from the user should be "tag [task number] [tag content]". This uses
     * a regex pattern to read just the tag content and return it as a String.
     */
    private String getTagContent(String userInput, String str) throws DukeException {
        Matcher matcher = Constant.TAGCONTENT.matcher(userInput.substring(userInput.indexOf(str)));
        if (!matcher.matches()) {
            throw new DukeException(Constant.TAGCONTENT_CANNOT_BE_EMPTY);
        }
        return matcher.group(1);
    }
}
