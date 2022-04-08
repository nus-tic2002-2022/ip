package duke.data.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Extension of Task class to modify deadline.
 */
public class Deadline extends Task {

    private final LocalDateTime by;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * getter of by
     * @return LocalDateTime by
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * @return task in string form
     * e.g. [D][ ] submit assignment  (by: 11/04/2022 23:59)
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatter.format(by) + ")";
    }


    /**
     * @return task in string form with comman format
     * e.g. D , 0 , submit assignment , 11/04/2022 23:59
     */
    @Override
    public String toSaveStr() {
        return "D , " + super.toSaveStr() + " , " + formatter.format(by);
    }

}