package com.calebjianhui.duke.enums;

/**
 * The different type of list commands
 * 1. Normal listing (Sorted based on time of input to task list)
 * 2. Schedule listing (Display DateModule tasks such as events and deadlines,
 * sorted based on time). Can either list all or only a specific date
 * 3. This just indicates that the type given is invalid
 */
public enum ListCommandType {
    NORMAL,
    SCHEDULE,
    INVALID_COMMAND
}
