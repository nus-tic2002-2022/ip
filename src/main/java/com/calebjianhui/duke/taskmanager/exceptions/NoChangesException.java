package com.calebjianhui.duke.taskmanager.exceptions;

/**
 * Exception thrown when there are no changes effected
 **/
public class NoChangesException extends Exception {
    private static final String REPLY = "Hmm, there are no changes to be made. Is there a mistake?";

    public NoChangesException() {
        super();
    }

    @Override
    public String getMessage() {
        return REPLY;
    }
}