package com.calebjianhui.duke.taskmanager;

import com.calebjianhui.duke.enums.TaskDateStructure;
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
        return new HashSet<>(Arrays.asList(ToDos.TYPE_INDICATOR, Event.TYPE_INDICATOR, Deadline.TYPE_INDICATOR));
    }

    /**
     * Decode the given alias
     * **/
    public String decodeTypeAlias(String alias) {
        if (ToDos.TYPE_INDICATOR.equals(alias)) {
            return TaskType.TODO.toString();
        } else if (Event.TYPE_INDICATOR.equals(alias)) {
            return TaskType.EVENT.toString();
        } else if (Deadline.TYPE_INDICATOR.equals(alias)) {
            return TaskType.DEADLINE.toString();
        } else {
            return "";
        }
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

            String allTask = "These are your current task:\n";
            for (int i = 0; i < taskList.size(); i++) {
                if (i != 0) {
                    allTask = allTask.concat("\n");
                }
                allTask = allTask.concat(String.valueOf(i+1)).concat(".");
                allTask = allTask.concat(getTaskDetails(taskList.get(i)));
            }
            ui.formatDukeReply(allTask);
        } catch (InvalidIndexException e) {
            ui.formatDukeReply(e.getMessage());
        }
    }

    /**
     * Retrieve the tasklist in string format for encoding purposes
     *
     * @return Arraylist containing all the task in Pair<S, T> format
     * S: Type
     * T: description
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
                    if (!command.contains(Deadline.COMMAND_SEPARATOR)) throw new InvalidTaskInputException(InvalidTaskInputException.REPLY_DEADLINE_NO_DATE);
                    // Else, proceed to add deadline task
                    commandList = command.split(Deadline.COMMAND_SEPARATOR);
                    // - Terminate should there be description and date input
                    if (commandList.length != 2) throw new InvalidTaskInputException(InvalidTaskInputException.REPLY_DEADLINE_INVALID_LENGTH);
                    // Add deadline task
                    taskList.add(new Deadline(isDone, commandList[0], commandList[1]));
                    break;
                case EVENT:
                    // Terminate should there be no date input
                    if (!command.contains(Event.COMMAND_SEPARATOR)) throw new InvalidTaskInputException(InvalidTaskInputException.REPLY_EVENT_NO_DATE);
                    // Else, proceed to add event task
                    commandList = command.split(Event.COMMAND_SEPARATOR);
                    // - Terminate should there be description and date input
                    if (commandList.length != 2) throw new InvalidTaskInputException(InvalidTaskInputException.REPLY_EVENT_INVALID_LENGTH);
                    // Add event task
                    taskList.add(new Event(isDone, commandList[0], commandList[1]));
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            if (!isSilent) ui.formatDukeReply(Messages.REPLY_ADD_TASK + getTaskDetails(taskList.get(taskList.size()-1)) + "\n" + getTaskAmount());
            return true;
        } catch (InvalidTaskInputException e) {
            if (!isSilent) ui.formatDukeReply(e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            if (!isSilent) ui.formatDukeReply(Messages.UNKNOWN_ERROR);
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
            if (updateFieldType.equals(UpdateCommandType.MARK) || updateFieldType.equals(UpdateCommandType.UNMARK)) {
                // Edit Mark / Unmark Status
                boolean isMark = updateFieldType.equals(UpdateCommandType.MARK);
                if (selected.getDoneStatus() == isMark) {
                    throw new NoChangesException();
                }
                // Set Status
                selected.setDoneStatus(isMark);
                // Show updated status
                String reply = isMark ? Messages.REPLY_UPDATE_MARK_TASK : Messages.REPLY_UPDATE_UNMARK_TASK;
                reply = reply.concat("\n\t").concat(getTaskDetails(selected));
                ui.formatDukeReply(reply);
            } else if (updateFieldType.equals(UpdateCommandType.EDIT_DATE)) {
                // Edit date
                if (selected.getDateStructure().equals(TaskDateStructure.NO_DATE)) {
                    throw new InvalidTaskInputException(InvalidTaskInputException.REPLY_TASK_NO_DATE);
                }
                selected.setDate(details);
                // Show updated status
                ui.formatDukeReply(Messages.REPLY_UPDATE_DATE);
            } else if (updateFieldType.equals(UpdateCommandType.EDIT_DESCRIPTION)) {
                // Edit description
                selected.setDescription(details);
                // Show updated status
                ui.formatDukeReply(Messages.REPLY_UPDATE_MESSAGE);
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
