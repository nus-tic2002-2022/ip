package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.common.Pair;
import com.calebjianhui.duke.enums.ListCommandType;
import com.calebjianhui.duke.taskmanager.TaskManager;

import java.util.Arrays;
import java.util.List;

public class ListCommand extends Command {
    public static final String COMMAND = "list";
    public static final List<Pair<String, ListCommandType>> LIST_COMMAND_PREFIX =
            Arrays.asList(
                    new Pair<>("-s", ListCommandType.SCHEDULE),
                    new Pair<>("-n", ListCommandType.NORMAL)
            );

    private final Pair<ListCommandType, String> listType;

    public ListCommand() {
        this.listType = new Pair<>(ListCommandType.NORMAL, "");
    }

    public ListCommand(Pair<ListCommandType, String> listType) {
        this.listType = listType;
        assert listType != null : "ListCommand constructor cannot be null!";
        assert (!(listType.getFirst().equals(ListCommandType.INVALID_COMMAND))): "ListCommand constructor cannot be invalid.";
    }

    /**
     * Execute the specified command
     */
    public boolean execute() {
        TaskManager.getInstance().listTask(listType);
        return false;
    }

    /**
     * Based on the given input, determine the selected command type
     *
     * @param input input string parameter
     * @return The corresponding ListCommandType, else return as INVALID_COMMAND
     */
    public static ListCommandType checkCommandType(String input) {
        for (Pair<String, ListCommandType> command: LIST_COMMAND_PREFIX) {
            if (command.getFirst().equals(input)) {
                return command.getSecond();
            }
        }
        return ListCommandType.INVALID_COMMAND;
    }
}
