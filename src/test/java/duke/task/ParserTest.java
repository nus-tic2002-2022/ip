package duke.task;

import duke.Parser;
import duke.command.Command;
import duke.exceptions.DukeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    private Parser parser;

    @BeforeEach
    void  elementary() {
        parser = new Parser();
        parser.capture("yu", fullCommand -> List.of(fullCommand[0]));
        parser.capture("wei", fullCommand -> List.of(fullCommand[1]));
    }

    @Test
    void parserValidCommandYu() throws DukeException, IOException {
        Command command = parser.parse(new String[]{"yu"});
        assertEquals("yu", command.run(new String[]{"yu", "input"}).get(0));
    }

    @Test
    void parserValidCommandWei() throws DukeException, IOException, DukeException {
        Command command = parser.parse(new String[]{"wei"});
        assertEquals("input", command.run(new String[]{"wei", "input"}).get(0));
    }
}
