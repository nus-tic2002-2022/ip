package duke.data.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Extension of Task class to modify deadline.
 */
public class Deadline extends Task {

    protected Date by;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns status of Task.
     *
     * @return status of Deadline with user input and save.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + sdf.format(by) + ")";
    }

    @Override
    public String toSaveStr() {
        return "D , " + super.toSaveStr() + " , " + sdf.format(by);
    }

}