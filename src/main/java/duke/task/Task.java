package duke.task;

import duke.importer.TaskFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Task {
    final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm").withResolverStyle(ResolverStyle.STRICT);
    protected String description;
    protected boolean isDone;
    protected LocalDateTime dateTime;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        System.out.println("Got it. I've added this task:");
    }

    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    public void newTask(){
        try {
            TaskFile.appendTask(addToFile());
            System.out.println("The task was saved successfully");
        } catch (IOException i){
            System.out.println("☹ OOPS!!! The task was not saved to the file.");
        }
    }

    public void setDT(String date){
                    dateTime = LocalDateTime.parse(date, DATE_TIME_FORMAT);
    }
    public String getDT() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd uuuu, hh:mm a"));
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String addToFile() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    //...
}