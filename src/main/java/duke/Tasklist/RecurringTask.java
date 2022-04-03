package duke.Tasklist;

import duke.Exception.dateparseException;

import java.time.LocalDate;

public class RecurringTask extends Events {

    protected Integer number;
    protected Integer between;
    public LocalDate first_occur;
    //protected LocalDate date;

    public RecurringTask (String description, String info,String firstdate,Integer n_of_recur, Integer gap)
    {
        super(description, info);
            this.first_occur = LocalDate.parse(firstdate);
            assert (gap > 0 && n_of_recur > 0);
            this.number = n_of_recur;
            this.between = gap;
    }
    @Override
    public String getDetails()
    {
        return details + " " + first_occur.toString();
    }
    @Override
    public String getStatus() {
        return (isDone ? "[R][X] " + this.description + "( " + details + " " +first_occur.toString()+")": "[R][ ] " + this.description + " (" + details + " " +first_occur.toString()+ ")");
    }
    public Integer getnumber()
    {
        return number;
    }
    public Integer getbetween()
    {
        return between;
    }
    public void add_day (Integer multiple)
    {
        //LocalDate date = first_occur.plusDays(between*multiple);
        //first_occur = date;
        first_occur = first_occur.plusDays(between*multiple);
        //details+=" "+ date.toString();
    }

}

