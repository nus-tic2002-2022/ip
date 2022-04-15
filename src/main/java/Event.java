import java.util.Arrays;
import java.util.Scanner;


public class  Event extends Task {

    protected String events;

    public Event(String description, String events) {
        super(description);
        this.events = events;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + events +")";
    }
}

