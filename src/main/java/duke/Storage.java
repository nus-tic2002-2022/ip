package duke;
import duke.exceptions.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private Path path;

    public Storage(String fileLocation) {
        path = Paths.get(fileLocation);
    }
    /**
     * Save the provided list of lines to a file.
     *
     * @param lines list of lines to save.
     * @throws IOException if the task cannot be recorded.
     */

    public void store(List<String> lines) throws IOException {
        Files.write(path, lines);
    }

    /**
     * Read the file and return the list of lines.
     *
     * @return list of lines loaded.
     * @throws IOException if the lines cannot be loaded.
     */
    public List<String>load() throws IOException{
        return Files.readAllLines(path);
    }
}
