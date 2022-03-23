package duke.tasks;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Pattern;

public class Deadline extends Task{
    private LocalDate by;
    private LocalTime time;
    private Pattern timePattern = Pattern.compile("^[0-2][0-9]:[0-5][0-9]$");

    //This class takes in 2 parameters; String description and String input. String input is the Date/Time provided by the user.
    //It will perform a try to see if the input can be parsed into date/time format.
    public Deadline(String description, String input) {
        super(description);
        try {
            String[] splited = input.split("[\\s]", 2);
            try {
                this.by = LocalDate.parse(splited[0]);
            } catch (Exception e) {
                this.by = null;
            }
            if (splited[1].matches(timePattern.pattern()))
                this.time = LocalTime.parse(splited[1]);
            else
                this.time = null;
        } catch (Exception e) {
            this.by = null;
            this.time = null;
        }
    }

    //The following method returns the description together with the date/time values of the Deadline class.
    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + " (by:" + ((by!=null)?by:"[invalid_date]") + " " + ((time!=null)?time:"[time_unspecified]") + ")";
    }
}
