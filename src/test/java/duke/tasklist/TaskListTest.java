package duke.tasklist;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void TaskListCheck(){
        String expectedResult = "\t\t\t\t\t\t\t\t\t 1. [T]\t[ ]\t\tread book\n" +
                "\t\t\t\t\t\t\t\t\t 2. [D]\t[ ]\t\tread book\tby : 2022/12/12 06:00 PM\n" +
                "\t\t\t\t\t\t\t\t\t 3. [E]\t[ ]\t\tread book\tat : 2022/12/12 06:00 PM\n";
        String taskInput = "read book";

        String dateTimeInput = "12-12-2022 1800";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime formattedDateTimeInput = LocalDateTime.parse(dateTimeInput, formatter);

        Task newTask1 = new Task(taskInput);
        Deadline newTask2 = new Deadline(taskInput, formattedDateTimeInput);
        Event newTask3 = new Event(taskInput, formattedDateTimeInput);

        ArrayList<Task> newArrayList = new ArrayList<Task>();
        newArrayList.add(newTask1);
        newArrayList.add(newTask2);
        newArrayList.add(newTask3);

        TaskList newTaskList = new TaskList(newArrayList);

        assertEquals(newTaskList.toString(), expectedResult);
    }
}
