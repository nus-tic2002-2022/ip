package duke.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class TaskListTest {

    DeadlineTest deadline = new DeadlineTest();

    @Test
    void markTaskTest(){
        deadline.isDone = true;
        Assertions.assertEquals("[D][X] test (by: Feb 22 2022, 10:00 PM)", deadline.toString());
    }

    @Test
    void unmarkTaskTest(){
        deadline.isDone = false;
        Assertions.assertEquals("[D][ ] test (by: Feb 22 2022, 10:00 PM)", deadline.toString());
    }

    @Test
    void doAfterTest(){
        Assertions.assertNotEquals(0,deadline.getDT().compareTo(LocalDateTime.now()) );
    }
}