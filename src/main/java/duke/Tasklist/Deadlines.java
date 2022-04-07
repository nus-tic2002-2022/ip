package duke.Tasklist;

import duke.Duke;
import duke.Exception.DukeException;
import duke.Exception.dateparseException;
import duke.Exception.timeparseException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadlines class
 * deadliner Date should be in this format when loaded in. Eg : 2022-02-19 (Means 19 Feb 2022)
 * timeliner Time should be in 24 hours format. Eg : 1800 (Means 06:00pm)
 * Accepted Input : deadline submit assignment 5 / by 2022-12-21 1800 (Means 21 Dec 2022 6pm)
 */

public class Deadlines extends Task {

    protected String by;
    protected LocalDate deadliner;
    private LocalTime timerliner;

    public Deadlines(String description, String by) throws dateparseException, DukeException, NullPointerException, timeparseException, DateTimeParseException {
        super(description);
        this.by = by;
        try {
            String[] d_t = by.split(" "); // by YYYY-MM-DD HHMM  (in 24 hours format)
            Integer by_index = -1;
            for (Integer i = 0; (i < d_t.length && by_index == -1); i++) {
                if (d_t[i].equals("by")) {
                    by_index = i;
                }
                if (!d_t[i].equals("by") && i == d_t.length - 1) {
                    throw new DukeException();
                }
            }
            this.deadliner = LocalDate.parse(d_t[by_index + 1]); //YYYY-MM-DD
            String time = tokenize_date(Integer.parseInt(d_t[by_index + 2]));
            this.timerliner = LocalTime.parse(time); //HHMM Eg 1800, 0000, 2359
        } catch (DateTimeParseException e) {
            System.out.println("DateTimeParse: "+"Date Time parse Error");
            throw new dateparseException();
        }catch (timeparseException e) {
            System.out.println("timeparseException: "+"Weird time detected");
            throw new timeparseException();
        }
    }
    private static String tokenize_date(Integer passed) throws timeparseException {

        if (passed > 2359 || passed < 0)
        {
            throw new timeparseException();
        }
        Integer mins = passed%100;
        Integer hours = passed/100;
        String str_mins = String.format("%02d",mins);
        String str_hours = String.format("%02d",hours);
        return str_hours + ":"+str_mins;
    }

    public String getStatus() {
        if (timerliner == null) {
            return (isDone ? "[D][X] " + this.description + " (" + deadliner.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")" : "[D][ ] " + this.description + " (" + deadliner.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
        }
        return (isDone ? "[D][X] " + this.description + " (" + deadliner.format(DateTimeFormatter.ofPattern("MMM d yyyy ")) + timerliner.format(DateTimeFormatter.ofPattern("hh:mma")) + ")" : "[D][ ] " + this.description + " (" + deadliner.format(DateTimeFormatter.ofPattern("MMM d yyyy ")) + timerliner.format(DateTimeFormatter.ofPattern("hh:mma")) + ")");
    }

    public String getBy() {
        return by;
    }

    public LocalDate getDate() {
        return deadliner;
    }

    public LocalTime getTime() {
        return timerliner;
    }

    public static String testgetStatus(String passed) {
        return (passed);
    }
}
