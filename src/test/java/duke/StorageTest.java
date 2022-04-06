package duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    int len;

    @Test
    public void readFileTest() {

        String fileName = "data\\duke.txt";

        Storage s = new Storage(fileName);
        try {
            s.readFile();
            len = TaskList.getListLength();
        } catch (IOException e) {
            Ui.printFileErrorMsg(fileName);
        }

        assertEquals(5, len); //number of tasks might change at the point of testing.

    }

}
