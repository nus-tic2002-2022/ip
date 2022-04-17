package com.calebjianhui.duke.taskmanager.exceptions;

public class InvalidTaskInputException extends Exception {
    public static final String REPLY_TASK_NO_DATE =
            "The selected task does not have any date to be edited. Perhaps select another task?";
    public static final String REPLY_DEADLINE_NO_DATE = "Please include the deadline for your task.";
    public static final String REPLY_DEADLINE_INVALID_LENGTH =
            "Please include the description and deadline for your task.";
    public static final String REPLY_EVENT_NO_DATE = "Please include the event date.";
    public static final String REPLY_EVENT_INVALID_LENGTH = "Please include the description and time of your event.";
    public static final String REPLY_FIXED_DURATION_NO_DURATION =
            "Please include the duration for your fixed duration task.";
    public static final String REPLY_FIXED_DURATION_INVALID_LENGTH =
            "Please include the description and duration of your task.";
    public static final String REPLY_LIST_INVALID_DATE = "Please indicate a valid date.";

    private final String message;

    /**
     * InvalidTaskInputException constructor
     *
     * @param message Error message to be displayed to user
     **/
    public InvalidTaskInputException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

