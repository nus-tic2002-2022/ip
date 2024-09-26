package duke.task;

import duke.Storage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageTest {
    private static final String location = "StorageTest.txt";
    private static final List<String> data = List.of("This is a test file.", "You can delete it.");

    @Test
    void storeAndLoadTest() throws IOException {
        new Storage(location).store(data);
        List<String> loadData = new Storage(location).load();
        assertEquals(data, loadData);
        assertTrue(Files.deleteIfExists(Paths.get(location)));
    }

}