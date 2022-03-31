package main.java.duke.data.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected Date at;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + sdf.format(at) + ")";
    }

    @Override
    public String toSaveStr() {
        return "E , " + super.toSaveStr() + " , " + sdf.format(at);
    }

}