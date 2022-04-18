package zhixuan.duke.parser;

import zhixuan.duke.commands.*;
import zhixuan.duke.ui.DukeUI;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private Command provideInput(String data) {
        return new Parser(new Scanner(new ByteArrayInputStream(data.getBytes()))).processInput();
    }

    @Test
    void provideInput_validAddCommand_success() {
        // AddCommand
        // Add todo
        Command processedInput = provideInput("todo test");
        assertTrue(processedInput.getClass().isAssignableFrom(AddCommand.class));
        // Add deadline
        Command processedInput2 = provideInput("deadline Go to sleep /by 2022-04-20 23:59");
        assertTrue(processedInput2.getClass().isAssignableFrom(AddCommand.class));
        // Add event
        Command processedInput3 = provideInput("event Finals /at 2022-04-25 09:00");
        assertTrue(processedInput3.getClass().isAssignableFrom(AddCommand.class));
    }

    @Test
    void provideInput_invalidCommand_fail() {
        // [AddCommand]
        // todo
        Command processedInput = provideInput("todoTest");
        assertTrue(processedInput.getClass().isAssignableFrom(InvalidCommand.class));
        // delete with non-digit
        Command processedInput2 = provideInput("delete f");
        assertTrue(processedInput2.getClass().isAssignableFrom(InvalidCommand.class));
    }

    @Test
    void processInput_validExitCommand_success() {
        Command processedInput = provideInput("bye");
        assertTrue(processedInput.getClass().isAssignableFrom(ExitCommand.class));
    }

}
