package duke.task;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class DukeExceptionTest {
    @Test
    void testDukeException() {
        DukeException exception = new DukeException();
        assertNull(exception.getMessage());
    }
}