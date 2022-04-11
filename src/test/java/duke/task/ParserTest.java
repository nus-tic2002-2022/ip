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
        parser.capture("wang", fullCommand -> List.of(fullCommand[0]));
        parser.capture("wenwei", fullCommand -> List.of(fullCommand[1]));
    }

    @Test
    void parserValidCommandWang() throws DukeException, IOException {
        Command command = parser.parse(new String[]{"wang"});
        assertEquals("wang", command.run(new String[]{"wang", "input"}).get(0));
    }

    @Test
    void parserValidCommandWenwei() throws DukeException, IOException, DukeException {
        Command command = parser.parse(new String[]{"wenwei"});
        assertEquals("input", command.run(new String[]{"wenwei", "input"}).get(0));
    }
}