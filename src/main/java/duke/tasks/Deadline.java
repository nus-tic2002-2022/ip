package duke.tasks;
import duke.constants.DukeConstants;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Pattern;

public class Deadline extends Task{
    private LocalDate by;
    private LocalTime time;
    private String dateString;

    //This class takes in 2 parameters; String description and String input. String input is the Date/Time provided by the user.
    //It will perform a try to see if the input can be parsed into date/time format.
    public Deadline(String description, String input) {
        super(description.replaceAll("\\s$",""));
        String[] splited = input.split("[\\s]", 2);
        try {
            this.by = LocalDate.parse(splited[0]);
            this.time = LocalTime.parse(splited[1]);
            this.dateString = null;
        } catch (Exception e) {
            this.dateString = input.replaceAll("^\\s","").replaceAll("\\s$","");
            this.time = null;
            this.by = null;
        }
    }

    //The following method returns the description together with the date/time values of the Deadline class.
    @Override
    public String getDescription() {
        if(dateString == null)
            return "[D]" + super.getDescription() + " (by:" + ((by!=null)?by:"") + " " + ((time!=null)?time:"") + ")";
        else
            return "[D]" + super.getDescription() + " (by:" + dateString + ")";
    }
}
