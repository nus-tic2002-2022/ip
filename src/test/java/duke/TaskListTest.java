package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    Task t1 = new Todo("buy pens");
    Task t2 = new Event("go shopping w mum /at 2022-03-30T17:00");
    Task t3 = new Deadline("final submission of tba3222 /by 2022-04-01T13:00");

    @Test
    public void getStatusIconTest() {

        assertEquals(" ", t1.getStatusIcon());
        assertEquals(" ", t2.getStatusIcon());
        assertEquals(" ", t3.getStatusIcon());

    }

    @Test
    public void getStatusBooleanTest() {

        assertEquals(false, t1.getStatusBoolean());
        assertEquals(false, t2.getStatusBoolean());
        assertEquals(false, t3.getStatusBoolean());

    }

    @Test
    public void getDescriptionTest() {

        assertEquals("buy pens", t1.getDescription());
        assertEquals("go shopping w mum", t2.getDescription());
        assertEquals("final submission of tba3222", t3.getDescription());

    }

    @Test
    public void toStringTest() {
        //assertEquals(2,2);

        assertEquals("[T] [ ] buy pens", t1.toString());
        assertEquals("[E] [ ] go shopping w mum (at: 2022-03-30T17:00)", t2.toString());
        assertEquals("[D] [ ] final submission of tba3222 (by: 2022-04-01T13:00)", t3.toString());

    }


}
