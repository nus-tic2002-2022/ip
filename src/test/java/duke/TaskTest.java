package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TaskTest {
    @Test
    public void testNewTask() {
        Task test = new Task("Not Done Task");

        Assertions.assertEquals(test.toString(), "[ ] Not Done Task");
    }

    @Test
    public void testSetDoneTask() {
        Task test = new Task("Done Task");
        test.isDone = true;

        Assertions.assertEquals(test.toString(), "[X] Done Task");
    }

    @Test
    public void testGetStatusIconX() {
        Task test = new Task("Done Task");
        test.isDone = true;

        Assertions.assertEquals(test.getStatusIcon(), "X");
    }

    @Test
    public void testGetStatusIcon() {
        Task test = new Task("Not Done Task");

        Assertions.assertEquals(test.getStatusIcon(), " ");
    }
}