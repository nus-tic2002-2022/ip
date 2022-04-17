package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.taskmanager.TaskManager;
import com.calebjianhui.duke.ui.Messages;

/**
 * This command allows the search of tasks that have their description matching the word provided.
 * Provides 2 type of searches (Word search & Character search). Default is word search
 **/
public class FindCommand extends Command {
    // Literal command given by user
    public static final String COMMAND = "find";
    // Parameter indicating a character search instead
    public static final String PARAMS_CHAR_SEARCH = "-c";
    // Help page
    public static final String HELP_PAGE =
            Messages.DIVIDER_UNDERSCORE + " Find a task that have its description matching the keyword provided.\n"
                    + " Default is word search.\n\n"
                    + " Usage:\n    find [-c] <keyword>\n\n"
                    + " Options:\n    -c     Character Search. Default is word search.\n\n"
                    + " Example:\n"
                    + "    find book\n"
                    + "    find -c book\n"
                    + Messages.DIVIDER_UNDERSCORE;

    // Variables needed:
    private final boolean isCharacterSearch;
    private final String keyword;

    /**
     * ListCommand constructor
     *
     * @param isCharacterSearch If it is to perform a character search
     * @param keyword Keyword to search for
     */
    public FindCommand(boolean isCharacterSearch, String keyword) {
        this.isCharacterSearch = isCharacterSearch;
        this.keyword = keyword;
    }

    /**
     * Execute the specified command.
     *
     * @return Default return false as this command does not make changes to the task list
     */
    @Override
    public boolean execute() {
        TaskManager.getInstance().findTask(isCharacterSearch, keyword);
        return false;
    }
}
