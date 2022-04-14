package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.enums.TaskType;
import com.calebjianhui.duke.taskmanager.Deadline;
import com.calebjianhui.duke.taskmanager.Event;
import com.calebjianhui.duke.taskmanager.FixedDurationTask;
import com.calebjianhui.duke.taskmanager.TaskManager;
import com.calebjianhui.duke.ui.Messages;

/**
 * This command allows adding of various types of tasks to the task manager.
 **/
public class AddCommand extends Command {
    // Literal command given by user, these represent the 4 different type of tasks available in the program.
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String FIXED_DURATION_COMMAND = "fixed";
    // Help page
    public static final String HELP_PAGE =
            Messages.DIVIDER_UNDERSCORE_EXTENDED + " Adds a task to the task list.\n"
                    + " We currently provide support for 4 different type of task, namely:\n"
                    + " 1) Todos\t\t\t\t: Task with a description.\n"
                    + " 2) Deadline\t\t\t: Task with a date which represent a deadline.\n"
                    + " 3) Event\t\t\t\t: Event with a date which represent the start date of the event.\n"
                    + " 4) Fixed duration task\t: Task that require a fixed amount of time to complete.\n\n"

                    + " Usage:\n\ttodo <description>"
                    + "\n\tdeadline <description>" + Deadline.COMMAND_SEPARATOR + "<date of deadline>"
                    + "\n\tevent <description>" + Event.COMMAND_SEPARATOR + "<date of event>"
                    + "\n\tfixed <description>" + FixedDurationTask.COMMAND_SEPARATOR
                    + "<fixed amount of time needed>\n\n"
                    + " We currently support the following datetime for deadline / event:\n"
                    + " '30/04/2022 2359', '30/04/2022 23:59', '30/04/2022 11.59pm',\n '30/04/2022 11.59 pm', "
                    + "'30/04/2022 11pm', '30/04/2022 11 pm',\n '30/04/2022', "
                    + " '30-04-2022 2359', '30-04-2022 23:59',\n '30-04-2022 11.59pm', '30-04-2022 11.59 pm', "
                    + "'30-04-2022 11pm',\n '30-04-2022 11 pm', '30-04-2022'\n\n"
                    + " Example:\n"
                    + " \ttodo Study\n"
                    + " \tdeadline Exam preparation /by 12/04/2022 12pm \n"
                    + " \tevent Wedding dinner /at 13/04/2022 14:00 \n"
                    + " \tfixed SE project work /needs 2hour \n"
                    + Messages.DIVIDER_UNDERSCORE_EXTENDED;

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
