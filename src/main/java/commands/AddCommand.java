package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.UI;

/**
 * Adds a task to the Task List.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD_TODO = "todo";
    public static final String COMMAND_WORD_DEADLINE = "deadline";
    public static final String COMMAND_WORD_EVENT = "event";
    public static final String COMMAND_WORD_FIXED = "fixed";


    public static final String MESSAGE_USAGE_TODO = "Adds a new Todo task to the Task List \n"
            + "usage: todo <description of task> \n\n"
            + "Eg. todo read a book\n"
            + "Creates a new todo task with description: read a book ";

    public static final String MESSAGE_USAGE_DEADLINE = "Adds a new Deadline task to the Task List \n"
            + "usage: deadline <description> /by <date> \n\n"
            + "Eg. deadline read a book /by tomorrow \n"
            + "Creates a new deadline task with description: read a book by date: tomorrow \n\n"
            + "Note: Most date formats are accepted: \n"
            + "Eg. Monday/Mon, Today, Tomorrow, 1/1, Three days later";

    public static final String MESSAGE_USAGE_EVENT = "Adds a new Event task to the Task List \n"
            + "usage: event <description> /at <date and time> \n\n"
            + "Eg. event book reading session /at tomorrow 3pm \n"
            + "Creates a new event task with description: book reading session by date: tomorrow 3pm \n\n"
            + "Note: Most date formats are accepted: \n"
            + "Eg. Monday/Mon, Today, Tomorrow, 1/1, Three days later";

    public static final String MESSAGE_USAGE_FIXED = "Adds a new fixed duration task to the Task List \n"
            + "usage: fixed <duration> <description> \n\n"
            + "Eg. fixed 5 to read book \n"
            + "Creates a new fixed duration task with description: to read book (needs 5 hours)";


    private final Task task;


    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.addTask(task);
        ui.printTaskAdded(task.toString(), taskList.getNumberOfTask());
        storage.save(taskList);
    }

}
