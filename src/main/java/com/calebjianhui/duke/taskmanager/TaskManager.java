package com.calebjianhui.duke.taskmanager;

import com.calebjianhui.duke.common.Pair;
import com.calebjianhui.duke.enums.ListCommandType;
import com.calebjianhui.duke.enums.TaskDateStructure;
import com.calebjianhui.duke.enums.TaskType;
import com.calebjianhui.duke.enums.UpdateCommandType;
import com.calebjianhui.duke.parser.DateParser;
import com.calebjianhui.duke.parser.TaskEncoder;
import com.calebjianhui.duke.taskmanager.exceptions.InvalidIndexException;
import com.calebjianhui.duke.taskmanager.exceptions.InvalidTaskInputException;
import com.calebjianhui.duke.taskmanager.exceptions.NoChangesException;
import com.calebjianhui.duke.ui.DukeUI;
import com.calebjianhui.duke.ui.Messages;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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

    /**
     * TaskManager constructor
     * - Private constructor to hold a singleton design.
     * - Create a new instance of DukeUI and ArrayList
     */
    private TaskManager () {
        ui = new DukeUI();
        taskList = new ArrayList<>();
    }

    /**
     * Singleton method to get the current instance, else instantiate a new instance
     **/
    public static synchronized TaskManager getInstance( ) {
        if (taskManager == null)
            taskManager = new TaskManager();
        return taskManager;
    }

    /**
     * Get a set of all possible task type alias
     *
     * @return A set containing the alias of all task type
     **/
    public Set<String> getAllPossibleTypes() {
        return new HashSet<>(Arrays.asList(ToDos.TYPE_INDICATOR, Event.TYPE_INDICATOR, Deadline.TYPE_INDICATOR, FixedDurationTask.TYPE_INDICATOR));
    }

    /**
     * Decode the task type based on the given task type alias
     *
     * @param alias TaskType alias
     * @return TaskType based on the given alias
     * @throws AssertionError If an invalid task type alias is given
     **/
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
     **/
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
     * Formatted string to display all DateModule task in the provided task list
     *
     * @param dateTasks Task list (containing DateModule type task only)
     * @return Formatted String of tasks in the given task list
     * @throws AssertionError Should a non-DateModule task be provided
     **/
    private String formatDateModuleTasks(ArrayList<Task> dateTasks) {
        StringBuilder scheduleTaskDetails = new StringBuilder();
        // Pair<T, U>, T = sorted, U = unsorted
        Pair<ArrayList<Task>, ArrayList<Task>> sortedTask = new Pair<>(new ArrayList<>(), new ArrayList<>());
        for (Task currentDateTask: dateTasks) {
            if (((DateModule) currentDateTask).getDateStructure().getFirst().equals(TaskDateStructure.UNSTRUCTURED_DATE_STRING)) {
                sortedTask.getSecond().add(currentDateTask);
            } else if (((DateModule) currentDateTask).getDateStructure().getFirst().equals(TaskDateStructure.VALID_DATE)) {
                sortedTask.getFirst().add(currentDateTask);
            } else {
                // TaskDateStructure should only consist of the above, therefore throw AssertionError
                String errorMessage = "Invalid TaskDateStructure received";
                assert false : errorMessage;
                throw new AssertionError(errorMessage);
            }
        }
        // Sort
        sortedTask.getFirst().sort(Comparator.comparing(o -> ((DateModule) o).getDateStructure().getSecond()));
        // Display tasks
        int i = 1;
        for (Task currentTask : sortedTask.getFirst()) {
            scheduleTaskDetails.append("\n\t").append(i).append(". [")
                    .append(((DateModule) currentTask).getDate(true)).append("] ")
                    .append("[").append(currentTask.getType()).append("] ")
                    .append(((DateModule) currentTask).getDescription(false));
            i++;
        }
        for (Task currentTask : sortedTask.getSecond()) {
            scheduleTaskDetails.append("\n\t").append(i).append(". [NA] ")
                    .append("[").append(currentTask.getType()).append("] ")
                    .append(currentTask.getDescription());
            i++;
        }
        return scheduleTaskDetails.toString();
    }

    /**
     * List all the task in the task queue
     * - Calls another method indicating a normal listing of task
     **/
    public void listTask() {
        listTask(new Pair<>(ListCommandType.NORMAL, ""));
    }

    /**
     * List all the task in the task queue
     * - Overloaded variant to have detailed listing of tasks
     *
     * @param listType Based on the given list command, display the relevant listing
     * @throws AssertionError Should an invalid ListCommandType be received
     * @see ListCommandType For detailing explanation of type of listing view
     **/
    public void listTask(Pair<ListCommandType, String> listType) {
        // Check if task list is empty
        if (taskList.isEmpty()) {
            ui.formatDukeReply(InvalidIndexException.REPLY_NO_ONGOING_TASK);
            return;
        }

        // Display tasks list based on user selected viewing preference
        switch (listType.getFirst()) {
            case NORMAL:
                ui.formatDukeReply("These are your current task:\n" + getTaskListString(taskList));
                break;
            case SCHEDULE:
                StringBuilder scheduleTaskDetails = new StringBuilder();
                ArrayList<Task> dateTasks = new ArrayList<>();
                if (listType.getSecond().isEmpty()) {
                    // All Events / Deadlines
                    for (Task current: taskList) {
                        if (current instanceof DateModule) {
                            dateTasks.add(current);
                        }
                    }
                    if (dateTasks.isEmpty()) {
                        scheduleTaskDetails.append("You have no upcoming tasks.");
                    } else {
                        scheduleTaskDetails.append("You have the following upcoming tasks:");
                    }
                } else {
                    Pair<TaskDateStructure, LocalDateTime> givenDate = DateParser.parseDateTimeString(listType.getSecond());
                    if (givenDate.getFirst().equals(TaskDateStructure.UNSTRUCTURED_DATE_STRING)) {
                        ui.formatDukeReply(InvalidTaskInputException.REPLY_LIST_INVALID_DATE);
                    }
                    // Events / Deadlines on specific date
                    for (Task current: taskList) {
                        if (current instanceof DateModule &&
                                ((DateModule) current).getDateStructure().getFirst().equals(TaskDateStructure.VALID_DATE)) {
                            if (((DateModule) current).getDateStructure().getSecond().toLocalDate().equals(givenDate.getSecond().toLocalDate())) {
                                dateTasks.add(current);
                            }
                        }
                    }
                    if (dateTasks.isEmpty()) {
                        scheduleTaskDetails.append("You have no upcoming task on ")
                                .append(listType.getSecond()).append(".");
                    } else {
                        scheduleTaskDetails.append("You have the following upcoming tasks on ")
                                .append(listType.getSecond()).append(":");
                    }
                }
                if (!(dateTasks.isEmpty())) {
                    scheduleTaskDetails.append(formatDateModuleTasks(dateTasks));
                }
                ui.formatDukeReply(scheduleTaskDetails.toString());
                break;
            default:
                // ListCommandType should only consist of the above, therefore throw AssertionError
                String errorMessage = "Invalid ListCommandType received";
                assert false : errorMessage;
                throw new AssertionError(errorMessage);
        }
    }

    /**
     * Find all task with relations to a keyword
     *
     * @param isCharacterSearch Whether to perform a character search or word search
     * @param keyword Keyword to search for
     **/
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
     **/
    public ArrayList<String> getAllTask() {
        return TaskEncoder.encodeTaskList(taskList);
    }

    /**
     * Return the current amount of task
     *
     * @return Formatted string containing the amount of task
     **/
    private String getTaskAmount() {
        return "You currently have " + taskList.size() + " task in your list.";
    }

    /**
     * Return the full details of a specific task
     *
     * @param selected The selected task
     * @return String containing the task details
     **/
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
     * @param isSilent Should there be any results displayed to the user
     * @param type Type of task to be added
     * @param isDone Whether the task is marked as done
     * @param command Task to be added
     * @return Should there be any changes made to the task list
     **/
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
     * @param details Details to update to the new field
     * @return Should there be any changes made to the task list
     **/
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
     * @return Should there be any changes made to the task list
     **/
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
                taskList.add(new Deadline(selected.getDoneStatus(), ((Deadline) selected).getDescription(false), ((Deadline) selected).getDate(false)));
            } else if (selected instanceof Event) {
                taskList.add(new Event(selected.getDoneStatus(), ((Event) selected).getDescription(false), ((Event) selected).getDate(false)));
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
      * Delete all tasks or a single task based on the index
      *
      * @param isDeleteAll Whether to delete all tasks
      * @param index Index of the task in the arraylist
      * @return Should there be any changes made to the task list
      **/
    public boolean deleteTask(boolean isDeleteAll, int index) {
        try {
            // Check if task list is empty
            if (taskList.isEmpty()) {
                throw new InvalidIndexException(InvalidIndexException.REPLY_NO_ONGOING_TASK);
            }

            StringBuilder output = new StringBuilder();
            if (isDeleteAll) {
                taskList.clear();
                output.append(Messages.REPLY_DELETE_ALL_TASK);
            } else {
                // Check if selected index is valid
                if (index < 0 || index >= taskList.size()) {
                    throw new InvalidIndexException(InvalidIndexException.REPLY_INVALID_INDEX);
                }
                // Delete task and send reply
                output.append(Messages.REPLY_DELETE_TASK).append(getTaskDetails(taskList.get(index))).append("\n");
                taskList.remove(index);
                output.append(getTaskAmount());
            }
            ui.formatDukeReply(output.toString());
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
