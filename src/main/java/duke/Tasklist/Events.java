package duke.Tasklist;

import duke.Duke;
import duke.Exception.DukeException;
import duke.Exception.timeparseException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
/**
 * @Event class.
 * Accepted Input : event attend seminar / description or location of event
 * always start with event/Event/EVENT
 * @details Date should be in this format when loaded in. Eg : 2022-02-19 (Means 19 Feb 2022)
 * Time should be in 24 hours format. Eg : 1800 (Means 06:00pm)
 */
public class Events extends Task {

    protected String details;
    public LocalDate first_occur;
    public Events(String description, String details) throws DateTimeParseException, DukeException {
        super(description);
        //this.details = details;
        try {
            String[] d_t = details.split(" "); // at woodland on YYYY-MM-DD
            //System.out.println(d_t[1]);
            Integer by_index = -1;
            for (Integer i = 0; (i < d_t.length && by_index == -1); i++) {
                if (d_t[i].equals("on")) {
                    by_index = i;
                    //System.out.println("What is this : " + d_t[by_index + 1]);
                }
                if (!d_t[i].equals("on") && i == d_t.length - 1) {
                    throw new DukeException();
                }
                this.details += " "+d_t[i];
            }
            this.details = d_t[by_index-1];
            this.first_occur = LocalDate.parse(d_t[by_index + 1]); //YYYY-MM-DD
        }
        catch (DateTimeParseException e)
        {
            System.out.println("Date parse Error");
        }

    }

    public String getStatus() {
        return (isDone ? "[E][X] " + this.description + "(" + details + " on " +first_occur.format(DateTimeFormatter.ofPattern("MMM d YYYY")) + ")" : "[E][ ] " + this.description + " (" + details + " on " +first_occur.format(DateTimeFormatter.ofPattern("MMM d YYYY")) + ")");
    }

    public String getDetails() {
        return details + " on " +first_occur.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
    }

    public LocalDate getDate() {
        return first_occur;
    }
}