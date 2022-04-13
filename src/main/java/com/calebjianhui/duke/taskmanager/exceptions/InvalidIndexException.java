package com.calebjianhui.duke.taskmanager.exceptions;

/**
 * Exception thrown when an invalid index is provided.
 * E.g < 0 or out of bounds
 **/
public class InvalidIndexException extends Exception {
    public static final String REPLY_INVALID_INDEX = "Task is not found. Please provide a valid index.";
    public static final String REPLY_NO_ONGOING_TASK = "You do not have any ongoing task. Add one now?";
    public static final String REPLY_FILTERED_EMPTY_TASK = "Hmmm.. There are no task that matches what you mentioned.";

    private final String message;

    /**
     * InvalidIndexException constructor
     *
     * @param message Error message to be displayed to user
     **/
    public InvalidIndexException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
