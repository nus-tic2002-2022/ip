package duke.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

class TaskTest implements Comparable<TaskTest>{

    final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm").withResolverStyle(ResolverStyle.STRICT);
    String description;
    String taskType;
    boolean isDone;
    LocalDateTime dateTime;

    public TaskTest() {
        this.description = "test ";
        this.isDone = false;
    }

    @Test
    void taskDescriptionTest(){
        Assertions.assertEquals("test ", this.description);
    }

    @Test
    void taskIsDoneTest(){
        Assertions.assertFalse(this.isDone);
    }

    @Test
    void taskGetStatusIconTest(){
        Assertions.assertEquals(" ",this.getStatusIcon());
    }

    String getTaskType(){
        return taskType;
    }
    void setDT(String date){
        dateTime = LocalDateTime.parse(date, DATE_TIME_FORMAT);
    }
    public LocalDateTime getDT() {
        return dateTime;
    }
    String printDT() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd uuuu, hh:mm a"));
    }
    String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    String addToFile() {
        return "[" + getStatusIcon() + "] " + description;
    }
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    @Override
    public int compareTo(TaskTest otherTask) {
        return getDT().compareTo(otherTask.getDT());
    }
}