package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import duke.exceptions.*;
public class Time extends Task{
    private LocalDateTime time;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    Time(String description, String timeString){
        super(description);
        time = LocalDateTime.parse(timeString, formatter);
    }

    /**
     * To convert the time for saving.
     *
     * @return time as a string.
     */
    String convertSaveTimeString(){
        return formatter.format(time);
    }

    /**
     * Send a converted time string to display to the user.
     *
     * @return time as a string.
     */
    String convertTimeString(){
        return DateTimeFormatter.ofPattern("MMM d yyyy HHmm").format(time);
    }

}
