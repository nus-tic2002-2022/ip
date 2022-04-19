package duke.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DeadlineTest extends TaskTest {
    
    String by;

    DeadlineTest(){
        super();
        this.taskType = "D";
        this.by = "2022-02-22 2200";
        setDT(by);
    }


    @Test
    void DeadlineGetTaskTypeTest(){
        Assertions.assertEquals("D", this.getTaskType());
    }

    @Test
    void DeadlineAddFileStringTest(){Assertions.assertEquals("[D][ ] test (by: 2022-02-22 2200)", this.addToFile());}

    @Test
    void DeadlineDisplayStringTest(){
        Assertions.assertEquals("[D][ ] test (by: Feb 22 2022, 10:00 PM)", this.toString());
    }

    @Override
    public String addToFile() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    @Override
    public String toString() { return "[D]" + super.toString() + "(by: " + printDT() +")";}
}