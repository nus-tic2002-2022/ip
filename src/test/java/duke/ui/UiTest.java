package duke.ui;

import org.junit.jupiter.api.Test;

import static duke.ui.Ui.generateMessage;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiTest {
    @Test
    public void GenerateMessageTest(){
        boolean isExpectedResult = false;
        String expectedResult1 = "\t\t\t\t\t\t\t\t Haro ! Feel free to let me know how can I help you ! Haro !";
        String expectedResult2 = "\t\t\t\t\t\t\t\t Haro ! Haro ! Haro !";

        if(generateMessage("hello", "").equalsIgnoreCase(expectedResult1) || generateMessage("hello", "").equalsIgnoreCase(expectedResult2)){
            isExpectedResult = true;
        }

        assertTrue(isExpectedResult);
    }
}
