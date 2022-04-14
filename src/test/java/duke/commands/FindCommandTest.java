package duke.commands;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.tasklist.Deadline;
import duke.tasklist.Event;
import duke.tasklist.Task;
import duke.tasklist.TaskList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {
    @Test
    public void FindFreeTimeTest() throws DukeException {
        // String expectedResult = "The specified date is fully scheduled from 12AM to 12PM";
        String expectedResult = "Free time for specified date : 02:00 AM - 10:00 AM , 12:00 PM - 10:00 PM";

        String taskInput = "Testing";
        String dateTimeInput1 = "12-12-2022 0000";
        String dateTimeInput2 = "12-12-2022 1200";
        String dateTimeInput3 = "12-12-2022 2200";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime formattedDateTimeInput = LocalDateTime.parse(dateTimeInput1, formatter);

        Event newTask1 = new Event(taskInput, LocalDateTime.parse(dateTimeInput1, formatter));
        Deadline newTask2 = new Deadline(taskInput, LocalDateTime.parse(dateTimeInput2, formatter));
        Event newTask3 = new Event(taskInput, LocalDateTime.parse(dateTimeInput3, formatter));

        ArrayList<Task> newArrayList = new ArrayList<Task>();
        newArrayList.add(newTask1);
        newArrayList.add(newTask2);
        newArrayList.add(newTask3);

        TaskList newTaskList = new TaskList(newArrayList);

        // Parser parser = new Parser("free 12-12-2022");
        assertEquals(FindCommand.findFreeTime(newTaskList), expectedResult);
    }
}
