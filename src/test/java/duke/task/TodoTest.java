package duke.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodoTest extends TaskTest{

    TodoTest(){
        super();
        this.taskType = "T";
    }

    @Test
    void TodoGetTaskTypeTest(){
        Assertions.assertEquals("T", this.getTaskType());
    }

    @Test
    void TodoAddFileStringTest(){Assertions.assertEquals("[T][ ] test ", this.addToFile());}

    @Test
    void TodoDisplayStringTest(){
        Assertions.assertEquals("[T][ ] test ", this.toString());
    }

    @Override
    public String addToFile() {
        return toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

