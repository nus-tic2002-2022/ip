package duke.storage;

import duke.data.entity.Task;
import duke.data.exception.DukeException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will store filePath of a text file
 * and provide methods to create, read and modify the text file.
 */
public class Storage {

    /**
     * Default file path used if the user doesn't provide the file name.
     */
    public static final String DEFAULT_STORAGE_FILEPATH = "task.txt";

    public final Path path;

    public Storage(String filePath) throws DukeException {
        if (filePath == null || filePath.equals(""))
            filePath = DEFAULT_STORAGE_FILEPATH;
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.txt'
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /**
     * Saves the {@code task} data to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void save(List<Task> tasks) throws DukeException {
        try {

            assert tasks != null : "task to find cannot be empty";
            List<String> taskToStore = TaskWriter.writeTaskList(tasks);
            Files.write(path, taskToStore);

        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        }
    }

    /**
     * Loads the {@code task} data from this storage file, and then returns it.
     * Returns an empty {@code task} if the file does not exist, or is not a regular file.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public List<Task> load() throws DukeException {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new ArrayList<>();
        }
        try {
            return TaskReader.readTaskList(Files.readAllLines(path));
        } catch (FileNotFoundException fe) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
            // other errors
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        } catch (DateTimeParseException pe) {
            throw new DukeException("Error reading from file: " + path);
        }
    }

    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStorageFilePathException extends DukeException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends DukeException {
        public StorageOperationException(String message) {
            super(message);
        }
    }


}