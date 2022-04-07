package duke.Tasklist;

import duke.Exception.DukeException;
/**
 * Recurring class extends Events
 * new variable : number. number of recurring task intended
 * new variable : between. the interval of recurring task
 */


public class RecurringTask extends Events {

    protected Integer number;
    protected Integer between;

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
        first_occur = first_occur.plusDays(between * multiple);
    }

}

