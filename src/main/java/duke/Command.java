package duke;

import java.util.*;
public abstract class Command {

    protected String type;
    protected boolean isExit = false;

    public Command(String passed)
    {
        this.type=passed;
    }
    public abstract boolean isExit();
    public abstract void execute (ArrayList <Task> tasklist, UI ui, fileaccess f);
}
