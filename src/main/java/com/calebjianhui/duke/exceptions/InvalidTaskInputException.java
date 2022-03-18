package com.calebjianhui.duke.exceptions;

public class InvalidTaskInputException extends Exception {
    String description;

    public InvalidTaskInputException(String errorMessage) {
        super(errorMessage);
        description = errorMessage;
    }

    @Override
    public String getMessage() {
        return description;
    }
}
