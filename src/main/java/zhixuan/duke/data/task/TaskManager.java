package zhixuan.duke.data.task;

import zhixuan.duke.storage.TaskEncoder;
import zhixuan.duke.ui.DukeUI;
import zhixuan.duke.common.EnumTask;
import zhixuan.duke.data.exceptions.InvalidTaskException;
import zhixuan.duke.data.exceptions.UnknownCommandException;
import zhixuan.duke.common.Messages;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Task manager to make changes to taskList
 *
 **/
public class TaskManager {

    private static TaskManager taskManager;
    private final DukeUI ui;
    private ArrayList<Task> taskList;

    /**
     * TaskManager constructor
     *
     * Constructor to hold singleton design.
     * Create new DukeUI and ArrayList instance
     */
    private TaskManager () {
        ui = new DukeUI();
        taskList = new ArrayList<>();
    }

    /**
     * Getter for current instance
     * Else, instantiate new instance
     **/
    public static synchronized TaskManager getInstance( ) {
        if (taskManager == null)
            taskManager = new TaskManager();
        return taskManager;
    }

    /**
     * List all tasks in taskList
     *
     * @throws UnknownCommandException if taskList is empty
     **/
    public void listTask() {
        try {
            String list;
            if (!taskList.isEmpty()) {
                list = Messages.REPLY_LIST;
                for (int i = 0; i < taskList.size(); i++) {
                    if (i != 0) {
                        list = list.concat("\n");
                    }
                    list = list.concat(String.valueOf(i + 1)).concat(".");
                    list = list.concat(String.valueOf(taskList.get(i)));
                }
            }
            else {
                throw new UnknownCommandException(UnknownCommandException.EMPTY);
            }
            ui.showToUser(list);
        } catch (UnknownCommandException e) {
            ui.showToUser(e.getMessage());
        }
    }

    /**
     * Getter for amount of tasks in taskList
     *
     * @return task amount in string
     **/
    public String getTaskAmount() {
        return "You have " + taskList.size() + " tasks in your list.";
    }

    /**
     * Add task to taskList
     * Categorize based on input
     *
     * @param isSilent used during loading of file, true if adding of task is to be silent (no success message)
     * @param type type of task (todo, event, deadline)
     * @param isDone whether task is done
     * @param input user input
     *
     * @return true if task is added, else false
     *
     * @throws InvalidTaskException if input is empty, contains invalid data or in invalid format
     * @throws IllegalArgumentException if input not on command list
     * @throws DateTimeParseException if input of event/deadline not in valid datetime form
     **/
    public boolean addToTaskList(boolean isSilent, EnumTask type, boolean isDone, String input) {
        try {
            String[] commandList;
            switch (type) {
                case TODO:
                    if (input.isEmpty()) throw new InvalidTaskException(InvalidTaskException.REPLY_TODO_EMPTY);
                    taskList.add(new Todo(input, isDone));
                    break;
                case DEADLINE:
                    if (!input.contains(" /by ")) throw new InvalidTaskException(InvalidTaskException.REPLY_DEADLINE_INVALID_DATE);
                    commandList = input.split(" /by ");
                    if (commandList[0].isEmpty()) throw new InvalidTaskException(InvalidTaskException.REPLY_DEADLINE_INVALID_DESC);
                    if (commandList.length != 2) throw new InvalidTaskException(InvalidTaskException.REPLY_DEADLINE_INVALID_LENGTH);
                    taskList.add(new Deadline(commandList[0].trim(), isDone, commandList[1].trim()));
                    break;
                case EVENT:
                    if (!input.contains(" /at ")) throw new InvalidTaskException(InvalidTaskException.REPLY_EVENT_INVALID_DATE);
                    commandList = input.split(" /at ");
                    if (commandList[0].isEmpty()) throw new InvalidTaskException(InvalidTaskException.REPLY_EVENT_INVALID_DESC);
                    if (commandList.length != 2) throw new InvalidTaskException(InvalidTaskException.REPLY_EVENT_INVALID_LENGTH);
                    taskList.add(new Event(commandList[0].trim(), isDone, commandList[1].trim()));
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            if (!isSilent) {
                ui.showToUser(Messages.REPLY_ADD_TASK + taskList.get(taskList.size()-1) + "\n" + getTaskAmount());
            }
            return true;
        } catch (InvalidTaskException e) {
            ui.showToUser(e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            ui.showToUser(UnknownCommandException.ERROR);
            return false;
        } catch (DateTimeParseException e) {
            ui.showToUser(InvalidTaskException.REPLY_INVALID_DATE_FORMAT);
            return false;
        }
    }

    /**
     * Mark task of specified index
     *
     * @param command whether 'mark' or 'unmark'
     * @param taskIndex index of task in taskList
     *
     * @return boolean true if task is marked/unmarked, else false
     *
     * @throws UnknownCommandException if task index is invalid or in invalid form
     **/
    public boolean markTask(String command, int taskIndex) {
        boolean success = false;
        try {
            if (taskList.isEmpty()) {
                throw new UnknownCommandException(UnknownCommandException.EMPTY);
            }
            if (taskIndex == 0 || taskIndex<0 || taskList.size()<taskIndex) {
                throw new UnknownCommandException();
            }
            taskIndex--;
            String reply = "";
            if (command.equals("mark")) {
                success = taskList.get(taskIndex).markAsDone();
                if (success) {
                    reply = Messages.REPLY_MARK_TASK;
                } else {
                    reply = Messages.REPLY_ALR_MARK_TASK;
                }
            }
            else if (command.equals("unmark")) {
                success = taskList.get(taskIndex).markAsUndone();
                if (success) {
                    reply = Messages.REPLY_UNMARK_TASK;
                } else {
                    reply = Messages.REPLY_ALR_UNMARK_TASK;
                }
            }

            reply = reply.concat("\n\t").concat(taskList.get(taskIndex).toString());
            ui.showToUser(reply);
            return success;
        } catch (UnknownCommandException e) {
            ui.showToUser(e.getMessage());
            return false;
        }
    }

    /**
     * Delete task of specified task index
     *
     * @param taskIndex index of task to be deleted
     *
     * @return boolean true if task is deleted, else false
     *
     * @throws UnknownCommandException if task index is invalid or in invalid form
     **/
    public boolean deleteTask(int taskIndex) {
        try {
            if (taskList.isEmpty()) {
                throw new UnknownCommandException(UnknownCommandException.EMPTY);
            }
            if (taskIndex == 0 || taskIndex < 0 || taskList.size()<taskIndex) {
                throw new UnknownCommandException();
            }
            taskIndex--;
            String output = Messages.REPLY_DELETE_TASK + taskList.get(taskIndex).toString() + "\n";
            taskList.remove(taskIndex);
            output += getTaskAmount();
            ui.showToUser(output);
            return true;
        } catch (UnknownCommandException e) {
            ui.showToUser(e.getMessage());
            return false;
            }
        }

    /**
     * Calls TaskEncoder.encodeTaskList to encode tasks
     *
     * @return string ArrayList with encoded tasks
     **/
    public ArrayList<String> getAllTask() {
        return TaskEncoder.encodeTaskList(taskList);
    }

}

