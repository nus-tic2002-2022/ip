import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class TaskManager {

    private final Scanner userInputScanner;
    private Storage saveFile;
    private TaskList taskList;

    public TaskManager(final Storage storage) {
        userInputScanner = new Scanner(System.in);
        saveFile = storage;
        taskList = new TaskList();
    }

    private boolean validateUserInput(final String line) {
        switch (line) {
            case "todo ":
                System.out.println(Constant.TODO_CANNOT_BE_EMPTY);
                return false;
            case "deadline ":
                System.out.println(Constant.DEADLINE_CANNOT_BE_EMPTY);
                return false;
            case "event ":
                System.out.println(Constant.EVENT_CANNOT_BE_EMPTY);
                return false;
            case "tag ":
                System.out.println(Constant.TASKTAG_CANNOT_BE_EMPTY);
                return false;
            case "untag ":
                System.out.println(Constant.UNTAG_CANNOT_BE_EMPTY);
                return false;
            case "delete ":
                System.out.println(Constant.DELETE_CANNOT_BE_EMPTY);
                return false;
            default:
                return true;
        }

    }

    /**
     * This method calls the tasklist method to create a To-do task from the user input.
     * Then it prints the arraylist into the text file.
     */
    public void createTodoTask(String taskDescription) {
        taskList.createTodoTask(taskDescription);
        saveFile.saveFile(taskList.getTaskList());
    }

    /**
     * This method calls the tasklist method to create a Deadline task from the user input.
     * Then it prints the arraylist into the text file.
     */
    public void createDeadlineTask(String taskDescription, String dateTime) {
        try {
            taskList.createDeadlineTask(taskDescription, dateTime);
            saveFile.saveFile(taskList.getTaskList());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method calls the tasklist method to create an Event task from the user input.
     * Then it prints the arraylist into the text file.
     */
    public void createEventTask(String taskDescription, String dateTime) {
        try {
            taskList.createEventTask(taskDescription,dateTime);
            saveFile.saveFile(taskList.getTaskList());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * This method calls the tasklist method to delete a specific task referenced from the user input.
     * Then it prints the arraylist into the text file.
     */
    public void deleteTask(String taskNumber) {
        try {
            taskList.deleteTask(taskNumber);
            saveFile.saveFile(taskList.getTaskList());
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * This method calls the tasklist method to mark a specific task referenced from the user input.
     * The isDone variable in the referenced Tasklist object is set to true.
     */
    public void markTaskAsDone(String taskNumber) {
        try {
            taskList.markTaskAsDone(taskNumber);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method calls the tasklist method to mark a specific task referenced from the user input.
     * The isDone variable in the referenced Tasklist object is set to false.
     */
    public void markTaskAsNotDone(String taskNumber) {
        try {
            taskList.markTaskAsNotDone(taskNumber);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method calls the tasklist method to change the tag variable in the referenced Tasklist object from the user input.
     * The tag variable is set to the user input. Then it prints the arraylist into the text file.
     */
    public void addTag(String taskNumber, String tagContent) {
        try {
            taskList.addTag(taskNumber, tagContent);
            saveFile.saveFile(taskList.getTaskList());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * This method calls the tasklist method to change the tag variable in the referenced Tasklist object from the user input.
     * The tag variable is set to a blank string. Then it prints the arraylist into the text file.
     */
    public void unTag(String taskNumber) {
        try {
            taskList.unTag(taskNumber);
            saveFile.saveFile(taskList.getTaskList());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }

    public void findTask(String userInput) {
        taskList.findTask(userInput);
    }


    public void run() throws DukeException, FileNotFoundException {
        String userInput = "";
        taskList = saveFile.createArrayList();

        while (!userInput.contains("bye")) {
            System.out.print("What can I do for you: ");
            userInput = userInputScanner.nextLine();
            Matcher mark = Constant.MARK.matcher(userInput);
            Matcher unmark = Constant.UNMARKED.matcher(userInput);
            Matcher todo = Constant.TODO.matcher(userInput);
            Matcher deadline = Constant.DEADLINE.matcher(userInput);
            Matcher event = Constant.EVENT.matcher(userInput);
            Matcher delete = Constant.DELETE.matcher(userInput);
            Matcher tag = Constant.TAG.matcher(userInput);
            Matcher untag = Constant.UNTAG.matcher(userInput);
            Matcher find = Constant.FIND.matcher(userInput);


            if (validateUserInput(userInput)) {

                if (userInput.equals("bye")) {
                    System.out.print("\tGood day and good bye");
                    break;
                }

                try{
                    if (userInput.equals("list")) {
                        taskList.displayList();
                    } else {
                        if (mark.matches()) {
                            Matcher matcher = Constant.MARK_VALIDATE.matcher(userInput);
                            if (!matcher.matches()){
                                throw new DukeException("\t" + "Missing arguments for mark function");
                            }
                            markTaskAsDone(matcher.group(1));
                        } else if (unmark.matches()) {
                            Matcher matcher = Constant.UNMARK_VALIDATE.matcher(userInput);
                            if (!matcher.matches()){
                                throw new DukeException("\t" + "Missing arguments for unmark function");
                            }
                            markTaskAsNotDone(matcher.group(1));
                        } else if (todo.matches()) {
                            Matcher matcher = Constant.TODO_VALIDATE.matcher(userInput);
                            if (!matcher.matches() || matcher.group(1).isEmpty()){
                                throw new DukeException("\t" + "Missing arguments for todo task");
                            }
                            createTodoTask(matcher.group(1));
                        } else if (deadline.matches()) {
                            Matcher matcher = Constant.DEADLINE_VALIDATE.matcher(userInput);
                            if (!matcher.matches() || matcher.group(1).isEmpty()){
                                throw new DukeException("\t" + "Missing arguments for deadline task");
                            }
                            createDeadlineTask(matcher.group(1), matcher.group(2));
                        } else if (event.matches()) {
                            Matcher matcher = Constant.EVENT_VALIDATE.matcher(userInput);
                            if (!matcher.matches() || matcher.group(1).isEmpty()){
                                throw new DukeException("\t" + "Missing arguments for event task");
                            }
                            createEventTask(matcher.group(1), matcher.group(2));
                        } else if (delete.matches()) {
                            Matcher matcher = Constant.DELETE_VALIDATE.matcher(userInput);
                            if (!matcher.matches()){
                                throw new DukeException("\t" + "Missing arguments for delete function");
                            }
                            deleteTask(matcher.group(1));
                        } else if (tag.matches()) {
                            Matcher matcher = Constant.TAG_VALIDATE.matcher(userInput);
                            if (!matcher.matches() || matcher.group(2).isEmpty()){
                                throw new DukeException("\t" + "Missing arguments for tag function");
                            }
                            addTag(matcher.group(1), matcher.group(2));
                        } else if (untag.matches()) {
                            Matcher matcher = Constant.UNTAG_VALIDATE.matcher(userInput);
                            if (!matcher.matches()){
                                throw new DukeException("\t" + "Missing arguments for untag function");
                            }
                            unTag(matcher.group(1));
                        } else if (find.matches()) {
                            Matcher matcher = Constant.FIND_VALIDATE.matcher(userInput);
                            if (!matcher.matches() || matcher.group(1).isEmpty()){
                                throw new DukeException("\t" + "Missing arguments for find function");
                            }
                            findTask(matcher.group(1));
                        } else {
                            System.out.println("\t" + "Sorry I don't understand what you mean.");
                        }

                    }
                }
                catch (DukeException e){
                    System.out.println(e.getMessage());
                }

            }
        }
    }

}
