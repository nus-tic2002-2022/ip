package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testToFileString(){
        Task testTask = new Todo("test");
        String actual = testTask.toFileString();
        String expected = "T|0|test";

        assertEquals(actual, expected);
    }
}
