package storage;

import org.junit.jupiter.api.Test;
import tasks.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void convertStrToTask_testTodoTask_success() {
        Task taskConverted = Storage.convertStrToTask("T|0|test| ");
        assertEquals("[T][ ] test", taskConverted.toString());
    }

    @Test
    public void convertStrToTask_testDeadlineTask_success() {
        Task taskConverted = Storage.convertStrToTask("D|0|test|Sat Jan 01 00:00:00 SGT 2022");
        assertEquals("[D][ ] test (by: Jan 01)", taskConverted.toString());
    }

    @Test
    public void convertStrToTask_testEventTask_success() {
        Task taskConverted = Storage.convertStrToTask("E|0|test|Sat Jan 01 00:00:00 SGT 2022");
        assertEquals("[E][ ] test (at: Jan 01 0AM)", taskConverted.toString());
    }

    @Test
    public void convertStrToTask_testTodoMarkedTask_success() {
        Task taskConverted = Storage.convertStrToTask("T|1|test| ");
        assertEquals("[T][X] test", taskConverted.toString());
    }

}
