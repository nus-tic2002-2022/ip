package duke.commands;

public enum Commands {
    LIST("list"),
    BYE("bye"),
    MARK("mark"),
    UNMARK("unmark"),
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo"),
    DELETE("delete");

    private String command;

    Commands(final String com) {
        this.command = com;
    }

    public String getCommand() {
        return command;
    }
}