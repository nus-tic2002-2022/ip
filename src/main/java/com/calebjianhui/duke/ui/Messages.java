package com.calebjianhui.duke.ui;

/**
 * This class simply holds most template messages to display to the user
 **/
public class Messages {
    // Public Variables
    // - Divider in underscore style
    public static final String DIVIDER_UNDERSCORE = "____________________________________________________________\n";
    public static final String DIVIDER_UNDERSCORE_EXTENDED =
            "__________________________________________________________________________________________\n";
    // Divider in dash style
    public static final String DIVIDER_DASH = "------------------------------------------------------------\n";
    public static final String DIVIDER_DASH_EXTENDED =
            "------------------------------------------------------------------------------------------\n";
    public static final String REPLY_ADD_TASK = "Roger. I will add this to your list:\n    ";
    public static final String REPLY_CLONE_SUCCESS = "Nice, I have made a copy of the task you specified.\n    ";
    public static final String REPLY_UPDATE_MARK_TASK = "Well done! I've marked this task as done:";
    public static final String REPLY_UPDATE_UNMARK_TASK = "Ok, I've marked this task as not done yet:";
    public static final String REPLY_UPDATE_DATE = "Alright! I have updated the date for your task.";
    public static final String REPLY_UPDATE_MESSAGE = "Alright! I have updated the details for your task.";
    public static final String REPLY_DELETE_TASK = "Alright. I will delete this task.:\n    ";
    public static final String REPLY_DELETE_ALL_TASK =
            "Order received! I have cleared all your tasks and you can start anew from now.";

    /**
     * Indentation used at the start of every line of reply
     * - To allow Duke to reply with a ~ and tab at the front
     */
    protected static final String DUKE_MESSAGE_INDENTATION = "~    ";
    protected static final String MESSAGE_LOGO =
            "\t\t ____        _        \n"
                    + "\t\t|  _ \\ _   _| | _____ \n"
                    + "\t\t| | | | | | | |/ / _ \\\n"
                    + "\t\t| |_| | |_| |   <  __/\n"
                    + "\t\t|____/ \\__,_|_|\\_\\___|\tA variant";


    public static String getWelcomeMessage(boolean successMessage) {
        if (successMessage) {
            return "Hello! I'm Duke, your personal assistant.\nWe managed to load your saved tasks!"
                    + "\nWhat else can I do for you today?";
        } else {
            return "Hello! I'm Duke, your personal assistant.\nWhat can I do for you?";
        }
    }
}
