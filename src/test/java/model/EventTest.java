package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToFileString(){
        Task testTask = new Event("test", "12/03/1996");
        String actual = testTask.toFileString();
        String expected = "E|0|test|12/03/1996";

        assertEquals(actual, expected);
    }
}
