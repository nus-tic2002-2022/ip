package com.calebjianhui.duke.commands;

import java.util.Arrays;
import java.util.List;

import com.calebjianhui.duke.common.Pair;
import com.calebjianhui.duke.enums.UpdateCommandType;
import com.calebjianhui.duke.taskmanager.TaskManager;

/**
 * This command allows the updating the fields for a specific task in the task list.
 * - Fields include: description, date, done status.
 **/
public class UpdateCommand extends Command {
    // Literal command given by user, indicating to edit (description / date), or to update the done status
    public static final String EDIT_COMMAND = "edit";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    // Definition of parameters behind the command given by user
    // Pair<T, U>, T = parameter input given by user, U = UpdateCommandType equivalence of input
    public static final List<Pair<String, UpdateCommandType>> UPDATE_COMMAND_PREFIX =
            Arrays.asList(
                    new Pair<>("-d", UpdateCommandType.EDIT_DATE),
                    new Pair<>("-m", UpdateCommandType.EDIT_DESCRIPTION)
            );

    // Variables needed:
    private final UpdateCommandType updateFieldType;
    private final int index;
    private final String details;

    /**
     * UpdateCommand constructor
     *
     * @param updateFieldType Field to be updated
     * @param index Index of task to update
     * @param details New details of the field to be updated to
     */
    public UpdateCommand(UpdateCommandType updateFieldType, int index, String details) {
        this.updateFieldType = updateFieldType;
        this.index = index;
        this.details = details;

        assert updateFieldType != null : "UpdateCommand constructor cannot have null UpdateCommandType!";
        assert (!(updateFieldType.equals(UpdateCommandType.INVALID_COMMAND)))
                : "UpdateCommand constructor cannot be invalid.";
    }

    /**
     * Execute the specified command.
     *
     * @return Whether the command made any changes to the task list
     */
    @Override
    public boolean execute() {
        return TaskManager.getInstance().updateTask(updateFieldType, index, details);
    }

    /**
     * Based on the given input, determine the UpdateCommandType it represents
     *
     * @param input input string parameter
     * @return The corresponding UpdateCommandType, else return as INVALID_COMMAND
     */
    public static UpdateCommandType checkCommandType(String input) {
        for (Pair<String, UpdateCommandType> command: UPDATE_COMMAND_PREFIX) {
            if (command.getFirst().equals(input)) {
                return command.getSecond();
            }
        }
        return UpdateCommandType.INVALID_COMMAND;
    }
}
