package duke.importer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class FileContentCheckerTest {

    final String LINE = "[D][X] test (by: 2020-02-20 2020)";
    final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm").withResolverStyle(ResolverStyle.STRICT);

    @Test
    public void LINECheckerTest (){

        Assertions.assertFalse(formatCheckerTest());
        Assertions.assertTrue(taskCheckerTest());
        Assertions.assertTrue(markCheckerTest());
        Assertions.assertTrue(byCheckerTest());
        Assertions.assertFalse(atCheckerTest());
        DateTimeParseException d = Assertions.assertThrows(DateTimeParseException.class, () -> {LocalDateTime.parse("2020-20-02 2020", DATE_TIME_FORMAT);});

    }

    @Test
    boolean formatCheckerTest(){
        //Returns True if format is wrong
        return (LINE.charAt(0) != '[' || LINE.charAt(2) != ']' || LINE.charAt(3) != '[' || LINE.charAt(5) != ']');
    }

    @Test
    boolean taskCheckerTest() {
        //Returns True if correct alphabet at index 1
        return (LINE.charAt(1) == 'T' || LINE.charAt(1) == 'D' || LINE.charAt(1) == 'E');
    }

    @Test
    boolean markCheckerTest(){
        //Returns True if done status format is correct at index 4
        return (LINE.charAt(4) == ' ' || LINE.charAt(4) == 'X');
    }

    @Test
    boolean byCheckerTest() {
        //Returns True if is deadline task and has a (by: )
        return (LINE.charAt(1) == 'D' && LINE.contains("(by:") && LINE.charAt(LINE.length()-1) == ')');
    }
    
    @Test
    boolean atCheckerTest() {
        //Returns True if is event task and has a (at: )
        return (LINE.charAt(1) == 'E' && LINE.contains("(at:") && LINE.charAt(LINE.length()-1) == ')');
    }
}
