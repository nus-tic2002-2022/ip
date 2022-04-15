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

    private void validateUserInput(final String line) throws DukeException {
        switch (line) {
            case "todo":
                throw new DukeException(Constant.TODO_CANNOT_BE_EMPTY);
            case "deadline":
                throw new DukeException(Constant.DEADLINE_CANNOT_BE_EMPTY);
            case "event":
                throw new DukeException(Constant.EVENT_CANNOT_BE_EMPTY);
            case "tag":
                throw new DukeException(Constant.TASKTAG_CANNOT_BE_EMPTY);
            case "untag":
                throw new DukeException(Constant.UNTAG_CANNOT_BE_EMPTY);
            case "delete":
                throw new DukeException(Constant.DELETE_CANNOT_BE_EMPTY);
        }
    }

    /**
     * This method calls the tasklist method to create a To-do task from the user input.
     * Then it prints the arraylist into the text file.
     */
    public void createTodoTask(String userInput) {
        taskList.createTodoTask(userInput);
        saveFile.saveFile(taskList.getTaskList());
    }
    /**
     * This method calls the tasklist method to create a Deadline task from the user input.
     * Then it prints the arraylist into the text file.
     */
    public void createDeadlineTask(String userInput) {
        try {
            taskList.createDeadlineTask(userInput);
        } catch (DukeException e) {
            System.out.print(e.getMessage());
        }
        saveFile.saveFile(taskList.getTaskList());
    }
    /**
     * This method calls the tasklist method to create an Event task from the user input.
     * Then it prints the arraylist into the text file.
     */
    public void createEventTask(String userInput) {
        try {
            taskList.createEventTask(userInput);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        saveFile.saveFile(taskList.getTaskList());
    }
    /**
     * This method calls the tasklist method to delete a specific task referenced from the user input.
     * Then it prints the arraylist into the text file.
     */
    public void deleteTask(String userInput) {
        try {
            taskList.deleteTask(userInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveFile.saveFile(taskList.getTaskList());
    }
    /**
     * This method calls the tasklist method to mark a specific task referenced from the user input.
     * The isDone variable in the referenced Tasklist object is set to true.
     */
    public void markTaskAsDone(String userInput) {
        taskList.markTaskAsDone(userInput);
    }
    /**
     * This method calls the tasklist method to mark a specific task referenced from the user input.
     * The isDone variable in the referenced Tasklist object is set to false.
     */
    public void markTaskAsNotDone(String userInput) {
        taskList.markTaskAsNotDone(userInput);
    }
    /**
     * This method calls the tasklist method to change the tag variable in the referenced Tasklist object from the user input.
     * The tag variable is set to the user input. Then it prints the arraylist into the text file.
     */
    public void addTag(String userInput) {
        try {
            taskList.addTag(userInput);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        saveFile.saveFile(taskList.getTaskList());
    }
    /**
     * This method calls the tasklist method to change the tag variable in the referenced Tasklist object from the user input.
     * The tag variable is set to a blank string. Then it prints the arraylist into the text file.
     */
    public void unTag(String userInput) {
        try {
            taskList.unTag(userInput);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        saveFile.saveFile(taskList.getTaskList());
    }

    public void findTask(String userInput) {
        taskList.findTask(userInput);
    }


    public void run() {
        String userInput = "";
        try {
            taskList = saveFile.createArrayList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DukeException e) {
            e.printStackTrace();
        }
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


            if (userInput.equals("bye")) {
                System.out.print("\tGood day and good bye");
                break;
            }

            try {
                validateUserInput(userInput);
            } catch (DukeException dukeException) {
                System.out.print(dukeException.getMessage());
                break;
            }

            if (userInput.equals("list")) {
                taskList.displayList();
            } else {
                if (mark.matches()) {
                    markTaskAsDone(userInput);
                } else if (unmark.matches()) {
                    markTaskAsNotDone(userInput);
                } else if (todo.matches()) {
                    createTodoTask(userInput);
                } else if (deadline.matches()) {
                    createDeadlineTask(userInput);
                } else if (event.matches()) {
                    createEventTask(userInput);
                } else if (delete.matches()) {
                    deleteTask(userInput);
                } else if (tag.matches()) {
                    addTag(userInput);
                } else if (untag.matches()) {
                    unTag(userInput);
                } else if (find.matches()) {
                    findTask(userInput);
                } else {
                    System.out.println("Sorry I don't understand what you mean.");
                }

            }
        }
    }

}
