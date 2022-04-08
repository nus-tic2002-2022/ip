package duke.Tasklist;

import duke.Exception.DukeException;
import duke.Exception.dateparseException;
import duke.Exception.timeparseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {
    @Test
    public void testgetstatus() {
        try {
            Deadlines t = new Deadlines("submit report","by 2022-05-17 2359");
            assertEquals("[D][ ] submit report (May 17 2022 11:59pm)", Deadlines.testgetStatus(t.getStatus()));

        } catch (DukeException e) {
        } catch (timeparseException e) {
        } catch (dateparseException e) {
        }
    }
}
