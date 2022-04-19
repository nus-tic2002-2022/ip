package zhixuan.duke.data.exceptions;

public class InvalidFileException extends Exception {

    public static String FILE_ERROR = "File could not be loaded.";
    public static String FILE_NOT_READABLE = "File is not readable. Please choose a new file.";
    public static String FILE_NOT_WRITABLE = "File is not writable. Please choose a new file.";
    public static final String FILE_EMPTY = "File is empty.";

    public InvalidFileException(String errorMessage) {
        super(errorMessage);
        FILE_ERROR = errorMessage;
    }

    @Override
    public String getMessage () {
        return FILE_ERROR;
    }

}
