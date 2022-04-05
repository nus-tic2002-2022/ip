package parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.IncorrectCommand;
import commands.ListCommand;
import commands.MarkDoneCommand;
import commands.UnmarkDoneCommand;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void parse_emptyInput_returnsIncorrectCommand() {
        final String input = " ";
        parseAndAssertCommandType(input, IncorrectCommand.class);
    }

    @Test
    public void parse_listCommand_parsedCorrectly() {
        final String input = "list";
        parseAndAssertCommandType(input, ListCommand.class);
    }

    @Test
    public void parse_ExitCommand_parsedCorrectly() {
        final String input = "bye";
        parseAndAssertCommandType(input, ExitCommand.class);
    }

    @Test
    public void parse_AddTodoCommand_parsedCorrectly() {
        final String input = "todo test";
        parseAndAssertCommandType(input, AddCommand.class);
    }

    @Test
    public void parse_AddDeadlineCommand_parsedCorrectly() {
        final String input = "deadline test /by today";
        parseAndAssertCommandType(input, AddCommand.class);
    }

    @Test
    public void parse_AddDeadlineCommandWithoutBy_returnsIncorrectCommand() {
        final String input = "deadline test today";
        parseAndAssertCommandType(input, IncorrectCommand.class);
    }

    @Test
    public void parse_AddEventCommand_parsedCorrectly() {
        final String input = "event test /at today";
        parseAndAssertCommandType(input, AddCommand.class);
    }

    @Test
    public void parse_AddEventCommandWithoutAt_returnsIncorrectCommand() {
        final String input = "event test today";
        parseAndAssertCommandType(input, IncorrectCommand.class);
    }

    @Test
    public void parse_markCommand_parsedCorrectly() {
        final String input = "mark 1";
        parseAndAssertCommandType(input, MarkDoneCommand.class);
    }

    @Test
    public void parse_markCommandNotNumber_returnsIncorrectCommand() {
        final String input = "mark asd";
        parseAndAssertCommandType(input, IncorrectCommand.class);
    }

    @Test
    public void parse_unmarkCommand_parsedCorrectly() {
        final String input = "unmark 1";
        parseAndAssertCommandType(input, UnmarkDoneCommand.class);
    }

    @Test
    public void parse_unmarkCommandNotNumber_returnsIncorrectCommand() {
        final String input = "unmark asd";
        parseAndAssertCommandType(input, IncorrectCommand.class);
    }

    @Test
    public void parse_deleteCommand_parsedCorrectly() {
        final String input = "delete 1";
        parseAndAssertCommandType(input, DeleteCommand.class);
    }

    @Test
    public void parse_deleteCommandNotNumber_returnsIncorrectCommand() {
        final String input = "delete asd";
        parseAndAssertCommandType(input, IncorrectCommand.class);
    }


    /**
     * Parses input and asserts the class/type of the returned command object.
     *
     * @param input to be parsed
     * @param expectedCommandClass expected class of returned command
     * @return the parsed command object
     */
    @SuppressWarnings("unchecked")
    private <T extends Command> T parseAndAssertCommandType(String input, Class<T> expectedCommandClass) {
        final Command result = parser.parseCommand(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        return (T) result;
    }
}
