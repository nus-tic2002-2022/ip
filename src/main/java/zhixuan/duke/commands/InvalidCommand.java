package zhixuan.duke.commands;

import zhixuan.duke.ui.DukeUI;

public class InvalidCommand extends Command {

    private final static String REPLY_INVALID_INDEX = "Invalid task selected.";
    private final static String REPLY_UNKNOWN_COMMAND = "You have entered an invalid command.";

    private final String commandType;
    public InvalidCommand(String commandType) {
        this.commandType = commandType;
    }
    private String decipherType() {
        switch (commandType) {
            case "invalid_index":
                return REPLY_INVALID_INDEX;
            default:
                return REPLY_UNKNOWN_COMMAND;
        }
    }

    public static boolean isSelectedCommand(Command instance) {
        return (instance instanceof InvalidCommand);
    }

    @Override
    public boolean execute() {
        new DukeUI().showToUser(decipherType());
        return false;
    }
    
}
