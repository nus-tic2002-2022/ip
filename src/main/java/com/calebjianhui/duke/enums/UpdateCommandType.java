package com.calebjianhui.duke.enums;

/**
 * The different type of update command
 * 1. Edit description
 * 2. Edit date
 * 3. Mark task as done
 * 4. Mark task as undone
 * 5. This just indicates that the type given is invalid
 */
public enum UpdateCommandType {
    EDIT_DESCRIPTION,
    EDIT_DATE,
    MARK,
    UNMARK,
    INVALID_COMMAND
}
