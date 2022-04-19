package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {
    private LocalDate at;
    private LocalTime time;
    private String dateString;

    //This class takes in 2 parameters; String description and String input. String input is the Date/Time provided by the user.
    //It will perform a try to see if the input can be parsed into date/time format.
    public Event(String description, String input, boolean isDone) {
        super(description.replaceAll("\\s$", ""), isDone);
        String[] splited = input.split("[\\s]", 2);
        try {
            this.at = LocalDate.parse(splited[0]);
            this.time = LocalTime.parse(splited[1]);
            this.dateString = null;
        } catch (Exception e) {
            this.dateString = input.trim();
            this.time = null;
            this.at = null;
        }
    }

    //The following method returns the description together with the date/time values of the Event class.
    @Override
    public String getDescription() {
        String message = dateString;
        if (dateString == null) {
            message = ((at != null) ? at : "") + " " + ((time != null) ? time : "");
        }

        return "[E]" + super.getDescription() + " (at:" + message + ")";
    }
}
