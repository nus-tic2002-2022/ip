package com.calebjianhui.duke.parser;

public class TaskDecoder {
    private static final String ENCODING_SEPARATOR = " /[|] ";

    /**
     * Decode a given task
     *
     * @return String[] containing the decoded task
     * Index:
     * 0 - Type
     * 1 - Done Status
     * 2 - Raw Description
     * **/
    public static String[] decodeTask(String task) {
        return task.split(ENCODING_SEPARATOR, 3);
    }
}
