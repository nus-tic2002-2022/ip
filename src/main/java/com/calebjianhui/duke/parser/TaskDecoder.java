package com.calebjianhui.duke.parser;

/**
 * Decode a given task for storage purposes
 **/
public class TaskDecoder {
    private static final String ENCODING_SEPARATOR = " /[|] ";

    /**
     * Returns a string array containing the decoded tasks
     *
     * @param task String containing an encoded task string
     * @return String[] containing the decoded task
     * Index:
     * 0 - Type
     * 1 - Done Status
     * 2 - Raw Description
     **/
    public static String[] decodeTask(String task) {
        return task.split(ENCODING_SEPARATOR, 3);
    }
}
