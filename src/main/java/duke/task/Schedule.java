package duke.task;
import duke.exceptions.InvalidScheduleFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Schedule extends Task {
    protected LocalDate startDate;
    protected LocalDate endDate;

    public Schedule(String task, String startDate, String endDate) {
        super(task);
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);

        try {
            this.checkScheduleDateFormat();
        } catch (InvalidScheduleFormatException e) {
            System.out.println("Exception occurred:" + e);
        }

    }

    @Override
    public String toString(){
        String startDateString = this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String endDateString =   this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return String.format("[S][%s] %s (from %s to %s)", (done?"x":" "), this.task,startDateString,endDateString);
    }

    @Override
    public String taskToSaveFile() {
        return String.format("S|%d|%s|%s|%s", (done?1:0) ,this.task ,this.startDate,this.endDate);
    };

    private void checkScheduleDateFormat() throws InvalidScheduleFormatException{
        if(this.startDate.isAfter(endDate)) {
            throw new InvalidScheduleFormatException("Invalid format! Start Date is after End Date!");
        };
    }
}
