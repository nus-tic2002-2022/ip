package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.common.Pair;
import com.calebjianhui.duke.enums.UpdateCommandType;
import com.calebjianhui.duke.taskmanager.TaskManager;

import java.util.Arrays;
import java.util.List;

public class UpdateCommand extends Command {
    public static final String EDIT_COMMAND = "edit";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final List<Pair<String, UpdateCommandType>> UPDATE_COMMAND_PREFIX =
            Arrays.asList(
                    new Pair<>("-d", UpdateCommandType.EDIT_DATE),
                    new Pair<>("-m", UpdateCommandType.EDIT_DESCRIPTION)
            );

    private final UpdateCommandType updateFieldType;
    private final int index;
    private final String details;

    public UpdateCommand(UpdateCommandType updateFieldType, int index, String details) {
        this.updateFieldType = updateFieldType;
        this.index = index;
        this.details = details;
    }

    /**
     * Execute the specified command
     */
    @Override
    public boolean execute() {
        return TaskManager.getInstance().updateTask(updateFieldType, index, details);
    }

    /**
     * Based on an input, determine if it is a selected command type
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
