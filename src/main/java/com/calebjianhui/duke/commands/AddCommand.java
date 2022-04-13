package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.enums.TaskType;
import com.calebjianhui.duke.taskmanager.TaskManager;

/**
 * This command allows adding of various types of tasks to the task manager.
 **/
public class AddCommand extends Command {
    // Literal command given by user, these represent the 4 different type of tasks available in the program.
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String FIXED_DURATION_COMMAND = "fixed";

    // Variables needed:
    // - Type of the task
    private final TaskType type;
    // - The remaining input given by the user after specifying the type of task
    private final String command;
    // Whether to display the results to the user
    // --> This is as adding of tasks on startup (from saved filed) should not show result to the user
    private final boolean isSilent;
    // - If the task is marked as done
    private final boolean isDone;

    /**
     * AddCommand constructor
     *
     * @param type Type of task
     * @param command The remaining input given by the user after specifying the type of task
     */
    public AddCommand(String type, String command) {
        this.type = getTaskTypeFromCommand(type);
        this.command = command;
        this.isSilent = false;
        this.isDone = false;
    }

    /**
     * AddCommand constructor
     *
     * @param isSilent Whether to display the results to the user
     * @param type Type of task
     * @param isDone If the task is to be marked as done
     * @param command The remaining input given by the user after specifying the type of task
     */
    public AddCommand(boolean isSilent, TaskType type, boolean isDone, String command) {
        this.type = type;
        this.command = command;
        this.isSilent = isSilent;
        this.isDone = isDone;

        assert type != null : "AddCommand constructor cannot have null TaskType!";
    }

    /**
     * From a given string, determine the type of task it represents
     * - This is on the assumption that the given string matches one of the task
     *
     * @param command The remaining input given by the user after specifying the type of task
     * @return TaskType representing the type of task
     * @throws AssertionError Should the given string not match any TaskType
     */
    private static TaskType getTaskTypeFromCommand(String command) {
        if (TODO_COMMAND.equals(command)) {
            return TaskType.TODO;
        } else if (DEADLINE_COMMAND.equals(command)) {
            return TaskType.DEADLINE;
        } else if (EVENT_COMMAND.equals(command)) {
            return TaskType.EVENT;
        } else if (FIXED_DURATION_COMMAND.equals(command)) {
            return TaskType.FIXED_DURATION;
        }
        // TaskType should only consist of the above, therefore throw AssertionError
        String errorMessage = "Invalid TaskType received";
        assert false : errorMessage;
        throw new AssertionError(errorMessage);
    }

    /**
     * Execute the specified command.
     *
     * @return Whether the command made any changes to the task list
     */
    @Override
    public boolean execute() {
        return TaskManager.getInstance().addToTaskList(isSilent, type, isDone, command);
    }
}
