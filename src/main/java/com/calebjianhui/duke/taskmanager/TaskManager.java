package com.calebjianhui.duke.taskmanager;

import com.calebjianhui.duke.enums.TaskType;
import com.calebjianhui.duke.enums.UpdateCommandType;
import com.calebjianhui.duke.parser.TaskEncoder;
import com.calebjianhui.duke.taskmanager.exceptions.InvalidIndexException;
import com.calebjianhui.duke.taskmanager.exceptions.InvalidTaskInputException;
import com.calebjianhui.duke.taskmanager.exceptions.NoChangesException;
import com.calebjianhui.duke.ui.DukeUI;
import com.calebjianhui.duke.ui.Messages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * Singleton creation of task manager
 * - Contains all task
 * - Ensure that only one version of task manager exist at a time (Singleton)
 */
public class TaskManager {
    private static TaskManager taskManager;
    private final DukeUI ui;
    private final ArrayList<Task> taskList;

    private TaskManager () {
        ui = new DukeUI();
        taskList = new ArrayList<>();
    }

    /**
     * Singleton method to get the current instance, else instantiate a new instance
     * **/
    public static synchronized TaskManager getInstance( ) {
        if (taskManager == null)
            taskManager = new TaskManager();
        return taskManager;
    }

    /**
     * Get a set of all possible type alias
     * **/
    public Set<String> getAllPossibleTypes() {
        return new HashSet<>(Arrays.asList(ToDos.TYPE_INDICATOR, Event.TYPE_INDICATOR, Deadline.TYPE_INDICATOR, FixedDurationTask.TYPE_INDICATOR));
    }

    /**
     * Decode the task type based on the given alias
     * **/
    public TaskType decodeTypeAlias(String alias) {
        if (ToDos.TYPE_INDICATOR.equals(alias)) {
            return TaskType.TODO;
        } else if (Event.TYPE_INDICATOR.equals(alias)) {
            return TaskType.EVENT;
        } else if (Deadline.TYPE_INDICATOR.equals(alias)) {
            return TaskType.DEADLINE;
        } else if (FixedDurationTask.TYPE_INDICATOR.equals(alias)) {
            return TaskType.FIXED_DURATION;
        } else {
            // TaskType should only consist of the above, therefore throw AssertionError
            String errorMessage = "Invalid TaskType received.";
            assert false : errorMessage;
            throw new AssertionError(errorMessage);
        }
    }

    /**
     * Get a string containing all the task in the given task list
     *
     * @param inputTaskList Task list to print from
     * @return Formatted String of tasks in task list
     * **/
    private String getTaskListString(ArrayList<Task> inputTaskList) {
        String output = "";
        for (int i = 0; i < inputTaskList.size(); i++) {
            if (i != 0) {
                output = output.concat("\n");
            }
            output = output.concat(String.valueOf(i+1)).concat(".");
            output = output.concat(getTaskDetails(inputTaskList.get(i)));
        }
        return output;
    }

    /**
     * List all the task in the task queue
     * **/
    public void listTask() {
        try {
            // Check if task list is empty
            if (taskList.isEmpty()) {
                throw new InvalidIndexException(InvalidIndexException.REPLY_NO_ONGOING_TASK);
            }
            String taskDetails = "These are your current task:\n".concat(getTaskListString(taskList));
            ui.formatDukeReply(taskDetails);
        } catch (InvalidIndexException e) {
            ui.formatDukeReply(e.getMessage());
        }
    }

    /**
     * Find all task with relations to a keyword
     *
     * @param keyword Keyword to search for
     * **/
    public void findTask(boolean isCharacterSearch, String keyword) {
        try {
            // Check if task list is empty
            if (taskList.isEmpty()) {
                throw new InvalidIndexException(InvalidIndexException.REPLY_NO_ONGOING_TASK);
            }

            // Filter task list
            ArrayList<Task> filteredTaskList = new ArrayList<>();
            for (Task task: taskList) {
                if (task.containsKeyword(isCharacterSearch, keyword)) {
                    filteredTaskList.add(task);
                }
            }

            // Check if task list is empty
            if (filteredTaskList.isEmpty()) {
                throw new InvalidIndexException(InvalidIndexException.REPLY_FILTERED_EMPTY_TASK);
            } else {
                String taskDetails = "We have managed to find these matching tasks:\n".concat(getTaskListString(filteredTaskList));
                ui.formatDukeReply(taskDetails);
            }
        } catch (InvalidIndexException e) {
            ui.formatDukeReply(e.getMessage());
        }
    }

    /**
     * Retrieve the tasklist in string format for encoding purposes
     *
     * @return Arraylist containing all the task in a formatted String format.
     * **/
    public ArrayList<String> getAllTask() {
        return TaskEncoder.encodeTaskList(taskList);
    }

