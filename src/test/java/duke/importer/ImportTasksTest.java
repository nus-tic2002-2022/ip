package duke.importer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ImportTasksTest {

    String todo = "[T][ ] read book";
    String deadline = "[D][X] read book (by: 2002-02-20 2020)";
    String event = "[E][ ] read book (at: 2002-02-20 2020)";

    @Test
    void importTodoTest(){
        String description = todo.substring(7);
        boolean doneStatus = isItDone(todo.charAt(4));
        char taskType = todo.charAt(1);

        Assertions.assertEquals("read book", description);
        Assertions.assertFalse(doneStatus);
        Assertions.assertEquals('T',taskType);
    }

    @Test
    void importDeadlineTest(){
        String description = "", date = "";
        boolean doneStatus = isItDone(deadline.charAt(4));

        for(int i=7;i<deadline.indexOf("(by:"); i++){
            description += deadline.charAt(i);
        }
        for(int i=deadline.indexOf("(by:")+4;i<deadline.length()-1; i++){
            date += deadline.charAt(i);
        }

        Assertions.assertEquals("read book", description.trim());
        Assertions.assertEquals("2002-02-20 2020", date.trim());
        Assertions.assertTrue(doneStatus);
    }

    @Test
    void importEventTest(){
        String description = "", date = "";
        boolean doneStatus = isItDone(event.charAt(4));

        for(int i=7;i<event.indexOf("(at:"); i++){
            description += event.charAt(i);
        }
        for(int i=event.indexOf("(at:")+4;i<event.length()-1; i++){
            date += event.charAt(i);
        }

        Assertions.assertEquals("read book", description.trim());
        Assertions.assertEquals("2002-02-20 2020", date.trim());
        Assertions.assertFalse(doneStatus);
    }

    boolean isItDone(char mark){
        return mark != ' ';
    }
}