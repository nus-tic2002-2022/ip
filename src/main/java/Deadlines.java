import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Deadlines extends Task{
    protected LocalDate deadline;

    /**
     * Represents a deadline events. requires a name and a date in format yyyy-mm-dd
     */
    public Deadlines (String task, String deadline ){
        super(task);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Returns a string formatted version of the Deadlines Class
     *
     * @param url an absolute URL giving the base location of the image
     * @param name the location of the image, relative to the url argument
     * @return the image at the specified URL
     * @see Image
     */
    @Override
    public String toString(){

        return String.format("[D][%s] %s (by: %s)", (done?"x":" "), this.task,this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String taskToSaveFile() {
        return String.format("D|%d|%s|%s", (done?1:0) ,this.task ,this.deadline);
    };
}
