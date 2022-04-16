package duke.data.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Extension of Task class to modify Event.
 */
public class Event extends Task {

    private final LocalDateTime at;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * getter of at
     *
     * @return LocalDateTime at
     */
    public LocalDateTime getAt() {
        return at;
    }

    /**
     * @return task in string form
     * e.g.[E][ ] jlpt exam  (at: 22/12/2022 09:00)
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatter.format(at) + ")";
    }

    /**
     * @return task in string form with comman format
     * e.g. E , 0 , jlpt exam , 22/12/2022 09:00
     */
    @Override
    public String toSaveStr() {
        return "E , " + super.toSaveStr() + " , " + formatter.format(at);
    }

}