package duke.task;

import duke.exceptions.DukeCheckLineException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class DukeCheckLineExceptionTest {
    @Test
    void testDukeCheckLineException() {
        DukeCheckLineException exception = new DukeCheckLineException();
        assertNull(exception.getMessage());
    }
}