public enum Command {
    LIST("list"),
    BYE("bye"),
    MARK("mark"),
    UNMARK("unmark"),
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo"),
    DELETE("delete");

    private String command;

    Command(final String com) {
        this.command = com;
    }

    public String getCommand() {
        return command;
    }


}