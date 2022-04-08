package duke.data.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest() {
        String expectedResult ="[T][ ] go shopping ";
        String input1 = "go shopping";
        Task t = new Todo(input1);
        assertEquals(t.toString(), expectedResult);
    }

    @Test
    public void toSaveStrTest() {
        String expectedResult ="T , 0 , go shopping";
        String input1 = "go shopping";
        Task t = new Todo(input1);
        assertEquals(t.toSaveStr(), expectedResult);
    }

}