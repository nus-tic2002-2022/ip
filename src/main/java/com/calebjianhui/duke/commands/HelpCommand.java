package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.ui.DukeUI;
import com.calebjianhui.duke.ui.Messages;

/**
 * This command display the help page to act as a user guide to the program
 **/
public class HelpCommand extends Command {
    // Literal command given by user
    public static final String COMMAND = "help";
    // Help page
    public static final String HELP_PAGE =
            Messages.DIVIDER_UNDERSCORE_EXTENDED
                    + " This is a simplified help page for all available commands supported by Duke.\n"
                    + " Type 'help <command>' to view detailed functionalities for the specified command.\n"
                    + Messages.DIVIDER_UNDERSCORE_EXTENDED + " Command\t| Action\n"
                    + Messages.DIVIDER_UNDERSCORE_EXTENDED
                    + " list\t\t| Display all tasks in the task list.\n" + Messages.DIVIDER_DASH_EXTENDED
                    + " todo\t\t| Add a task with a description.\n"
                    + " deadline\t| Add a task with a deadline.\n"
                    + " event\t\t| Add an event on a specific date.\n"
                    + " fixed\t\t| Add a task that require a fixed amount of time to complete.\n"
                    + Messages.DIVIDER_DASH_EXTENDED
                    + " edit\t\t| Edit the date / description of a specific task.\n" + Messages.DIVIDER_DASH_EXTENDED
                    + " mark\t\t| Mark a task as completed / done.\n"
                    + " unmark\t\t| Mark a task as ongoing.\n" + Messages.DIVIDER_DASH_EXTENDED
                    + " find\t\t| Find a task that have its description matching the keyword provided.\n"
                    + Messages.DIVIDER_DASH_EXTENDED
                    + " clone\t\t| Make a copy of a task that is in the task list.\n" + Messages.DIVIDER_DASH_EXTENDED
                    + " delete\t\t| Delete all or a specific task in the task list.\n" + Messages.DIVIDER_DASH_EXTENDED
                    + " bye\t\t| Exit the program.\n"
                    + Messages.DIVIDER_UNDERSCORE_EXTENDED;

    // Variables needed:
    private final String message;
    /**
     * HelpCommand constructor
     *
     * @param message Help page to print to the user
     */
    public HelpCommand(String message) {
        this.message = message;
    }

    /**
     * Execute the specified command.
     *
     * @return Default return false as this command does not make changes to the task list
     */
    @Override
    public boolean execute() {
        new DukeUI().printToUser(message);
        return false;
    }

}
