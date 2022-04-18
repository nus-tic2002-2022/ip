package zhixuan.duke.commands;

import zhixuan.duke.ui.DukeUI;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String BYE_COMMAND = "bye";

    public static boolean selectedCommand(Command instance) {
        return (instance instanceof ExitCommand);
    }

    public boolean execute() {
        new DukeUI().printEndingMessage();
        return false;
    }
}
