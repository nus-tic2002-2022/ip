package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testToFileString(){
        Task testTask = new Deadline("test", "1996-12-03");
        String actual = testTask.toFileString();
        String expected = "D|0|test|1996-12-03";

        assertEquals(actual, expected);
    }
}
