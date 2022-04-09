package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.taskmanager.TaskManager;

public class FindCommand extends Command {
    public static final String COMMAND = "find";
    public static final String PARAMS_CHAR_SEARCH = "-c";

    private final boolean isCharacterSearch;
    private final String keyword;

    public FindCommand(boolean isCharacterSearch, String keyword) {
        this.isCharacterSearch = isCharacterSearch;
        this.keyword = keyword;
    }

    /**
     * Execute the specified command
     */
    @Override
    public boolean execute() {
        TaskManager.getInstance().findTask(isCharacterSearch, keyword);
        return false;
    }
    
}
