package duke.commands;

/**
 * Define data type for commands in this class.
 */
public enum Commands {
    BYE("bye", "b"),
    DEADLINE("deadline", "d"),
    DELETE("delete", "del"),
    EVENT("event", "e"),
    FIND("find", "f"),
    LIST("list", "l"),
    MARK("mark", "m"),
    TODO("todo", "t"),
    UNMARK("unmark", "u");


    private final String command;
    private final String shr;

    Commands(final String com, final String shr) {
        this.command = com;
        this.shr = shr;
    }

    public String getCommand() {
        return command;
    }

    public String getShr() {
        return shr;
    }
}