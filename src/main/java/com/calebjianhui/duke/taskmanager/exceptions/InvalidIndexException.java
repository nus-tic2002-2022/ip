package com.calebjianhui.duke.taskmanager.exceptions;

/**
 * Exception thrown when an invalid index is provided.
 * E.g < 0 or out of bounds
 * **/
public class InvalidIndexException extends Exception {
    private static final String REPLY_GENERIC = "Task is not found. Please provide a valid index.";
    private static final String REPLY_NO_ONGOING_TASK = "You do not have any ongoing task. Add one now?";

    public static final String OPTION_GENERIC = "generic";
    public static final String OPTION_EMPTY_TASK_LIST = "empty";

    String type;

    public InvalidIndexException() {
        super();
        this.type = REPLY_GENERIC;
    }

    public InvalidIndexException(String type) {
        super();
        this.type = type;
    }

    @Override
    public String getMessage() {
        if (OPTION_EMPTY_TASK_LIST.equals(type)) {
            return REPLY_NO_ONGOING_TASK;
        } else {
            return REPLY_GENERIC;
        }
    }

    public String getType() {
        return type;
    }
}