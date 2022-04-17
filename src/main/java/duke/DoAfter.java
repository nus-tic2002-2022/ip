package duke;

import java.time.LocalDate;

public class DoAfter extends Task {
    protected String after;

    public DoAfter(String description, String after) {
        super(description);
        this.after = after;
    }

    /**
     * Convert the task to string
     *
     * @return string
     */
    @Override
    public String toString() {
        return "[A]" + super.toString() + " (after: " + after + ")";
    }
}
