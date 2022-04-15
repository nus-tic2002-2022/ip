package duke.storage;

import duke.exception.IllegalValueException;
import duke.tasklist.Task;
import duke.tasklist.tasklist;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class storage {
    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "data/duke.txt";


    public final Path path;

    /**
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
    public storage() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }


    public storage(String filePath) throws InvalidStorageFilePathException {
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

    public void save(String fileName, tasklist TL) throws IOException {
        File filename = new File(DEFAULT_STORAGE_FILEPATH);
        boolean newFile = filename.createNewFile();
        FileOutputStream oFile = new FileOutputStream(fileName, false);
        PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
        for (int i = 0; i < TL.getSize(); i++) {
            pw.println(TL.getTask(i).getType() + " | " + TL.getTask(i).getStatusIconS() + " | " + TL.getTask(i).getDescription() + " | " );
        }
        pw.close();
    }


    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

}

