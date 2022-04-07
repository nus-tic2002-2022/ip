package duke.Tasklist;

import duke.Duke;
import duke.Exception.DukeException;
import duke.Exception.dateparseException;
import duke.Exception.timeparseException;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * @Deadline class
 * @deadliner Date should be in this format when loaded in. Eg : 2022-02-19 (Means 19 Feb 2022)
 * @timeliner Time should be in 24 hours format. Eg : 1800 (Means 06:00pm)
 * Accepted Input : deadline submit assignment 5 / by 2022-12-21 1800 (Means 21 Dec 2022 6pm)
 */
public class Deadlines extends Task {

    protected String by;
    protected LocalDate deadliner;
    //private String timerliner;
    private LocalTime timerliner;

    public Deadlines(String description, String by) throws dateparseException, ParseException, DukeException, NullPointerException
        {
            super(description);
            this.by = by;
            try {
                String[] d_t = by.split(" "); // by YYYY-MM-DD HHMM  (in 24 hours format)
                //System.out.println(d_t[1]);
                Integer by_index = -1;
                for (Integer i = 0; (i < d_t.length && by_index == -1); i++) {
                    if (d_t[i].equals("by")) {
                        by_index = i;
                        //System.out.println("What is this : " + d_t[by_index + 1]);
                    }
                    if(!d_t[i].equals("by") && i == d_t.length-1) {
                        throw new DukeException();
                    }
                }
                    this.deadliner = LocalDate.parse(d_t[by_index + 1]); //YYYY-MM-DD
                    String time = tokenize_date(Integer.parseInt(d_t[by_index + 2]));
                    SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
                    SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
                    Date _24HourDt = _24HourSDF.parse(time);
                    //this.timerliner = _12HourSDF.parse(_24HourDt);
                    this.timerliner = LocalTime.parse(time);

            }
            catch (timeparseException e) {
                System.out.println("Time Parse Error");
            }
            catch (DateTimeParseException e)
            {
                System.out.println("Date Parse Error");
            }


            }

    private static String tokenize_date(Integer passed) throws timeparseException {
        Integer mins = passed % 100;
        Integer hours = passed / 100;
        if (passed > 2359 || passed < 0) {
            throw new timeparseException();
        }
        String str_mins = String.format("%02d", mins);
        String str_hours = String.format("%02d", hours);
        return str_hours + ":" + str_mins;
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
    public LocalDate getDate()
    {
        return deadliner;
    }
    public LocalTime getTime()
    {
        return timerliner;
    }
}
