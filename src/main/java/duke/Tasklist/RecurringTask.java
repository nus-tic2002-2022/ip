package duke.Tasklist;

import duke.Duke;
import duke.Exception.DukeException;
import duke.Exception.dateparseException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class RecurringTask extends Events {

    protected Integer number;
    protected Integer between;
    //public LocalDate first_occur;
    //protected LocalDate date;

    public RecurringTask(String description, String info, Integer n_of_recur, Integer gap) throws DukeException {

        super(description, info);
        assert (gap > 0 && n_of_recur > 0);
        this.number = n_of_recur;
        this.between = gap;
    }

    public Integer getnumber() {
        return number;
    }

    public Integer getbetween() {
        return between;
    }

    public void add_day(Integer multiple) {
        //LocalDate date = first_occur.plusDays(between*multiple);
        //first_occur = date;
        first_occur = first_occur.plusDays(between * multiple);
        //details+=" "+ date.toString();
    }

}