    /**
     * Return the current amount of task
     *
     * @return String containing the amount of task
     * **/
    private String getTaskAmount() {
        return "You currently have " + taskList.size() + " task in your list.";
    }

    /**
     * Return the full details of a specific task
     *
     * @param selected The selected task
     * @return String containing the task details
     * **/
    private static String getTaskDetails(Task selected) {
        // Type
        String details = "[".concat(selected.getType()).concat("]");
        // Status
        details = details.concat("[").concat(selected.getDoneStatus() ? "X] " : " ] ");
        // Description
        details = details.concat(selected.getDescription());
        return details;
    }

    /**
     * Add a task to the task list
     *
     * @param type Type of task to be added
     * @param command Task to be added
     *
     * @return Should the action be successful
     * **/
    public boolean addToTaskList(boolean isSilent, TaskType type, boolean isDone, String command) {
        try {
            String[] commandList;
            switch (type) {
                case TODO:
                    // Add task
                    taskList.add(new ToDos(isDone, command));
                    break;
                case DEADLINE:
                    // Terminate should there be no date input
                    if (!command.contains(Deadline.COMMAND_SEPARATOR)) {
                        throw new InvalidTaskInputException(InvalidTaskInputException.REPLY_DEADLINE_NO_DATE);
                    }
                    // Else, proceed to add deadline task
                    commandList = command.split(Deadline.COMMAND_SEPARATOR);
                    // - Terminate should there not be description and date input
                    if (commandList.length != 2) {
                        throw new InvalidTaskInputException(InvalidTaskInputException.REPLY_DEADLINE_INVALID_LENGTH);
                    }
                    // Add deadline task
                    taskList.add(new Deadline(isDone, commandList[0], commandList[1]));
                    break;
                case EVENT:
                    // Terminate should there be no date input
                    if (!command.contains(Event.COMMAND_SEPARATOR)) {
                        throw new InvalidTaskInputException(InvalidTaskInputException.REPLY_EVENT_NO_DATE);
                    }
                    // Else, proceed to add event task
                    commandList = command.split(Event.COMMAND_SEPARATOR);
                    // - Terminate should there not be description and date input
                    if (commandList.length != 2) {
                        throw new InvalidTaskInputException(InvalidTaskInputException.REPLY_EVENT_INVALID_LENGTH);
                    }
                    // Add event task
                    taskList.add(new Event(isDone, commandList[0], commandList[1]));
                    break;
                case FIXED_DURATION:
                    // Terminate should there be no duration input
                    if (!command.contains(FixedDurationTask.COMMAND_SEPARATOR)) {
                        throw new InvalidTaskInputException(InvalidTaskInputException.REPLY_FIXED_DURATION_NO_DURATION);
                    }
                    // Else, proceed to add fixed duration task
                    commandList = command.split(FixedDurationTask.COMMAND_SEPARATOR);
                    // - Terminate should there not be description and duration input
                    if (commandList.length != 2) {
                        throw new InvalidTaskInputException(InvalidTaskInputException.REPLY_FIXED_DURATION_INVALID_LENGTH);
                    }
                    // Add fixed duration task
                    taskList.add(new FixedDurationTask(isDone, commandList[0], commandList[1]));
                    break;
                default:
                    // TaskType should only consist of the above, therefore throw AssertionError
                    String errorMessage = "Invalid TaskType received";
                    assert false : errorMessage;
                    throw new AssertionError(errorMessage);
            }
            if (!isSilent) {
                ui.formatDukeReply(Messages.REPLY_ADD_TASK + getTaskDetails(taskList.get(taskList.size()-1)) + "\n" + getTaskAmount());
            }
            return true;
        } catch (InvalidTaskInputException e) {
            if (!isSilent) {
                ui.formatDukeReply(e.getMessage());
            }
            return false;
        }
    }

