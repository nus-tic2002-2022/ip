package duke.commands;
/**
 * Define data type for commands in this class.
 */
public enum Commands {
    LIST("list","l"),
    BYE("bye","b"),
    MARK("mark","m"),
    UNMARK("unmark","u"),
    DEADLINE("deadline","d"),
    EVENT("event","e"),
    TODO("todo","t"),
    DELETE("delete","del");

    private String command;
    private String shr;

    Commands(final String com,final String shr) {
        this.command = com;
        this.shr = shr;
    }

    public String getCommand() {
        return command;
    }
    public String getShr(){
        return shr;
    }
}