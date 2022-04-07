package duke.Tasklist;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testgetstatus() {
        ToDo t1 = new ToDo("borrow book");
        assertEquals("[T][ ] borrow book", ToDo.testgetStatus(t1.getStatus()));
    }
}
