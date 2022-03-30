package duke.Tasklist;
import duke.Exception.dateparseException;
import duke.Exception.timeparseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Deadline class
 * @deadliner Date should be in this format when loaded in. Eg : 19-2-2022 (Means 19 Feb 2022)
 * @timeliner Time should be in 24 hours format. Eg : 1800 (Means 06:00pm)
 * */
public class Deadlines extends Task {

    protected String by;
    protected LocalDate deadliner;
    private String timerliner;

    public Deadlines (String description, String by) throws dateparseException
    {

        super(description);
        this.by = by;
        System.out.println(by);
        //DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String[] d_t = by.split(" ",2);

        try {
                this.deadliner = LocalDate.parse(d_t[0]);
                String time = tokenize_date(Integer.parseInt(d_t[1]));
                SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
                SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
                Date _24HourDt = _24HourSDF.parse(time);
                this.timerliner = _12HourSDF.format(_24HourDt);
            }
            catch (timeparseException e) {}
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Deadline without time");
        }
        catch (java.text.ParseException e)
        {
            System.out.println("Time error");
        }
    }
    private static String tokenize_date(Integer passed) throws timeparseException {
        Integer mins = passed%100;
        Integer hours = passed/100;
        if (passed > 2359 || passed < 0)
        {
            throw new timeparseException();
        }
        String str_mins = String.format("%02d",mins);
        String str_hours = String.format("%02d",hours);
        return str_hours + ":"+str_mins;
    }
    public String getStatus() {
        if(timerliner == null)
        {
            return (isDone ? "[D][X] " + this.description  + " (" +deadliner.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+")" : "[D][ ] " + this.description +"("+deadliner.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +")");
        }
        return (isDone ? "[D][X] " + this.description  + " (" +deadliner.format(DateTimeFormatter.ofPattern("MMM d yyyy "))+timerliner+")" : "[D][ ] " + this.description +"("+deadliner.format(DateTimeFormatter.ofPattern("MMM d yyyy ")) +timerliner+")");
    }

    /*public String getStatus() {
        return (isDone ? "[D][X] " + this.description  + "(" +by +")" : "[D][ ] " + this.description +"("+by +")");
    }*/

    public String getBy() {
        return by;
    }
}