    /**
     * Update the task
     *
     * @param updateFieldType Field to update
     * @param index Index of selected task
     *
     * @return Should the action be successful
     * **/
    public boolean updateTask(UpdateCommandType updateFieldType, int index, String details) {
        try {
            // Check if task list is empty
            if (taskList.isEmpty()) {
                throw new InvalidIndexException(InvalidIndexException.REPLY_NO_ONGOING_TASK);
            }

            // Check if selected index is valid
            if (index < 0 || index >= taskList.size()) {
                throw new InvalidIndexException(InvalidIndexException.REPLY_INVALID_INDEX);
            }

            // Get & verify current status
            Task selected = taskList.get(index);
            // Determine which fields to update
            switch (updateFieldType) {
                case MARK:
                case UNMARK:
                    // Edit Mark / Unmark status
                    boolean isMark = updateFieldType.equals(UpdateCommandType.MARK);
                    if (selected.getDoneStatus() == isMark) {
                        throw new NoChangesException();
                    }
                    // Set status
                    selected.setDoneStatus(isMark);
                    // Show updated status
                    String reply = isMark ? Messages.REPLY_UPDATE_MARK_TASK : Messages.REPLY_UPDATE_UNMARK_TASK;
                    reply = reply.concat("\n\t").concat(getTaskDetails(selected));
                    ui.formatDukeReply(reply);
                    break;
                case EDIT_DATE:
                    // Edit date
                    if (!(selected instanceof DateModule)) {
                        throw new InvalidTaskInputException(InvalidTaskInputException.REPLY_TASK_NO_DATE);
                    }
                    ((DateModule) selected).setDate(details);
                    // Show updated status
                    ui.formatDukeReply(Messages.REPLY_UPDATE_DATE);
                    break;
                case EDIT_DESCRIPTION:
                    // Edit description
                    selected.setDescription(details);
                    // Show updated status
                    ui.formatDukeReply(Messages.REPLY_UPDATE_MESSAGE);
                    break;
                default:
                    // UpdateCommandType should only consist of the above, therefore throw AssertionError
                    String errorMessage = "Invalid UpdateCommandType received";
                    assert false : errorMessage;
                    throw new AssertionError(errorMessage);
            }
            return true;
        } catch (InvalidIndexException e) {
            ui.formatDukeReply(e.getMessage());
            if (e.getMessage().equals(InvalidIndexException.REPLY_INVALID_INDEX)) {
                listTask();
            }
            return false;
        } catch (NoChangesException e) {
            ui.formatDukeReply(e.getMessage());
            listTask();
            return false;
        } catch (InvalidTaskInputException e) {
            ui.formatDukeReply(e.getMessage());
            return false;
        }
    }

    /**
     * Clone a task based on the index
     *
     * @param index Index of the task in the arraylist
     *
     * @return Should the action be successful
     * **/
    public boolean cloneTask(int index) {
        try {
            // Check if task list is empty
            if (taskList.isEmpty()) {
                throw new InvalidIndexException(InvalidIndexException.REPLY_NO_ONGOING_TASK);
            }

            // Check if selected index is valid
            if (index < 0 || index >= taskList.size()) {
                throw new InvalidIndexException(InvalidIndexException.REPLY_INVALID_INDEX);
            }

            // Clone a task
            Task selected = taskList.get(index);
            if (selected instanceof ToDos) {
                taskList.add(new ToDos(selected.getDoneStatus(), selected.getDescription()));
            } else if (selected instanceof Deadline) {
                taskList.add(new Deadline(selected.getDoneStatus(), ((Deadline) selected).getDescription(false), ((Deadline) selected).getDate()));
            } else if (selected instanceof Event) {
                taskList.add(new Event(selected.getDoneStatus(), ((Event) selected).getDescription(false), ((Event) selected).getDate()));
            } else if (selected instanceof FixedDurationTask) {
                taskList.add(new FixedDurationTask(selected.getDoneStatus(), ((FixedDurationTask) selected).getDescription(false), ((FixedDurationTask) selected).getDuration()));
            } else {
                // Type of tasks should only consist of the above, therefore throw AssertionError
                String errorMessage = "Unable to clone task due to invalid task type received.";
                assert false : errorMessage;
                throw new AssertionError(errorMessage);
            }

            ui.formatDukeReply(Messages.REPLY_CLONE_SUCCESS + getTaskDetails(taskList.get(taskList.size()-1)) + "\n" + getTaskAmount());
            return true;
        } catch (InvalidIndexException e) {
            ui.formatDukeReply(e.getMessage());
            if (e.getMessage().equals(InvalidIndexException.REPLY_INVALID_INDEX)) {
                listTask();
            }
            return false;
        }
    }

    /**
      * Delete a task based on the index
      *
      * @param index Index of the task in the arraylist
     *
     * @return Should the action be successful
      * **/
    public boolean deleteTask(int index) {
        try {
            // Check if task list is empty
            if (taskList.isEmpty()) {
                throw new InvalidIndexException(InvalidIndexException.REPLY_NO_ONGOING_TASK);
            }

            // Check if selected index is valid
            if (index < 0 || index >= taskList.size()) {
                throw new InvalidIndexException(InvalidIndexException.REPLY_INVALID_INDEX);
            }

            // Delete task and send reply
            String output = Messages.REPLY_DELETE_TASK + getTaskDetails(taskList.get(index)) + "\n";
            taskList.remove(index);
            output += getTaskAmount();
            ui.formatDukeReply(output);
            return true;
        } catch (InvalidIndexException e) {
            ui.formatDukeReply(e.getMessage());
            if (e.getMessage().equals(InvalidIndexException.REPLY_INVALID_INDEX)) {
                listTask();
            }
            return false;
        }
    }

}