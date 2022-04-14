package duke.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EventTest extends TaskTest{

    String at;

    EventTest(){
        super();
        this.taskType = "E";
        this.at = "2022-02-22 2200";
        setDT(at);
    }


    @Test
    void EventGetTaskTypeTest(){
        Assertions.assertEquals("E", this.getTaskType());
    }

    @Test
    void EventAddFileStringTest(){Assertions.assertEquals("[E][ ] test (at: 2022-02-22 2200)", this.addToFile());}

    @Test
    void EventDisplayStringTest(){
        Assertions.assertEquals("[E][ ] test (at: Feb 22 2022, 10:00 PM)", this.toString());
    }

    @Override
    public String addToFile() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }

    @Override
    public String toString() { return "[E]" + super.toString() + "(at: " + printDT() +")";}

}