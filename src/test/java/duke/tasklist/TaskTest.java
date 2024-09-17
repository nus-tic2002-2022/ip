package duke.tasklist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void TodoTest(){
        String expectedResult = "[T]\t[ ]\t\tread book";
        String input = "read book";
        Task newTask = new Task(input);
        assertEquals(newTask.toString(), expectedResult);
    }
}
