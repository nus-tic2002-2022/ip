package duke.task;

import duke.parser.TimeParser;
import java.time.LocalDate;


public class Deadlines extends TasksWithDate {

    /**
     * Represents a deadline events. requires a name and a date in format yyyy-mm-dd
     */
    public Deadlines (String task, String taskDate ){
        super(task,taskDate);
    }

    /**
     * Returns a string formatted version of the duke.task.Deadlines Class
     *
     * @param url an absolute URL giving the base location of the image
     * @param name the location of the image, relative to the url argument
     * @return the image at the specified URL
     * @see Image
     */
    @Override
    public String toString(){
        String formattedDateString = TimeParser.convertDateToString(this.taskDate);
        return String.format("[D][%s] %s (by: %s)", (done?"x":" "), this.task,formattedDateString);
    }

    @Override
    public String taskToSaveFile() {
        return String.format("D|%d|%s|%s", (done?1:0) ,this.task ,this.taskDate);
    };

    @Override
    public void rescheduleTask(String rescheduleDate) {
        this.taskDate = LocalDate.parse(rescheduleDate);
    };
}
