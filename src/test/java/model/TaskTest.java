package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testIsDone(){
        Task testTask = new Task("test");
        boolean actual = testTask.isDone();
        boolean expected = false;

        assertEquals(actual, expected);
    }

    @Test
    public void testSetDone(){
        Task testTask = new Task("test");
        testTask.setDone(true);
        boolean actual = testTask.isDone();
        boolean expected = true;

        assertEquals(actual, expected);
    }

    @Test
    public void testToFileString(){
        Task testTask = new Task("test");
        String actual = testTask.toFileString();
        String expected = "";

        assertEquals(actual, expected);
    }
}
