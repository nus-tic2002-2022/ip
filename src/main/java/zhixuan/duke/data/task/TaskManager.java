package zhixuan.duke.data.task;

import zhixuan.duke.common.EnumDateTime;
import zhixuan.duke.data.exceptions.InvalidFileException;
import zhixuan.duke.parser.DateParser;
import zhixuan.duke.storage.StorageFile;
import zhixuan.duke.storage.TaskEncoder;
import zhixuan.duke.ui.DukeUI;
import zhixuan.duke.common.EnumTask;
import zhixuan.duke.data.exceptions.InvalidTaskException;
import zhixuan.duke.data.exceptions.UnknownCommandException;
import zhixuan.duke.common.Messages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Task manager to make changes to taskList
 *
 **/
public class TaskManager {

    private static TaskManager taskManager;
    private final DukeUI ui;
    private final ArrayList<Task> taskList;

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
            if (taskList.isEmpty()) {
                throw new UnknownCommandException(UnknownCommandException.EMPTY);
            }
            list = Messages.REPLY_LIST;
            for (int i = 0; i < taskList.size(); i++) {
                if (i != 0) {
                    list = list.concat("\n");
                }
                list = list.concat(String.valueOf(i + 1)).concat(".");
                list = list.concat(String.valueOf(taskList.get(i)));
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
     * @throws ArrayIndexOutOfBoundsException if loaded file has invalid data
     * @throws AssertionError if receive unexpected type
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
                    String errorMessage = "Invalid task type.";
                    assert false : errorMessage;
                    throw new AssertionError(errorMessage);
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
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showToUser(InvalidFileException.FILE_ERROR);
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
        boolean isSuccessful = false;
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
                isSuccessful = taskList.get(taskIndex).markAsDone();
                if (isSuccessful) {
                    reply = Messages.REPLY_MARK_TASK;
                } else {
                    reply = Messages.REPLY_ALR_MARK_TASK;
                }
            }
            else if (command.equals("unmark")) {
                isSuccessful = taskList.get(taskIndex).markAsUndone();
                if (isSuccessful) {
                    reply = Messages.REPLY_UNMARK_TASK;
                } else {
                    reply = Messages.REPLY_ALR_UNMARK_TASK;
                }
            }

            reply = reply.concat("\n").concat(taskList.get(taskIndex).toString());
            ui.showToUser(reply);
            return isSuccessful;
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
            String reply = Messages.REPLY_DELETE_TASK + taskList.get(taskIndex).toString() + "\n";
            taskList.remove(taskIndex);
            reply += getTaskAmount();
            ui.showToUser(reply);
            return true;
        } catch (UnknownCommandException e) {
            ui.showToUser(e.getMessage());
            return false;
        }
    }

    /**
     * Checks if input is in valid date format (yyyy-MM-dd)
     *
     * @param input String to be checked
     *
     * @throws ParseException if input is not in date format
     **/
    public boolean isDateFormat(String input) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(input.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    /**
     * Find task of specified date
     *
     * @param dateTime date to be searched
     *
     * @throws UnknownCommandException if list is empty or has invalid characters
     * @throws DateTimeParseException if date is not in correct format
     **/
    public void findTaskWithDate(String dateTime) {
        try {
            String list = "";
            LocalDateTime parsedDateTime = DateParser.parseStringToDateTime(dateTime);
            if (taskList.isEmpty()) {
                throw new UnknownCommandException(UnknownCommandException.EMPTY);
            }
            for (Task task : taskList) {
                if (!(task instanceof Todo) && DateParser.compareDateTime(parsedDateTime, task.getDueDate())) {
                    list = list.concat(task.toString());
                }
            }
            if (list.isEmpty()) {
                ui.showToUser(Messages.REPLY_NO_TASK_FOUND);
            } else {
                ui.showToUser(Messages.REPLY_TASK_FOUND + list);
            }
        } catch (UnknownCommandException e) {
            ui.showToUser(e.getMessage());
        } catch (DateTimeParseException e) {
            ui.showToUser(InvalidTaskException.REPLY_INVALID_DATE_FORMAT);
        }
    }

    /**
     * Find task with specified keyword
     * Calls isDateFormat and findTaskWithDate if input is date
     *
     * @param input String to be checked
     **/
    public void findTask (String input) {
        if (isDateFormat(input)) {
            findTaskWithDate(input);
        } else {
            try {
                String list = "";
                if (taskList.isEmpty()) {
                    throw new UnknownCommandException(UnknownCommandException.EMPTY);
                }
                for (Task task : taskList) {
                    if (task.containsKeyword(input)) {
                        list = list.concat(task.toString());
                    }
                }
                if (list.isEmpty()) {
                    ui.showToUser(Messages.REPLY_NO_TASK_FOUND);
                } else {
                    ui.showToUser(Messages.REPLY_TASK_FOUND + list);
                }
            } catch (UnknownCommandException e) {
                ui.showToUser(e.getMessage());
            }
        }
    }

    /**
     * Load new file chosen by user
     *
     * Clears taskList first
     * Calls StorageFile commands
     **/
    public void loadNewFile() {
        taskList.clear();
        if (!StorageFile.loadUserFile()) {
            ui.showToUser(InvalidFileException.FILE_ERROR);
            StorageFile.loadFile(StorageFile.getDefaultLocation());
        } else {
            ui.showToUser(Messages.REPLY_FILE_LOADED);
            listTask();
        }
    }

    /**
     * Snooze (add) due date of given task
     *
     * @param taskIndex index of task to be snoozed
     * @param DateTime type of date/time
     * @param amount amount of time to be added
     *
     * @throws UnknownCommandException if empty or has invalid index
     * @throws InvalidTaskException if given task is a Todo task
     * @throws AssertionError if given date/time type not found
     **/
    public boolean snoozeTask(int taskIndex, EnumDateTime DateTime, int amount) {
        try {
            String reply = "";
            LocalDateTime dueDate;
            if (taskList.isEmpty()) {
                throw new UnknownCommandException(UnknownCommandException.EMPTY);
            }
            if (taskIndex == 0 || taskIndex < 0 || taskList.size()<taskIndex) {
                throw new UnknownCommandException();
            }
            taskIndex--;
            if ((taskList.get(taskIndex) instanceof Todo)) {
                throw new InvalidTaskException(InvalidTaskException.REPLY_INVALID_TASK);
            }
            Task selectedTask = taskList.get(taskIndex);
            dueDate = selectedTask.getDueDate();
            switch (DateTime) {
                case YEAR:
                    selectedTask.setDueDate(dueDate.plusYears(amount));
                    break;
                case MONTH:
                    selectedTask.setDueDate(dueDate.plusMonths(amount));
                    break;
                case DAY:
                    selectedTask.setDueDate(dueDate.plusDays(amount));
                    break;
                case HOUR:
                    selectedTask.setDueDate(dueDate.plusHours(amount));
                    break;
                case MINUTE:
                    selectedTask.setDueDate(dueDate.plusMinutes(amount));
                    break;
                default:
                    String errorMessage = "Invalid date/time type.";
                    assert false : errorMessage;
                    throw new AssertionError(errorMessage);
            }
            reply = reply.concat(Messages.REPLY_DUEDATETIME_ADDED + amount + " " + DateTime + Messages.REPLY_DUEDATETIME_TO);
            ui.showToUser(reply + selectedTask);
            return true;
        } catch (UnknownCommandException e) {
            ui.showToUser(e.getMessage());
            return false;
        } catch (InvalidTaskException e) {
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

