import java.util.Arrays;
import java.util.Scanner;


public class  Event extends Task {

    protected String events;

    public Event(String description, String events) {
        super(description);
        this.events = events;
        System.out.println("Got it. I've added this task:");
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + events +")";
    }
}

