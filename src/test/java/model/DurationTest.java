package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DurationTest {
    @Test
    public void testToFileString(){
        Task testTask = new Duration("test", "2");
        String actual = testTask.toFileString();
        String expected = "DR|0|test|2";

        assertEquals(actual, expected);
    }
}
