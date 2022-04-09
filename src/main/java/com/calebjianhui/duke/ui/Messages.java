package com.calebjianhui.duke.ui;

public class Messages {
    // Public Variables
    protected static final String MESSAGE_LOGO =
            "\t\t\t ____        _        \n"
                    + "\t\t\t|  _ \\ _   _| | _____ \n"
                    + "\t\t\t| | | | | | | |/ / _ \\\n"
                    + "\t\t\t| |_| | |_| |   <  __/\n"
                    + "\t\t\t|____/ \\__,_|_|\\_\\___|\tA variant";
    public static final String REPLY_ADD_TASK = "Roger. I will add this to your list:\n\t";
    public static final String REPLY_UPDATE_MARK_TASK = "Nice! I've marked this task as done:";
    public static final String REPLY_UPDATE_UNMARK_TASK = "Ok, I've marked this task as not done yet:";
    public static final String REPLY_UPDATE_DATE = "Alright! I have updated the date for your task.";
    public static final String REPLY_UPDATE_MESSAGE = "Alright! I have updated the details for your task.";
    public static final String REPLY_DELETE_TASK = "Alrighty. I will delete this task.:\n\t";
    public static final String UNKNOWN_ERROR = "An error occurred.";

    // Private Variables
    private static final String[] MESSAGE_WELCOME_LIST = {"Test"};

    public static String getWelcomeMessage(boolean successMessage) {
        if (successMessage) {
            return "Hello! I'm Duke\nWe managed to load your saved tasks!\nWhat else can I do for you today?";
        } else {
            return "Hello! I'm Duke\nWhat can I do for you?";
        }
    }
}
