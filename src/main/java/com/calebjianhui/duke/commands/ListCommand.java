package com.calebjianhui.duke.commands;

import java.util.Arrays;
import java.util.List;

import com.calebjianhui.duke.common.Pair;
import com.calebjianhui.duke.enums.ListCommandType;
import com.calebjianhui.duke.taskmanager.TaskManager;
import com.calebjianhui.duke.ui.Messages;

/**
 * This command allows the listing of task in the task manager
 * - Supports general listing and schedule listing (including schedule of a specific day)
 **/
public class ListCommand extends Command {
    // Literal command given by user
    public static final String COMMAND = "list";
    // Definition of parameters behind the command given by user
    // Pair<T, U>, T = parameter input given by user, U = ListCommandType equivalence of input
    public static final List<Pair<String, ListCommandType>> LIST_COMMAND_PREFIX =
            Arrays.asList(
                    new Pair<>("-s", ListCommandType.SCHEDULE),
                    new Pair<>("-n", ListCommandType.NORMAL)
            );
    // Help page
    public static final String HELP_PAGE =
            Messages.DIVIDER_UNDERSCORE_EXTENDED + " Display all tasks in the task list or task on a specific date.\n"
                    + " We provide 2 different type of task list display:\n"
                    + " 1) Normal - View tasks in the order that they are added to the program.\n"
                    + " 2) Schedule - View event/deadlines sorted by date. Either view all or on a specific date.\n"
                    + " The default view is Normal.\n\n"
                    + " Usage:\n    list [(-s/-n)] [<date>]\n"
                    + " The accepted date format are: '28/03/2022' or '28-03-2022'.\n"
                    + " Do note that non-recognizable task date will be displayed as NA.\n\n"
                    + " Example:\n"
                    + "    list\n"
                    + "    list -s\n"
                    + "    list -s 28/03/2022\n"
                    + "    list -n\n"
                    + Messages.DIVIDER_UNDERSCORE_EXTENDED;

    // Variables needed:
    // - List type and date input given
    // Pair<T, U>, T = ListCommandType of command, U = Date String input, empty if it is to list all
    private final Pair<ListCommandType, String> listType;

    /**
     * ListCommand constructor
     */
    public ListCommand() {
        this.listType = new Pair<>(ListCommandType.NORMAL, "");
    }

    /**
     * ListCommand constructor
     *
     * @param listType Declaration of listType directly
     */
    public ListCommand(Pair<ListCommandType, String> listType) {
        this.listType = listType;
        assert listType != null : "ListCommand constructor cannot be null!";
        assert (!(listType.getFirst().equals(ListCommandType.INVALID_COMMAND)))
                : "ListCommand constructor cannot be invalid.";
    }

    /**
     * Execute the specified command.
     *
     * @return Default return false as this command does not make changes to the task list
     */
    public boolean execute() {
        TaskManager.getInstance().listTask(listType);
        return false;
    }

    /**
     * Based on the given input, determine the selected ListCommandType
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
