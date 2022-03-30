package duke.command;

import duke.Tasklist.Task;
import duke.UI.UI;
import duke.Storage.fileaccess;

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