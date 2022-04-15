package duke.command;
import duke.task.*;

public class Duke {

    public static void main(String[] args) throws DukeException {
        Storage.main();
        UI.main();
        DateTimeList.main();
    }
}