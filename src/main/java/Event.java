import java.util.Arrays;
import java.util.Scanner;


public class  Event extends Task {

    protected String events;

    public Event(String description, String events) {
        super(description);
        this.events = events;
        System.out.println("Got it. I've added this task:");
        System.out.println(this);
        newTask();
    }

    //Overload
    public Event(String description, String events, boolean isDone) {
        super(description);
        this.events = events;
        this.isDone = isDone;
        System.out.println("Got it. I've added this task:");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + events +")";
    }
}

