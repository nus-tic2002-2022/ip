package com.calebjianhui.duke.taskmanager;

import com.calebjianhui.duke.enums.TaskType;
import com.calebjianhui.duke.taskmanager.exceptions.InvalidIndexException;
import com.calebjianhui.duke.taskmanager.exceptions.InvalidTaskInputException;
import com.calebjianhui.duke.taskmanager.exceptions.NoChangesException;
import com.calebjianhui.duke.ui.DukeUI;
import com.calebjianhui.duke.ui.Messages;

import java.util.ArrayList;

/**
 * Singleton creation of task manager
 * - Contains all task
 * - Ensure that only one version of task manager exist at a time (Singleton)
 */
public class TaskManager {
    // Generic error message. Should not happen

    private static TaskManager taskManager;
    private final DukeUI ui;
    private ArrayList<Task> taskList;

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
     * List all the task in the task queue
     * **/
    public void listTask() {
        try {
            // Check if task list is empty
            if (taskList.isEmpty()) {
                throw new InvalidIndexException("empty");
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
     * Return the current amount of task
     *
     * @return String containing the amount of task
     * **/
    public String getTaskAmount() {
        return "You currently have " + taskList.size() + " task in your list.";
    }

    /**
     * Return the full details of a specific task
     *
     * @param selected The selected task
     * @return String containing the task details
     * **/
    public static String getTaskDetails(Task selected) {
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
     * **/
    public void addToTaskList(TaskType type, String command) {
        try {
            String[] commandList;
            switch (type) {
                case TODO:
                    // Add task
                    taskList.add(new ToDos(command));
                    break;
                case DEADLINE:
                    // Terminate should there be no date input
                    if (!command.contains(" /by ")) throw new InvalidTaskInputException(InvalidTaskInputException.REPLY_DEADLINE_NO_DATE);
                    // Else, proceed to add deadline task
                    commandList = command.split(" /by ");
                    // - Terminate should there be description and date input
                    if (commandList.length != 2) throw new InvalidTaskInputException(InvalidTaskInputException.REPLY_DEADLINE_INVALID_LENGTH);
                    // Add deadline task
                    taskList.add(new Deadline(commandList[0], commandList[1]));
                    break;
                case EVENT:
                    // Terminate should there be no date input
                    if (!command.contains(" /at ")) throw new InvalidTaskInputException(InvalidTaskInputException.REPLY_EVENT_NO_DATE);
                    // Else, proceed to add deadline task
                    commandList = command.split(" /at ");
                    // - Terminate should there be description and date input
                    if (commandList.length != 2) throw new InvalidTaskInputException(InvalidTaskInputException.REPLY_EVENT_INVALID_LENGTH);
                    // Add deadline task
                    taskList.add(new Event(commandList[0], commandList[1]));
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            ui.formatDukeReply(Messages.REPLY_ADD_TASK + getTaskDetails(taskList.get(taskList.size()-1)) + "\n" + getTaskAmount());
        } catch (InvalidTaskInputException e) {
            ui.formatDukeReply(e.getMessage());
        } catch (IllegalArgumentException e) {
            ui.formatDukeReply(Messages.UNKNOWN_ERROR);
        }
    }


    /**
     * Update a task status
     *
     * @param isMark Determine to mark/unmark task
     * @param index Index of selected task
     * **/
    public void updateTaskStatus(boolean isMark, int index) {
        try {
            // Check if task list is empty
            if (taskList.isEmpty()) {
                throw new InvalidIndexException("empty");
            }

            // Check if selected index is valid
            if (index < 0 || index >= taskList.size()) {
                throw new InvalidIndexException();
            }

            // Get & verify current status
            Task selected = taskList.get(index);
            if (selected.getDoneStatus() == isMark) {
                throw new NoChangesException();
            }
            // Set Status
            selected.setDoneStatus(isMark);
            String reply = isMark ? Messages.REPLY_CONFIRM_MARK_TASK : Messages.REPLY_CONFIRM_UNMARK_TASK;
            reply = reply.concat("\n\t").concat(getTaskDetails(selected));
            ui.formatDukeReply(reply);
        } catch (InvalidIndexException e) {
            ui.formatDukeReply(e.getMessage());
            if (e.getType().equals(InvalidIndexException.OPTION_GENERIC)) {
                listTask();
            }
        } catch (NoChangesException e) {
            ui.formatDukeReply(e.getMessage());
            listTask();
        }
    }

    /**
      * Delete a task based on the index
      *
      * @param index Index of the task in the arraylist
      * **/
    public void deleteTask(int index) {
        try {
            // Check if task list is empty
            if (taskList.isEmpty()) {
                throw new InvalidIndexException("empty");
            }

            // Check if selected index is valid
            if (index < 0 || index >= taskList.size()) {
                throw new InvalidIndexException();
            }

            // Delete task and send reply
            String output = Messages.REPLY_DELETE_TASK + getTaskDetails(taskList.get(index)) + "\n";
            taskList.remove(index);
            output += getTaskAmount();
            ui.formatDukeReply(output);
        } catch (InvalidIndexException e) {
            ui.formatDukeReply(e.getMessage());
            if (e.getType().equals(InvalidIndexException.OPTION_GENERIC)) {
                listTask();
            }
        }
    }

}
