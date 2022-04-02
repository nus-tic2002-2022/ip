package duke.exception;

import duke.storage.Storage;

public class DukeException extends Exception{

    public String message;

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}