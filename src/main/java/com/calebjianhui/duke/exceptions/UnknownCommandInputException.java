package com.calebjianhui.duke.exceptions;

public class UnknownCommandInputException extends Exception {
    final static String DUKE_UNSURE_COMMAND_REPLY = "Hmm, I don't understand what that means. Can you explain again?";

    public UnknownCommandInputException() {
        super();
    }

    @Override
    public String getMessage() {
        return DUKE_UNSURE_COMMAND_REPLY;
    }
}