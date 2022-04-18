package zhixuan.duke.commands;

/**
 * Represents an executable command.
 */
public abstract class Command {

    /**
     * Executes the command and returns success boolean.
     *
     * @retyrn boolean true if successful, else false
     */
    public abstract boolean execute();

}
