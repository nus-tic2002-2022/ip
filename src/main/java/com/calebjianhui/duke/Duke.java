package com.calebjianhui.duke;

import com.calebjianhui.duke.taskmanager.Deadline;
import com.calebjianhui.duke.taskmanager.Event;
import com.calebjianhui.duke.taskmanager.Task;
import com.calebjianhui.duke.taskmanager.ToDos;
import com.calebjianhui.duke.exceptions.InvalidTaskInputException;
import com.calebjianhui.duke.exceptions.UnknownCommandInputException;
import com.calebjianhui.duke.ui.DukeUI;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // Variables
    static ArrayList<Task> taskList = new ArrayList<>();
    DukeUI ui;

    //================================================================================
    // Commands
    //================================================================================

    /**
     * Check for any terminating words
     *
     * @param input The given string to check
     * @return Boolean value to determine whether to terminate the program (e.g. true to terminate)
     * **/
    public static boolean checkTerminatingWord(String input) {
        return input.equals("bye");
    }

    /**
     * Determine the action to be perform based on user's command
     *
     * @param command Input command from user
     * **/
    private void determineAction(String command) throws UnknownCommandInputException {
        if (command.equals("list")) {
            listTask();
        } else if (command.startsWith("mark") || command.startsWith("unmark")) {
            updateTaskStatus(command);
        } else if (command.startsWith("todo ")) {
            addToTaskList("todo", command.replaceFirst("todo ", ""));
        } else if (command.startsWith("deadline ")) {
            addToTaskList("deadline", command.replaceFirst("deadline ", ""));
        } else if (command.startsWith("event ")) {
            addToTaskList("event", command.replaceFirst("event ", ""));
        }  else if (command.startsWith("delete ")) {
            deleteTask(command.replaceFirst("delete ", ""));
        } else {
            throw new UnknownCommandInputException();
        }
    }

    /**
     * Add a task to the task list
     *
     * @param type Type of task to be added
     * @param command Task to be added
     * **/
    public void addToTaskList(String type, String command) {
        try {
            String task = command;
            if (type.equals("todo")) {
                // Add task
                taskList.add(new ToDos(command));
            } else if (type.equals("deadline")) {
                // Terminate should there be no date input
                if (!command.contains(" /by ")) throw new InvalidTaskInputException("Please include the deadline for your task");
                // Else, proceed to add deadline task
                String[] commandList = command.split(" /by ");
                // - Terminate should there be description and date input
                if (commandList.length != 2) throw new InvalidTaskInputException("Please include the description and deadline for your task");
                // Add deadline task
                task = commandList[1];
                taskList.add(new Deadline(commandList[0], task));
            } else if (type.equals("event")) {
                // Terminate should there be no date input
                if (!command.contains(" /at ")) throw new InvalidTaskInputException("Please include the event date");
                // Else, proceed to add deadline task
                String[] commandList = command.split(" /at ");
                // - Terminate should there be description and date input
                if (commandList.length != 2) throw new InvalidTaskInputException("Please include the time and of your event");
                // Add deadline task
                task = commandList[1];
                taskList.add(new Event(commandList[0], task));
            }
            ui.formatDukeReply("Roger. I will add this to your list:\n\t" + getTaskDetails(taskList.get(taskList.size()-1)) + "\nYou currently have " + taskList.size() + " task in your list.");
        } catch (InvalidTaskInputException e) {
            ui.formatDukeReply(e.getMessage());
        }
    }

    /**
     * Delete a task based on the index
     *
     * @param indexString Index of the task in the arraylist
     * **/
    private void deleteTask(String indexString) {
        // Attempt to convert input to integer
        int index;
        try {
            index = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            ui.formatDukeReply("Task is invalid. Please select a valid task to delete");
            return;
        }
        // Check if index is within range
        index -= 1;
        if (index < 0 || index >= taskList.size()) {
            ui.formatDukeReply("Task is invalid. Please select a valid task to delete");
            return;
        }
        // Delete task and send reply
        ui.formatDukeReply("Alrighty. I will delete this task.:\n\t" + getTaskDetails(taskList.get(index)) + "\nYou currently have " + (taskList.size() - 1) + " task in your list.");
        taskList.remove(index);
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
     * List all the task in the task queue
     * **/
    public void listTask() {
        if (taskList.isEmpty()) {
            ui.formatDukeReply("You have no pending task. Add one now?");
        } else {
            String allTask = "These are your current task:\n";
            for (int i = 0; i < taskList.size(); i++) {
                if (i != 0) {
                    allTask = allTask.concat("\n");
                }
                allTask = allTask.concat(String.valueOf(i+1)).concat(".");
                allTask = allTask.concat(getTaskDetails(taskList.get(i)));
            }
            ui.formatDukeReply(allTask);
        }
    }

    /**
     * Update a task status
     *
     * @param command Determine to mark task
     * **/
    public void updateTaskStatus(String command) {
        if (taskList.isEmpty()) {
            ui.formatDukeReply("You do not have any ongoing task. Add one first?");
            return;
        }

        // Filter out command
        boolean isMark = command.startsWith("mark");
        command = command.replaceFirst(isMark ? "mark" : "unmark", "");
        // Process command
        // - Ensure that it is an integer position
        try {
            int index = Integer.parseInt(command.strip());
            index--;
            if (index < 0 || index >= taskList.size()) {
                ui.formatDukeReply("classes.Task is not found. Please provide a valid index.");
                listTask();
                return;
            }
            Task selected = taskList.get(index);
            if (selected.getDoneStatus() == isMark) {
                ui.formatDukeReply("There are no changes to be made!");
                listTask();
                return;
            }
            selected.setDoneStatus(isMark);
            String reply = isMark ? "Nice! I've marked this task as done:" : "Ok, I've marked this task as not done yet:";
            reply = reply.concat("\n\t").concat(getTaskDetails(selected));
            ui.formatDukeReply(reply);
        } catch (NumberFormatException e) {
            ui.formatDukeReply("Please provide the index of the task that you wish to remove.");
        }
    }

    //================================================================================
    // Main
    //================================================================================

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui = new DukeUI();
        String command;
        Scanner in = new Scanner(System.in);

        // Welcome Message
        ui.printWelcomeMessage();

        //
        while (true) {
            command = in.nextLine();
            if (checkTerminatingWord(command)) {
                break;
            } else {
                try {
                    determineAction(command);
                } catch (UnknownCommandInputException e) {
                    ui.formatDukeReply(e.getMessage());
                }
            }
        }
        // Ending Message
        ui.printEndingMessage();
    }
}
